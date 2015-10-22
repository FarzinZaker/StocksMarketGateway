package stocks

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import grails.converters.JSON

class TwitterTagLib {

    static namespace = "twitter"

    def graphDBService
    def commonGraphService
    def groupGraphService
    def springSecurityService
    def sharingService
    def propertyGraphService
    def materialGraphService
    def rateGraphService
    def likeGraphService
    def commentGraphService
    def personGraphService
    def followGraphService

    def shareGroups = { attrs, body ->
        def name = attrs.name
        def id = attrs.id ?: attrs.name
        def user = springSecurityService.currentUser as User

        def currentSharedGroups = []
        if (attrs.materialId)
            currentSharedGroups = sharingService.materialShareGroups(attrs.materialId as Long).collect {
                it.id.toString()
            }

        out << "<div class='shareGroupList' style='${attrs.style}'>"

        ([commonGraphService.publicGroupAndUnwrap] + groupGraphService.listForAuthor(user)).each { group ->
            out << form.checkbox(name: "${name}_group_${group.id}", id: "${id}_group_${group.id}", text: group.title, checked: currentSharedGroups.contains(group.id))
        }

        out << "</div>"
    }

    def shareTags = { attrs, body ->

        def groups = [
                [
                        text : message(code: 'twitter.property.symbol.label'),
                        value: 'Symbol'
                ],
                [
                        text : message(code: 'twitter.property.index.label'),
                        value: 'Index'
                ],
                [
                        text : message(code: 'twitter.property.coin.label'),
                        value: 'Coin'
                ],
                [
                        text : message(code: 'twitter.property.currency.label'),
                        value: 'Currency'
                ],
                [
                        text : message(code: 'twitter.property.metal.label'),
                        value: 'Metal'
                ],
                [
                        text : message(code: 'twitter.property.oil.label'),
                        value: 'Oil'
                ],
                [
                        text : message(code: 'twitter.property.future.label'),
                        value: 'Future'
                ]
        ]

        def name = attrs.name
        def id = attrs.id ?: attrs.name

        def currentAboutProperties = []
        if (attrs.materialId)
            currentAboutProperties = sharingService.materialAboutProperties(attrs.materialId as Long)

        out << asset.javascript(src: 'jquery.color.js')

        out << form.hidden(name: name, id: id, value: (currentAboutProperties.collect {
            [text: it.title, value: it.identifier, type: it.label]
        }) as JSON)

        out << """
        <span class="k-rtl" style="display:block;${attrs.style ?: ''}">
            <select id="${id}_tagGroup" name="${name}_tagGroup" style="width:${attrs.groupSelectorWidth ?: 200}px;">
"""
        groups.each { group ->
            out << """<option value="${group.value}">${group.text}</option>"""
        }
        out << """
            </select>
"""
        out << """
            <span id="${id}_tagItemContainer"></span>
"""

        out << """
            <a class="k-button" style="padding: 8px 10px 1px;" onclick="addtagValue_${id}()">
                <i class="fa fa-plus"></i>
            </a>
"""

        out << """
            <div class="multiSelectValues" id="tagValuesContainer_${id}">
                <ul>
"""
        currentAboutProperties.each { Map tagValue ->
            out << """
                    <li>
                        <i onclick="\$(this).parent().remove();updateTagJSON();">x</i>
                        ${tagValue.title}
                        <input type="hidden" name="tagValueText_${id}" value="${tagValue.title}"/>
                        <input type="hidden" name="tagValueValue_${id}" value="${tagValue.identifier}"/>
                        <input type="hidden" name="tagValueType_${id}" value="${tagValue.label}"/>
                    </li>
"""
        }
        out << """
                </ul>
            
                <div></div>
            </div>

        </span>
"""

        out << """
        <script language="javascript" type="text/javascript">

            \$(document).ready(function () {
                \$("#${id}_tagGroup").kendoComboBox({
                    change: function (e) {
                        init_${id}_tagItemSelector();
                    }
                });
                init_${id}_tagItemSelector();
            });

            function init_${id}_tagItemSelector() {
                \$('#${id}_tagItemContainer').empty().append('<input name="${name}_tagItem" id="${id}_tagItem" type="hidden"/>');
                \$("#${id}_tagItem").width('${attrs.itemSelectorWidth ?: 300}px').kendoComboBox({
                    dataTextField: "text",
                    dataValueField: "value",
                    filter: "contains",
                    index: 0,
                    dataSource: {
                        serverFiltering: true,
                        transport: {
                            type: 'odata',
                            read: {
                                url: "${
            createLink(controller: 'twitter', action: 'propertyAutoComplete')
        }?group=" + \$('#${
            id
        }_tagGroup').val(),
                                dataType: "json",
                                type: "POST"

                            }
                        },
                        schema: {
                            data: "data"
                        }
                    },
                    change: function (e) {
                        if (this.value() && this.selectedIndex == -1) {
                            var dt = this.dataSource._data[0];
                            this.text(dt[this.options.dataTextField]);
                            this._selectItem();
                        }
                    }
                });
            }

            var tagValueCounter_${id} = 1;
            function addtagValue_${id}() {
                var text = \$('#${id}_tagItem').data('kendoComboBox').text();
                var value = \$('#${id}_tagItem').data('kendoComboBox').value();
                var type = \$('#${id}_tagGroup').data('kendoComboBox').value();
                if (!value || value == '') {
                    window.alert('${message(code: 'alerting.query.register.notElementSelected.message')}');
                    return;
                }
                var alreadyAddedItem;
                \$("#tagValuesContainer_${id}").find("ul input[name=tagValueValue_${id}]").each(function () {
                    if (\$(this).val() == value)
                        alreadyAddedItem = \$(this).parent();
                });
                if (alreadyAddedItem) {
                    alreadyAddedItem.animateHighlight("#8ebc00", 1000);
                }
                else {
                    \$('#tagValuesContainer_${id}').find('ul')
                            .append('<li><i onclick="\$(this).parent().remove();updateTagJSON();">x</i>'
                                    + text
                                    + '<input type="hidden" name="tagValueText_${id}" value="' + text + '"/>'
                                    + '<input type="hidden" name="tagValueValue_${id}" value="' + value + '"/>'
                                    + '<input type="hidden" name="tagValueType_${id}" value="' + type + '"/>'
                                    + '</li>');
                }
                \$('#tagValuesContainer_${id}').parent().parent().find('.form-error').fadeOut(200);

                updateTagJSON();

                return false;
            }

            function updateTagJSON(){

                var list = [];
                \$.each(\$('#tagValuesContainer_${id}').find('li'), function(){
                    var item = new Object();
                    item.text = \$(this).find('[name=tagValueText_${id}]').val();
                    item.value = \$(this).find('[name=tagValueValue_${id}]').val();
                    item.type = \$(this).find('[name=tagValueType_${id}]').val();
                    list[list.length] = item;
                });
                \$('#${id}').val(JSON.stringify(list));
            }
        </script>
"""
    }

    def tagCloud = { attrs, body ->

        out << asset.javascript(src: 'tagcloud.js')
        out << """
        <div class="tagCloud">
        """

        def tags = []

        if (attrs.groupId)
            tags = groupGraphService.propertyCloud(attrs.groupId as String)
        else if (attrs.authorId)
            tags = personGraphService.propertyCloud(attrs.authorId as String)
        else if (attrs.propertyId)
            tags = propertyGraphService.propertyCloud(attrs.propertyId as String)
        else
            tags = propertyGraphService.propertyCloud()

        Collections.shuffle(tags)
        tags.each {
            def tag = it.title
            def count = it.count
            out << """
            <a href="${createLink(controller: "twitter", action: 'property', id: it.identifier)}" rel="${
                count
            }">${
                tag
            }</a>
        """
        }

        out << """
        </div>
        <script language="javascript" type="text/javascript">
            \$(".tagCloud a").tagcloud({
                size: {
                    start: 12,
                    end: 24,
                    unit: 'px'
                },
                color: {
                    start: "#007cbc",
                    end: "#8ebc00"
                }
            });
        </script>
        """
    }

    def relatedMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.getRelatedMaterials(attrs.id as String).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def newMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.getNewMaterials(attrs.id as String).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def topAuthorMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.topByAuthor(attrs.id as String).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def topGroupMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.topByGroup(attrs.id as String).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def topPropertyMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.topByProperty(attrs.id as String).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def rating = { attrs, body ->
        def rate = rateGraphService.getPersonRateForMaterial(springSecurityService.currentUser as User, attrs.material as OrientVertex)
        if (rate)
            out << render(template: '/rate/result', model: [rateValue: rate?.value])
        else
            out << render(template: '/rate/submit', model: [materialId: attrs.material?.id?.toString()?.replace('#', '')])
    }

    def commentList = { attrs, body ->
        if (attrs.materialId) {
            out << "<div class='newComments' id='cd_${attrs.materialId?.replace(':', '_')}'></div>"
            out << commentList(commentGraphService.getCommentList(attrs.materialId as String), "<div id='ed_${attrs.materialId?.replace(':', '_')}'>${attrs.emptyMessage}</div>" ?: '')
        }
        if (attrs.commentId) {
            out << "<div class='newComments' id='cc_${attrs.commentId?.replace(':', '_')}'></div>"
            out << commentList(commentGraphService.getChildCommentList(attrs.commentId as String))
        }
    }

    def commentList(List<Map> commentList, def emptyMessage = '') {
        if (commentList && commentList.size() > 0) {
            def result = ""
            commentList.each {
                result += render template: '/comment/view', model: [comment: it]
            }
            result
        } else
            emptyMessage
    }


    def like = { attrs, body ->
        def likesCount = likeGraphService.getLikesCount(attrs.commentId as String)
        def dislikesCount = likeGraphService.getDislikesCount(attrs.commentId as String)
        def hasLiked = likeGraphService.hasLiked(springSecurityService.currentUser as User, attrs.commentId as String)
        def hasDisliked = likeGraphService.hasDisliked(springSecurityService.currentUser as User, attrs.commentId as String)
        out << """
            <div class="rateWrapper">
                <span class="rate rateUp ${hasLiked ? 'active' : ''}" data-item="${attrs.commentId}">
                    <span class="rateUpN">${likesCount}</span>
                </span>
                <span class="rate rateDown ${hasDisliked ? 'active' : ''}" data-item="${attrs.commentId}">
                    <span class="rateDownN">${dislikesCount}</span>
                </span>
            </div>
"""
    }

    def rateGage = { attrs, body ->
        def value = rateGraphService.getAverageRate(attrs.materialId as String)

        if (!value)
            return

        def percent = (value as Double) * 100 / 5

        def displayValue = message(code: "rating.display.${Math.round(value)}")

        value = value - 3

        if (value > 2)
            value = 2
        else if (value < -2)
            value = -2
        def val = ((-value + 2) * 100 / 5) as Double
        def red = [246, 53, 56]
        def white = [65, 69, 84]
        def green = [48, 204, 90]
        def start = green
        def end = white

        if (val > 50) {
            start = white;
            end = red;
            val = val % 51;
        }
        def r = Interpolate(start[0], end[0], 50, val);
        def g = Interpolate(start[1], end[1], 50, val);
        def b = Interpolate(start[2], end[2], 50, val);

        def bg = "rgb(${r}, ${g}, ${b})"

        def id = UUID.randomUUID().toString()?.replace('-', '_')

        out << """
            <div class="rateGage" id="gage_${id}">
                <div style="width: ${percent}%;background:${bg};">${attrs.showLabel ? displayValue : ''}</div>
            </div>
"""

//        out << """
//            <script language="javascript" type="text/javascript">
//                \$('#gage_${id}').kendoTooltip({
//                content: '${displayValue}',
//                width: 120,
//                position: "top"
//            });
//            </script>
//"""
    }

    def followScript = { attrs, body ->
        out << """
            <script language="javascript" type="text/javascript">
                function follow(btn, id){
                    \$(btn).hide();
                    \$('#loading_' + \$(btn).attr('id')).show();
                    \$.ajax({
                        type: "POST",
                        url: '${createLink(controller: 'twitter', action: 'follow')}',
                        data: { id: id }
                    }).done(function (response) {
                        \$(btn).replaceWith(response);
                        \$('#loading_' + \$(btn).attr('id')).hide();
                    });
                }

                function unfollow(btn, id){
                    \$(btn).hide();
                    \$('#loading_' + \$(btn).attr('id')).show();
                    \$.ajax({
                        type: "POST",
                        url: '${createLink(controller: 'twitter', action: 'unfollow')}',
                        data: { id: id }
                    }).done(function (response) {
                        \$(btn).replaceWith(response);
                        \$('#loading_' + \$(btn).attr('id')).hide();
                    });
                }
            </script>
"""
    }

    def followButton = { attrs, body ->
        def itemId = attrs.itemId?.toString()
        def id = UUID.randomUUID().toString().replace('-', '_')
        def user = springSecurityService.currentUser as User

        def followship = followGraphService.getUserFollowshipForItem(itemId, user?.id)
        out << """
            <span name="follow_${itemId.replace(':', '_')}" id="${id}" class="btn${followship ? 'Unfollow' : 'Follow'}"
                onclick="${followship ? 'unfollow' : 'follow'}(this, '${itemId}');">
                <i class='fa fa-${followship ? 'minus' : 'plus'}'></i>
                <span> ${message(code: "user.followList.${followship ? 'remove' : 'add'}")} </span>
                <div class='clear-fix'></div>
            </span>
"""
        out << form.loading(id: "loading_${id}")
    }

    private Integer Interpolate(start, end, steps, count) {
        def s = start
        def e = end
        def last = s + (((e - s) / steps) * count);
        Math.round(Math.floor(last));
    }
}
