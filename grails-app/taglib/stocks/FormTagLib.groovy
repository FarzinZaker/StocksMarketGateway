package stocks

import grails.converters.JSON

class FormTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def springSecurityService

    static namespace = "form"

    def form = { attrs, body ->
        if (!attrs.name)
            throw new Exception("No name is specified for form tag")
        out << g.form(attrs, body)
        out << """
            <script language="javascript" type="text/javascript">
                \$(document).ready(function () {
                    \$.validate({
                        form: '#${attrs.name}'
                    });
                });
            </script>
"""
    }

    def field = { attrs, body ->
        out << """

            <div class='form-field ${attrs.border == '0' ? 'borderless' : ''}'>
                <div class='main'>
                    ${
            (attrs.fieldName || attrs.label) && attrs.showLabel != '0' ? "<label>${attrs.label ?: message(code: "${attrs.fieldName}.label")}</label>" : ''
        }
                    """
        out << body()
        out << """
                </div>
"""
        if (attrs.showHelp != '0')
            out << """
                <div class='help'>
                    ${attrs.fieldName ? "<label>${message(code: "${attrs.fieldName}.help")}</label>" : ''}
                </div>
"""
        out << """
                <div class='clear-fix'></div>
            </div>
"""
    }

    def hidden = { attrs, body ->
        out << """
            <input type='hidden' id='${attrs.id ?: attrs.name}' name='${attrs.name}' value='${
            attrs.value ? attrs.value : (attrs.entity?."${attrs.name}" ?: '')
        }' />
"""
    }

    def textBox = { attrs, body ->
        out << """
            <input type='${attrs.type ?: 'text'}' class='k-textbox ${attrs.class ?: ''}' ${
            attrs.style ? "style='${attrs.style}'" : ''
        } name='${attrs.name}' id='${attrs.id ?: attrs.name}' value='${
            attrs.value ?: attrs.entity?."${attrs.name}" ?: ''
        }'
                ${attrs.placeholder ? "placeholder='${attrs.placeholder}'" : ''} data-validation="${
            attrs.validation ?: ''
        }"
            ${attrs."ng-model" ? "ng-model=${attrs."ng-model"}" : ''}
            ${attrs."ng-keyup" ? "ng-keyup=${attrs."ng-keyup"}" : ''} />
"""
    }

    def numericTextBox = { attrs, body ->
        out << """
            <span class='k-rtl'>
                <input type='${attrs.type ?: 'text'}' class='number ${attrs.class ?: ''}' ${
            attrs.style ? "style='${attrs.style}'" : ''
        } name='${attrs.name}' id='${attrs.id ?: attrs.name}' value='${
            attrs.value != null ? attrs.value : attrs.entity?."${attrs.name}" ?: ''
        }'
                    ${attrs.placeholder ? "placeholder='${attrs.placeholder}'" : ''} data-validation="${
            attrs.validation ?: ''
        }"
                ${attrs."ng-model" ? "ng-model=${attrs."ng-model"}" : ''} />
            </span>
"""

        out << """
            <script language='javascript' type='text/javascript'>
                \$(document).ready(function(){
                    \$('#${attrs.id ?: attrs.name}').kendoNumericTextBox({
                        decimals: 0
                    });
                });
            </script>
"""

    }

    def textArea = { attrs, body ->
        out << """
            <textarea class='k-textbox ${attrs.class ?: ''}' ${
            attrs.style ? "style='${attrs.style}'" : ''
        } name='${attrs.name}' id='${attrs.id ?: attrs.name}'
                data-validation="${attrs.validation ?: ''}" >${attrs.entity?."${attrs.name}" ?: ''}</textarea>
"""
    }

    def password = { attrs, body ->
        out << textBox(attrs + [type: 'password'], body)
    }

    def checkbox = { attrs, body ->
        out << """
                <input type="checkbox" class="css-checkbox" id="${attrs.id ?: attrs.name}" name="${attrs.name}"
                    ${attrs.checked ? "checked='checked'" : ''} ${attrs.onchange ? "onchange='${attrs.onchange}'" : ''} />
                <label class="css-label" for="${attrs.id ?: attrs.name}" style="${attrs.style}">
                    ${attrs.text}
                </label>
"""
    }

    def select = { attrs, body ->

        out << """
            <span class="k-rtl">
                <input class="k-textbox" name='${attrs.name}' id="${attrs.id ?: attrs.name}" ${
            attrs.style ? "style='${attrs.style}'" : ''
        } data-validation="${attrs.validation ?: ''}" ${attrs."ng-model" ? "ng-model=${attrs."ng-model"}" : ''} ${
            attrs.value ? "value='${attrs.value}'" : ''
        } />
            </span>
            <script>
                \$(document).ready(function() {
                    var data = ${attrs.items as JSON};

                    \$("input[name=${attrs.name}]").removeClass('k-textbox').kendoComboBox({
                        dataTextField: "text",
                        dataValueField: "value",
                        dataSource: data,
"""
        if (!attrs.preSelect || attrs.preSelect == 'true')
            out << """
                        index: 0,
"""
        out << """
                        suggest:${attrs.suggest ?: 'false'},
                        change : function (e) {
                            ${attrs.onchange ? "${attrs.onchange}(e);" : ''}
"""
        if (attrs.allowUserInput != 'true')
            out << """
                            if (this.value() && this.selectedIndex == -1) {
                                var dt = this.dataSource._data[0];
                                this.text(dt[this.options.dataTextField]);
                                this._selectItem();
                            }
"""
        if (attrs.valueValidate == 'numeric')
            out << """
                            if(this.value() && this.selectedIndex == -1 && !\$.isNumeric(this.value())){
                                this.value('0');
                            }
"""
        out << """
                        }
                    });
                });
            </script>
"""
    }

    def searchBox = { attrs, body ->
        out << """
            <div class="searchbox k-textbox ${attrs.class ?: ''}" id="${attrs.id ? attrs.id : attrs.name}_container">
                <input type='text' data-role="searchbox" name="${attrs.name}" id="${
            attrs.id ? attrs.id : attrs.name
        }" placeholder="${message(code: 'search')}"/>
                <a class="k-icon k-i-search k-search" href="#"></a>
            </div>
            <script language="javascript" type="text/javascript">
                \$("#${attrs.id ? attrs.id : attrs.name}_container input").keypress(function(e){if (e.keyCode == 13) {${
            attrs.action
        }(); return false;}});
                \$("#${attrs.id ? attrs.id : attrs.name}_container a").click(function(){${attrs.action}();return false;});
            </script>
"""
    }

    def editor = { attrs, body ->
        if (!attrs.ajax) {
            out << asset.javascript(src: 'tinymce/tinymce.min.js')
            out << asset.javascript(src: 'tinymce/jquery.tinymce.min.js')
        }
        out << """
            <div class='k-rtl'>
                <textarea ${
            attrs.class ? "class='${attrs.class}'" : ''
        } style='color:transparent;background-color:transparent;border-width:0;${
            attrs.width ? "width:${attrs.width}px;" : ''
        }${attrs.style ?: ''}' name='${attrs.name}' id='${attrs.id ?: attrs.name}'  data-validation="${
            attrs.validation ?: ''
        }">
                    ${attrs.entity?."${attrs.name}" ?: ''}
                </textarea>
            </div>
            <script language='javascript' type='text/javascript'>
                function getKendoImageBrowserUrl(){
                    return '${createLink(controller: 'file', action: 'imageBrowser', absolute: true)}';
                }
                \$(document).ready(function() {
                    tinymce.init({
                        ${attrs.mode != 'full' ? 'menubar:false,' : ''}
                        selector: "#${attrs.id ?: attrs.name}",
                        language: "fa",
                        content_css : "${asset.assetPath(src: 'editor.css')}",
                        statusbar : false,
                        ${attrs.width ? "width:${attrs.width}," : ""}
                        ${attrs.height ? "height:${attrs.height}," : ""}
                        plugins: [
                            "kendoImageBrowser ${
            attrs.mode == 'full' ? 'advlist' : ''
        } autolink link image lists charmap hr anchor ${attrs.height ? '' : 'autoresize'}",
                            "searchreplace wordcount fullscreen",
                            "save table contextmenu directionality paste textcolor hr"
                        ],
                        toolbar: "${
            attrs.mode == 'full' ? 'redo undo | styleselect' : ''
        } | italic bold | alignjustify alignleft aligncenter alignright | indent outdent ${
            attrs.mode == 'full' ? '| hr' : ''
        } | numlist | bullist ${attrs.mode != 'simple' ? '| image link' : ''} ${
            attrs.mode == 'full' ? '| forecolor | backcolor' : ''
        }"
                    });
                });
            </script>
"""
    }

    def imageUpload = { attrs, body ->
        def image = attrs.entity?."${attrs.name}"
        out << """
            <div class='k-rtl'>
                <div id='${attrs.id ?: attrs.name}_container' ${attrs.class ? "class='${attrs.class}'" : ''} ${
            attrs.style ? "style='${attrs.style}'" : ''
        }>
                    <input type='hidden' name='${attrs.name}.id' id='${attrs.id ?: attrs.name}_hidden' ${
            image ? "value='${image?.id}'" : ''
        } />
                    <input type='file' name='${attrs.name}' id='${attrs.id ?: attrs.name}' />
                </div>
            </div>
            <script language='javascript' type='text/javascript'>
                \$(document).ready(function() {
                    \$("#${attrs.id ?: attrs.name}").kendoUpload({
                        async: {
                           saveUrl: '${attrs.saveUrl}?name=${attrs.name}',
                           autoUpload: true
                        },
                        select: function (e) {
                            \$('#${attrs.id ?: attrs.name}_container .k-file').remove();
                            var fileReader = new FileReader();
                            fileReader.onload = function (event) {
                            \$('#${attrs.id ?: attrs.name}_container .k-file .k-progress').remove();
                                console.log(event);
                                var mapImage = event.target.result;
                                \$('#${
            attrs.id ?: attrs.name
        }_container .k-file .k-icon').replaceWith("<img class='upload-thumbnail' id='${attrs.id ?: attrs.name}_preview'/>");
                                \$("#${attrs.id ?: attrs.name}_preview").attr('src', mapImage);

                            }
                            fileReader.readAsDataURL(e.files[0].rawFile);

                        },

                        multiple: false,
                        success: function (e) {
                            \$("#${attrs.id ?: attrs.name}_hidden").attr('value', e.response);
                        }
                    });
"""
        if (image)
            out << """
            \$('#${
                attrs.name
            }_container .k-widget.k-upload.k-header').append('<ul class="k-upload-files k-reset"><li class="k-file k-file-success"><img id="image_preview" class="upload-thumbnail" src="${
                createLink(controller: 'image', action: 'index', id: image?.id)
            }"/><span title="${image.name}" class="k-filename">1403890017_basics-22.png</span><strong class="k-upload-status"><span class="k-upload-pct">100%</span><button class="k-button k-button-bare k-upload-action" type="button" style="display: none;"></button></strong></li></ul>');
"""
        out << """
                });
            </script>
"""
    }

    def multiSelect = { attrs, body ->

        out << asset.stylesheet(src: 'tag-it.css')
        out << asset.stylesheet(src: 'tag-it.ui.css')
        out << asset.javascript(src: 'jquery.ui/autocomplete.min.js')
        out << asset.javascript(src: 'tag-it.min.js')

        out << """
            <div class='k-rtl k-reset ${attrs.class ?: ''}' ${attrs.style ? "style='${attrs.style}'" : ''}>
                <input id="${attrs.id ?: attrs.name}"/>
            </div>
            <script language='javascript' type='text/javascript'>
                \$(document).ready(function(){
                    \$("#${attrs.id ?: attrs.name}").attr('value', '${
            attrs.entity?."${attrs.name}"?.collect { it?.name }?.join(',') ?: ''
        }');
                    \$("#${attrs.id ?: attrs.name}").tagit({
                        fieldName: '${attrs.name}',
                        singleField: true,
                        singleFieldNode: \$('#mySingleField'),
                        allowSpaces: true,
                        minLength: 2,
                        removeConfirmation: true,
                        tagSource: function( request, response ) {
                            //console.log("1");
                            \$.ajax({
                                url: "${attrs.dataSourceUrl}",
                                data: { term:request.term },
                                dataType: "json",
                                success: function( data ) {
                                    response( \$.map( data, function( item ) {
                                        return {
                                            label: item.name,
                                            value: item.name
                                        }
                                    }));
                                }
                            });
                        }
                    });
                    \$("#${attrs.id ?: attrs.name}").attr('name', '${attrs.id ?: attrs.name}');
                });
            </script>
"""
    }

    def fillRecordChildren(root, domainClass, relationProperty, titleProperty, selectedId) {
        def recordList
        if (root)
            recordList = domainClass.clazz.createCriteria().list {
                eq("${relationProperty}.id", root.id)
            }
        else
            recordList = domainClass.clazz.createCriteria().list {
                isNull(relationProperty)
            }

        recordList.findAll { !it.deleted }.collect {
            [
                    id      : it.id,
                    text    : it.properties[titleProperty],
                    expanded: true,
                    selected: it.id == selectedId,
                    items   : fillRecordChildren(it, domainClass, relationProperty, titleProperty, selectedId)
            ]
        }
    }

    def treeCombo = { attrs, body ->
        def domainClass = grailsApplication.getDomainClass(attrs.domainClass)
        out << """
            <div class='k-rtl k-treeCombo'>
                <div id="${attrs.id ?: attrs.name}Tree"></div>

                <input id="${attrs.id ?: attrs.name}" name="${attrs.name}" ${
            attrs.style ? "style='${attrs.style}'" : ''
        } value="${attrs.value}"></input>
            </div>
            <script id="${attrs.id ?: attrs.name}-treeview-template" type="text/kendo-ui-template">
                <span class='treeNode-text'>#:item.text#</span><span class='treeNode-value'>#:item.id#</span>
            </script>
            <script type="text/javascript">
                \$(document).ready(function() {
                    var ${attrs.id ?: attrs.name}_dropdown = \$("#${attrs.id ?: attrs.name}").kendoDropDownList({
                        dataSource: [{ text: "${
            attrs.value > 0 ? domainClass.clazz.get(attrs.value)."${attrs.titleProperty}" : message(code: 'root')
        }", value: "${attrs.value}" }],
                        dataTextField: "text",
                        dataValueField: "value",
                        open: function (e) {
                            // If the treeview is not visible, then make it visible.
                            if (!\$treeviewRootElem.hasClass("k-custom-visible")) {
                            \$treeviewRootElem.css({ "top": \$${
            attrs.id ?: attrs.name
        }_dropdownRootElem.position().top + \$${attrs.id ?: attrs.name}_dropdownRootElem.height(), "right": \$${
            attrs.id ?: attrs.name
        }_dropdownRootElem.position().right });
                                \$treeviewRootElem.slideToggle('fast', function() {
                                    ${attrs.id ?: attrs.name}_dropdown.close();
                                    \$treeviewRootElem.addClass("k-custom-visible");
                                });
                            }
                        }
                    }).data("kendoDropDownList");
                    \$('#${attrs.id ?: attrs.name}-list').css('display', 'none').css('opacity', '0');
                    var \$${attrs.id ?: attrs.name}_dropdownRootElem = \$(${attrs.id ?: attrs.name}_dropdown.element).closest("span.k-dropdown");

                    var ${attrs.id ?: attrs.name}_treeview = \$("#${attrs.id ?: attrs.name}Tree").kendoTreeView({
                        template: kendo.template(\$("#${attrs.id ?: attrs.name}-treeview-template").html()),
                        dataSource: [{id: 0, text: '${message(code: 'root')}', expanded: true, selected: false, items:${
            (fillRecordChildren(null, domainClass, attrs.relationProperty, attrs.titleProperty, attrs.value as Long)) as JSON
        }}],
                        select: function (e) {
                            // When a node is selected, display the text for the node in the dropdown and hide the treeview.
                            \$${attrs.id ?: attrs.name}_dropdownRootElem.find("span.k-input").text(\$(e.node).children("div").find(".treeNode-text").text());
                            \$("#${attrs.id ?: attrs.name}").val(\$(e.node).children("div").find(".treeNode-value").text());
                            ${attrs.onchange ? "${attrs.onchange}();" : ''}
                            \$treeviewRootElem.slideToggle('fast', function() {
                                \$treeviewRootElem.removeClass("k-custom-visible");
                            });
                        }
                    }).data("kendoTreeView");
                    ${attrs.id ?: attrs.name}_treeview.select(${attrs.id ?: attrs.name}_treeview.findByUid(${
            attrs.id ?: attrs.name
        }_treeview.dataSource.get(${attrs.value}).uid));
                    var \$treeviewRootElem = \$(${attrs.id ?: attrs.name}_treeview.element).closest("div.k-treeview");
                    \$treeviewRootElem
                        .width(\$${attrs.id ?: attrs.name}_dropdownRootElem.width() - 2)
                        .css({ "display": "none", "position": "absolute" });
                    \$treeviewRootElem
                        .css({ "top": \$${attrs.id ?: attrs.name}_dropdownRootElem.position().top + \$${
            attrs.id ?: attrs.name
        }_dropdownRootElem.height(), "right": \$${attrs.id ?: attrs.name}_dropdownRootElem.position().right });

                    \$(document).click(function(e) {
                        // Ignore clicks on the treetriew.
                        if (\$(e.target).closest("div.k-treeview").length == 0) {
                            if (\$treeviewRootElem.hasClass("k-custom-visible")) {
                                \$treeviewRootElem.slideToggle('fast', function() {
                                    \$treeviewRootElem.removeClass("k-custom-visible");
                                });
                            }
                        }
                    });
                });
            </script>
"""
    }

    def datePicker = { attrs, body ->
        out << "<link rel='stylesheet' type='text/css' href='${resource(dir: "css/bootstrap/datepicker", file: "bootstrap-datepicker.css")}'/>"
        out << "<script language='javascript' type='text/javascript' src='${resource(dir: "js/bootstrap/datepicker", file: "bootstrap-datepicker.js")}'></script>"
        out << "<script language='javascript' type='text/javascript' src='${resource(dir: "js/bootstrap/datepicker", file: "bootstrap-datepicker.fa.js")}'></script>"

        out << textBox(attrs, body)

        out << """
            <script language='javascript' type='text/javascript'>
                \$(document).ready(function(){
                    \$('#${attrs.id ?: attrs.name}').datepicker({
"""
        if (attrs.direction == 'up')
            out << """
                beforeShow: function (textbox, instance) {
                       instance.dpDiv.css({
                           marginTop: (-textbox.offsetHeight) + 'px',
                           marginLeft: textbox.offsetWidth + 'px'
                       });
                   }
"""
        out << """
                    });
                });
            </script>
"""

    }

    def timePicker = { attrs, body ->
        out << """
            <input id="${attrs.id ?: attrs.name}" name="${attrs.name}" ${attrs.value ? "value='${attrs.value}'" : ''}/>
            <script language="javascript" type="text/javascript">
                \$(document).ready(function(){
                    \$("#${attrs.id ?: attrs.name}").kendoTimePicker({
"""
        if (attrs.interval)
            out << """
                        interval: ${attrs.interval}
"""
        if (attrs.min)
            out << """
                        interval: ${attrs.min}
"""
        if (attrs.max)
            out << """
                        interval: ${attrs.max}
"""
        out << """
                    });
                });
            </script>
"""
    }

    def timeRangeSlider = { attrs, body ->
        out << """
            <div id="${attrs.id ?: attrs.name}" class="timeSlider timeSlider${attrs.id ?: attrs.name}" style="${
            attrs.style ?: ''
        }">
                <input name="${attrs.name}Start" ${attrs.rangeStart ? "value='${attrs.rangeStart}'" : ''}/>
                <input name="${attrs.name}End" ${attrs.rangeEnd ? "value='${attrs.rangeEnd}'" : ''}/>
            </div>

            <script language="javascript" type="text/javascript">
                \$(document).ready(function () {
                    var templateString = "# return (Math.floor(selectionStart / 60)) + ':' + (selectionStart % 60 > 0 ? (selectionStart % 60) : '00') + '&nbsp;&nbsp;${
            message(code: 'timeRange.till')
        }&nbsp;&nbsp;' + (Math.floor(selectionEnd / 60)) + ':' + (selectionEnd % 60 > 0 ? (selectionEnd % 60) : '00') #";
                    var slider = \$("#${attrs.id ?: attrs.name}").kendoRangeSlider({
                        min: ${attrs.min ?: 0},
                        max: ${attrs.max ?: 24 * 60},
                        smallStep: 30,
                        largeStep: 60,
                        tickPlacement: "both",
                        tooltip: {
                            template: kendo.template(templateString)
                        },
"""
        if (attrs.childTimeRangeSlider)
            out << """
                        change:rangeSliderOnChange_${attrs.id ?: attrs.name}

"""
        out << """
                    });

                    \$.each(\$('.timeSlider${attrs.id ?: attrs.name} .k-tick .k-label'), function(index, item){
                        if(index < 24) {
                            var value = parseInt(\$(item).text().replace(',', ''));
//                            \$(item).text((Math.floor(value / 60)) + ':' + (value % 60 > 0 ? (value % 60) : '00'));
                            \$(item).text(Math.floor(value / 60))
                        }
                    });
                });
            </script>
"""
    }

    def captcha = { attrs, body ->
        if (!attrs.style)
            attrs.style = ""
        attrs.style = "width:${attrs.width.toInteger() - 130}px;" + attrs.style
        out << textBox(attrs, body)
        out << """
            <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}" width='125px'/>
"""
    }

    def button = { attrs, body ->
        out << """
            <span name="${attrs.name}" id="${attrs.id ?: attrs.name}" class="k-button ${attrs.class}"
                 ${attrs."ng-click" ? "ng-click='${attrs."ng-click"}'" : ''}
                ${attrs.onclick ? "onclick='${attrs.onclick}'" : ''}>${attrs.text}</span>
"""
    }

    def linkButton = { attrs, body ->
        out << """
            <a name="${attrs.name}" id="${attrs.id ?: attrs.name}" class="k-button ${attrs.class}" ${
            attrs.href ? "href='${attrs.href}'" : ''
        }>${attrs.text}</a>
            <script language="javascript" type="text/javscript">
                \$(document).ready(function(){
                    \$('#${attrs.id ?: attrs.name}').kendoButton();
                });
            </script>
"""
    }

    def submitButton = { attrs, body ->
        out << """
            <input type="submit" name="${attrs.name}" id="${attrs.id ?: attrs.name}" class="k-button ${
            attrs.class
        }" value="${
            attrs.text
        }"></input>
            <script language="javascript" type="text/javscript">
                \$(document).ready(function(){
                    \$('#${attrs.id ?: attrs.name}').kendoButton();
                });
            </script>
"""
    }

    def loading = { attrs, body ->
        out << """
            <span class="loading" id="${attrs.id}">
"""
        out << asset.image(src: "loading.gif")
        out << """
                <span>${message(code: 'wait')}</span>
            </span>
"""
    }

    def error = { attrs, body ->
        if (attrs.message)
            out << """
            <div class="errorMessage">
                ${attrs.message}
            </div>
"""
    }

    def info = { attrs, body ->
        if (attrs.message)
            out << """
            <div class="info">
                ${attrs.message}
            </div>
"""
    }
}
