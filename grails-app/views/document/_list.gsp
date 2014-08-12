
<div class="documentListContainer k-rtl">
    <form:searchBox name="search" action="searchList"/>
    <script language="javascript" type="text/javascript">
        function searchList() {
            var documentListView = $('#documentListView').data('kendoListView');
            documentListView.dataSource.read();   // added line
            documentListView.refresh();
        }
    </script>

    <div id="documentListView"></div>

    <div id="pager" class="k-pager-wrap"></div>
</div>

<script type="text/x-kendo-tmpl" id="documentTemplate">
    <div class="document" item-id="#:itemId#" >
        <span class='image'><img src="${createLink(controller: 'image', action: 'index')}/#:imageId#?name=#:imageName#&size=250" alt="#:title#" /></span>
        <h3>#:title#</h3>
        <span class='date'>#:date#</span>
        <p>#:summary#</p>
    </div>
</script>

<script>
    $(document).ready(function () {
        var dataSource = new kendo.data.DataSource({
            transport: {
                type: 'odata',
                read: {
                    url: "${dataServiceUrl}",
                    dataType: "json",
                    type: "POST"

                },
                parameterMap: function (data, action) {
                    if (action === "read") {
                        data.search = $('#search').val();
                        return data;
                    } else {
                        return data;
                    }
                }
            },
            schema: {
                data: "data", // records are returned in the "data" field of the response
                total: "total" // total number of records is in the "total" field of the response
            },
            serverPaging: true,
            pageSize: 8
        });

        $("#pager").kendoPager({
            dataSource: dataSource
        });

        $("#documentListView").kendoListView({
            dataSource: dataSource,
            selectable: "single",
            template: kendo.template($("#documentTemplate").html()),
            dataBound: function () {
                $('#documentListView').find('.document p').dotdotdot({
                    ellipsis: '... ',
                    wrap: 'word',
                    fallbackToLetter: true,
                    after: null,
                    watch: false,
                    height: 90,
                    tolerance: 0,
                    callback: function (isTruncated, orgContent) {
                    },
                    lastCharacter: {
                        remove: [ ' ', ',', ';', '.', '!', '?' ],
                        noEllipsis: []
                    }
                });
            }
        });

    });
</script>

<div class="toolbar">
    <input id="btn-createDocument" type="button" value="${message(code: "${entityName}.create.label")}"
           class="k-button"/>
    <input id="btn-viewDocument" type="button" value="${message(code: "${entityName}.view.label")}"
           class="k-button"/>
    <input id="btn-editDocument" type="button" value="${message(code: "${entityName}.edit.label")}"
           class="k-button"/>
    <input id="btn-deleteDocument" type="button" value="${message(code: "${entityName}.delete.label")}"
           class="k-button"/>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#btn-createDocument').click(function () {
            window.location.href = "${createLink(action: 'create')}";
        });
        $('#btn-viewDocument').click(function () {
            if (checkSelection())
                window.location.href = "${createLink(action: 'thread')}/" + $('#documentListView').find('.document.k-state-selected').attr('item-id');
        });

        $('#btn-editDocument').click(function () {
            if (checkSelection())
                window.location.href = "${createLink(action: 'edit')}/" + $('#documentListView').find('.document.k-state-selected').attr('item-id');
        });

        $('#btn-deleteDocument').click(function () {
            if (checkSelection())
                confirm('${message(code:'article.delete.confirm')}', deleteArticle);
        });

        function checkSelection() {
            if ($('#documentListView').find('.document.k-state-selected').length > 0)
                return true;
            else {
                window.alert('${message(code:'article.select.error')}');
                return false;
            }
        }

        function deleteArticle() {
            var id = $('#documentListView').find('.document.k-state-selected').attr('item-id');
            $.ajax({
                type: "POST",
                url: '${createLink(action: 'delete')}',
                data: { id: id }
            }).done(function (response) {
                if (response == "1") {
                    var documentListView = $('#documentListView').data('kendoListView');
                    documentListView.dataSource.read();   // added line
                    documentListView.refresh();
                }
                else {
                    window.alert('${message(code:'article.delete.error')}');
                }
            });
        }
    });
</script>