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

    def topFollowedMaterials = { attrs, body ->

        out << '<ul id="material-list" class="clear-fix materialList sm">'
        def indexer = 0
        out << materialGraphService.topForHome(attrs.id as Long).collect {
            "<li class='${indexer++ % 2 ? 'even' : 'odd'}'>" + g.render(template: "/twitter/material/${it.label}", model: [material: it, imageSize: 60]) + '</li>'
        }.join('\n')
        out << '</ul>'
    }

    def rating = { attrs, body ->
        def rate = rateGraphService.getPersonRateForMaterial(springSecurityService.currentUser as User, attrs.materialId ? graphDBService.getVertex(attrs.materialId) : attrs.material as OrientVertex)
        if (rate)
            out << render(template: "/rate/result${attrs.inline ? 'Inline' : ''}", model: [rateValue: rate?.value])
        else
            out << render(template: "/rate/submit${attrs.inline ? 'Inline' : ''}", model: [materialId: attrs.materialId ?: attrs.material?.id?.toString()?.replace('#', '')])
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
            <div class="rateWrapper" id="rate_${attrs.commentId?.replace(':', '_')}">
                <span class="rate rateUp ${hasLiked ? 'active' : ''}" data-item="${attrs.commentId}">
                    <span class="rateUpN">${likesCount}</span>
                </span>
                <span class="rate rateDown ${hasDisliked ? 'active' : ''}" data-item="${attrs.commentId}">
                    <span class="rateDownN">${dislikesCount}</span>
                </span>
            </div>

            <script language="javascript" type="text/javascript">
                \$(function() {
                    jQuery.ajaxSetup({ cache:false });
                    rateObject.rate(\$('#rate_${attrs.commentId?.replace(':', '_')} .rate'));
                });
            </script>
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
                function follow(btn, id, callback){
                    \$(btn).hide();
                    \$('#loading_' + \$(btn).attr('id')).show();
                    \$.ajax({
                        type: "POST",
                        url: '${createLink(controller: 'twitter', action: 'follow')}',
                        data: { id: id }
                    }).done(function (response) {
                        \$(btn).replaceWith(response);
                        \$('#loading_' + \$(btn).attr('id')).hide();
                        if(callback)
                            callback(btn, id);
                    });
                }

                function unfollow(btn, id, callback){
                    \$(btn).hide();
                    \$('#loading_' + \$(btn).attr('id')).show();
                    \$.ajax({
                        type: "POST",
                        url: '${createLink(controller: 'twitter', action: 'unfollow')}',
                        data: { id: id }
                    }).done(function (response) {
                        \$(btn).replaceWith(response);
                        \$('#loading_' + \$(btn).attr('id')).hide();
                        if(callback)
                            callback(btn, id);
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
                onclick="${followship ? 'unfollow' : 'follow'}(this, '${itemId}'${
            attrs.callback ? ", ${attrs.callback}" : ''
        });">
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

    def tagSearch = {

        def config = [
                [
                        id   : 'symbol',
                        title: message(code: 'globalSearch.symbol'),
                        link : createLink(controller: 'symbol', action: 'hashTagSearch')
                ],
                [
                        id   : 'index',
                        title: message(code: 'globalSearch.index'),
                        link : createLink(controller: 'index', action: 'hashTagSearch')
                ],
                [
                        id   : 'currency',
                        title: message(code: 'globalSearch.currency'),
                        link : createLink(controller: 'currency', action: 'hashTagSearch')
                ],
                [
                        id   : 'coin',
                        title: message(code: 'globalSearch.coin'),
                        link : createLink(controller: 'coin', action: 'hashTagSearch')
                ],
                [
                        id   : 'metal',
                        title: message(code: 'globalSearch.metal'),
                        link : createLink(controller: 'metal', action: 'hashTagSearch')
                ],
                [
                        id   : 'oil',
                        title: message(code: 'globalSearch.oil'),
                        link : createLink(controller: 'oil', action: 'hashTagSearch')
                ],
                [
                        id   : 'future',
                        title: message(code: 'globalSearch.future'),
                        link : createLink(controller: 'future', action: 'hashTagSearch')
                ]
        ]

        out << """
        <div id="tagSearchResults" class="k-rtl">
            <div id="tagSearchResultsTab">
                <ul>
                    <li class='k-state-active'>
                        ${message(code: 'all')}
                    </li>
"""
        config.each { item ->
            out << """
                    <li>
                    ${item.title}
                    </li>
"""
        }
        out << """
                </ul>
"""

        out << """
                <div>
                    <div id="tagSearchResult_all" class="tagSearchResult">
"""
        out << form.loading()
        out << """
                    </div>
                </div>
"""
        config.each {
            out << """
                <div>
                    <div id="tagSearchResult_${it.id}" class="tagSearchResult">
"""
            out << form.loading()
            out << """
                    </div>
                </div>
"""
        }

        out << """
            </div>
        </div>
        <script language='javascript' type='text/javascript'>
"""
        config.each { item ->
            out << """
            var tagSearchResultLoaded_${item.id} = false;
            var tagSearchResultList_${item.id} = [];
"""
        }

        out << """
            var lastGlobalTagSearchTerm;
            var currentHashTagEditor;
            function showTagSearchResults(ed, phrase){
                currentHashTagEditor = ed;
                var resultsPane = \$('#tagSearchResults');
                if(phrase == lastGlobalTagSearchTerm)
                    return;
                lastGlobalTagSearchTerm = phrase;
                \$('#tagSearchResult_all').find('.loading').show();
"""

        config.each { item ->
            out << """
                tagSearchResultLoaded_${item.id} = false;
                \$('#tagSearchResult_${item.id}').find('.loading').show();
                \$.ajax({
                    type: "POST",
                    url: '${item.link}',
                    data: {term: phrase}
                }).done(function (response) {
                    var container = \$('#tagSearchResult_${item.id}');
                    container.html('');
                    var indexer_${item.id} = 0;
                    \$.each(response, function(){
                        container.append('<a data-tag=\\'' + this.tag + '\\' data-link=\\'' + this.link + '\\' data-typeClass=\\'' + this.typeClass + '\\' data-id=\\'' + this.id + '\\' href="javascript:insertHashTag(\\'' + this.tag + '\\', \\'' + this.link + '\\', \\'' + this.typeClass + '\\', \\'' + this.id + '\\')" class="' + (indexer_${
                item.id
            } == 0 ? 'k-state-active' : '') + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        indexer_${item.id}++;
                    });
                    if(response.length == 0){
                        container.html('<span class="noSearchResult">${message(code: 'tagSearch.noResult')}</span>');
                    }
                    tagSearchResultList_${item.id} = response;
                    tagSearchResultLoaded_${item.id} = true;
"""
            out << """
                    if(${config.collect { "tagSearchResultLoaded_${it.id}" }.join(' && ')}){
                        var total = [];
"""
            out << config.collect {
                """
                    total = total.concat(tagSearchResultList_${it.id});
"""
            }.join('\r\n')
            out << """
                    total.sort(compareSearchResults)
                    var allContainer = \$('#tagSearchResult_all');
                    allContainer.html('')
                    var totalIndexer = 0;
                    \$.each(total, function(){
                        allContainer.append('<a data-tag=\\'' + this.tag + '\\' data-link=\\'' + this.link + '\\' data-typeClass=\\'' + this.typeClass + '\\' data-id=\\'' + this.id + '\\' href="javascript:insertHashTag(\\'' + this.tag + '\\', \\'' + this.link + '\\', \\'' + this.typeClass + '\\', \\'' + this.id + '\\')" class="' + (totalIndexer == 0 ? 'k-state-active' : '') + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        totalIndexer++;
                    });
                    if(total.length == 0){
                        allContainer.html('<span class="noSearchResult">${message(code: 'tagSearch.noResult')}</span>');
                            }
                        }
                    });
"""
        }

        out << """
            }
            \$(document).ready(function () {
                \$("#tagSearchResultsTab").kendoTabStrip();
            });
        </script>
"""
    }

    def authorSearch = {

        def config = [
                [
                        id   : 'author',
                        title: message(code: 'globalSearch.author'),
                        link : createLink(controller: 'twitter', action: 'globalAuthorSearch')
                ],
//                [
//                        id   : 'group',
//                        title: message(code: 'globalSearch.group'),
//                        link : createLink(controller: 'twitter', action: 'globalGroupSearch')
//                ]
        ]

        out << """
        <div id="authorSearchResults" class="k-rtl">
            <div id="authorSearchResultsTab">
                <ul>
                    <li class='k-state-active'>
                        ${message(code: 'all')}
                    </li>
"""
        config.each { item ->
            out << """
                    <li>
                    ${item.title}
                    </li>
"""
        }
        out << """
                </ul>
"""

        out << """
                <div>
                    <div id="authorSearchResult_all" class="authorSearchResult">
"""
        out << form.loading()
        out << """
                    </div>
                </div>
"""
        config.each {
            out << """
                <div>
                    <div id="authorSearchResult_${it.id}" class="authorSearchResult">
"""
            out << form.loading()
            out << """
                    </div>
                </div>
"""
        }

        out << """
            </div>
        </div>
        <script language='javascript' type='text/javascript'>
"""
        config.each { item ->
            out << """
            var authorSearchResultLoaded_${item.id} = false;
            var authorSearchResultList_${item.id} = [];
"""
        }

        out << """
            var lastGlobalAuthorSearchTerm;
            var currentHashAuthorEditor;
            function showAuthorSearchResults(ed, phrase){
                currentHashAuthorEditor = ed;
                var resultsPane = \$('#authorSearchResults');
                if(phrase == lastGlobalAuthorSearchTerm)
                    return;
                lastGlobalAuthorSearchTerm = phrase;
                \$('#authorSearchResult_all').find('.loading').show();
"""

        config.each { item ->
            out << """
                authorSearchResultLoaded_${item.id} = false;
                \$('#authorSearchResult_${item.id}').find('.loading').show();
                \$.ajax({
                    type: "POST",
                    url: '${item.link}',
                    data: {term: phrase}
                }).done(function (response) {
                    var container = \$('#authorSearchResult_${item.id}');
                    container.html('');
                    var indexer_${item.id} = 0;
                    \$.each(response, function(){
                        container.append('<a data-author=\\'' + this.author + '\\' data-link=\\'' + this.link + '\\' data-typeClass=\\'' + this.typeClass + '\\' data-id=\\'' + this.id + '\\' href="javascript:insertHashAuthor(\\'' + this.author + '\\', \\'' + this.link + '\\', \\'' + this.typeClass + '\\', \\'' + this.id + '\\')" class="' + (indexer_${
                item.id
            } == 0 ? 'k-state-active' : '') + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        indexer_${item.id}++;
                    });
                    if(response.length == 0){
                        container.html('<span class="noSearchResult">${message(code: 'authorSearch.noResult')}</span>');
                    }
                    authorSearchResultList_${item.id} = response;
                    authorSearchResultLoaded_${item.id} = true;
"""
            out << """
                    if(${config.collect { "authorSearchResultLoaded_${it.id}" }.join(' && ')}){
                        var total = [];
"""
            out << config.collect {
                """
                    total = total.concat(authorSearchResultList_${it.id});
"""
            }.join('\r\n')
            out << """
                    total.sort(compareSearchResults)
                    var allContainer = \$('#authorSearchResult_all');
                    allContainer.html('')
                    var totalIndexer = 0;
                    \$.each(total, function(){
                        allContainer.append('<a data-author=\\'' + this.author + '\\' data-link=\\'' + this.link + '\\' data-typeClass=\\'' + this.typeClass + '\\' data-id=\\'' + this.id + '\\' href="javascript:insertHashAuthor(\\'' + this.author + '\\', \\'' + this.link + '\\', \\'' + this.typeClass + '\\', \\'' + this.id + '\\')" class="' + (totalIndexer == 0 ? 'k-state-active' : '') + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        totalIndexer++;
                    });
                    if(total.length == 0){
                        allContainer.html('<span class="noSearchResult">${message(code: 'authorSearch.noResult')}</span>');
                            }
                        }
                    });
"""
        }

        out << """
            }
            \$(document).ready(function () {
                \$("#authorSearchResultsTab").kendoTabStrip();
            });
        </script>
"""
    }

}
