package stocks

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import grails.converters.JSON

class TwitterTagLib {

    static namespace = "twitter"

    def commonGraphService
    def groupGraphService
    def springSecurityService
    def sharingService
    def graphDBService

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
}
