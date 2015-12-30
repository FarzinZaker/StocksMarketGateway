package stocks

class LayoutTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def springSecurityService

    static namespace = "layout"

    def panel = { attrs, body ->
        out << """
            <div class="panel ${attrs.class ?: ''}">
"""
        if (attrs.showHeader != 'no')
            out << """
                <${attrs.header ?: 'h2'}>${attrs.title ?: ''}</${attrs.header ?: 'h2'}>
"""
        out << """
                <div class="content ${attrs.contentClass ?: ''}">
                ${
            !attrs.loginRequiredAction || springSecurityService.loggedIn ? body() : loginMessage(attrs.loginRequiredAction)
        }
                </div>
            </div>
"""
    }

    def loginMessage(action) {
        """
            <div class="info">
                ${message(code: 'loginRequired.message', args: [action])}
                <div>
                    <a href="${createLink(controller: 'login', action: 'auth')}" class='k-button'>${
            message(code: 'login')
        }</a>
                    <a href="${createLink(controller: 'user', action: 'register')}" class='k-button'>${
            message(code: 'register')
        }</a>
                </div>
            </div>
"""
    }

    def breadcrumb = { attrs, body ->
        out << """
            <ul class="breadcrumb">
            """
        attrs.items.eachWithIndex { def item, int index ->
            out << """
                <li class="${index == attrs.items.size() - 1 ? 'current' : ''}">
                    <a class="${index == 0 ? 'home' : ''}" href="${item.url}" ><i class="${
                index == 0 ? 'fa fa-home' : ''
            }"></i>${item.text}</a>
                </li>
"""
        }
        out << """
            </ul>
"""
    }

    def searchBox = {

        def config = [
                [
                        id   : 'symbolOrIndex',
                        title: message(code: 'symbolOrIndex.title'),
                        items: [
                                [
                                        id   : 'symbol',
                                        title: message(code: 'globalSearch.symbol'),
                                        link : createLink(controller: 'symbol', action: 'globalSearch')
                                ],
                                [
                                        id   : 'index',
                                        title: message(code: 'globalSearch.index'),
                                        link : createLink(controller: 'index', action: 'globalSearch')
                                ],
                                [
                                        id   : 'investmentFund',
                                        title: message(code: 'globalSearch.investmentFund'),
                                        link : createLink(controller: 'symbol', action: 'globalInvestmentFundSearch')
                                ],
                                [
                                        id   : 'bonds',
                                        title: message(code: 'globalSearch.bonds'),
                                        link : createLink(controller: 'symbol', action: 'globalBondsSearch')
                                ],
                                [
                                        id   : 'housingFacility',
                                        title: message(code: 'globalSearch.housingFacility'),
                                        link : createLink(controller: 'symbol', action: 'globalHousingFacilitySearch')
                                ]
                        ]
                ],
                [
                        id   : 'twitter',
                        title: message(code: 'twitter.title'),
                        items: [
                                [
                                        id   : 'author',
                                        title: message(code: 'globalSearch.author'),
                                        link : createLink(controller: 'twitter', action: 'globalAuthorSearch')
                                ],
                                [
                                        id   : 'tag',
                                        title: message(code: 'globalSearch.tag'),
                                        link : createLink(controller: 'twitter', action: 'globalPropertySearch')
                                ],
                                [
                                        id   : 'material',
                                        title: message(code: 'article.menu.article'),
                                        link : createLink(controller: 'twitter', action: 'globalMaterialSearch')
                                ],
                                [
                                        id   : 'group',
                                        title: message(code: 'globalSearch.group'),
                                        link : createLink(controller: 'twitter', action: 'globalGroupSearch')
                                ]
                        ]
                ]
        ]

        out << """
                <input id="symbolSearch" placeholder="${message(code: 'symbolSearch.placeHolder')}"/>

                <div id="symbolSearchResults" style="display: none;">
                    <div id="symbolSearchResultsTab">
                        <ul>
"""
        config.eachWithIndex { group, index ->
            out << """
                            <li ${index == 0 ? 'class="k-state-active"' : ''}>
                                ${group.title}
                            </li>
"""
        }
        out << """</ul>"""
        config.each { group ->
            out << """
                        <div>
                            <div class="symbolSearchResult">
"""
//            start render children

            out << """
                                <div id="symbolSearchResultsTab_${group.id}">
                                    <ul>
                                        <li class='k-state-active'>
                                        ${message(code: 'all')}
                                        </li>
"""
            group.items.each { item ->
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
                                        <div id="symbolSearchResult_${group.id}_all" class="symbolSearchResult">
"""
            out << form.loading()
            out << """
                                        </div>
                                    </div>
"""
            group.items.each {
                out << """
                                    <div>
                                        <div id="symbolSearchResult_${it.id}" class="symbolSearchResult">
"""
                out << form.loading()
                out << """
                                        </div>
                                    </div>
"""
            }
            out << """
                                </div>
"""
//            end render children

            out << """
                            </div>
                        </div>
"""
        }

        out << """
                    </div>
                </div>

            <script language="javascript" type="text/javascript">
"""
        config.each { group ->
            it.items.each { item ->
                out << """
                var globalSearchResultLoaded_${item.id} = false;
                var globalSearchResultList_${item.id} = [];
"""
            }
        }

        out << """
                var lastGlobalSearchTerm;
                function showSymbolSearchResults(){
                    var searchBox = \$("#symbolSearch");
                    var resultsPane = \$('#symbolSearchResults');
                    resultsPane.css('top', 40).css('left', 0).css('z-index', 1000).width(searchBox.parent().width() + 8).fadeIn(200).slideDown();
                    if(searchBox.val() == lastGlobalSearchTerm)
                        return;
                    lastGlobalSearchTerm = searchBox.val();
"""
        config.each { group ->
            out << """
                    \$('#symbolSearchResult_${group.id}_all').find('.loading').show();
"""
        }

        config.eachWithIndex { group, index ->
            group.items.each { item ->
                out << """
                    globalSearchResultLoaded_${item.id} = false;
                    \$('#symbolSearchResult_${item.id}').find('.loading').show();
                    \$.ajax({
                        type: "POST",
                        url: '${item.link}',
                        data: {term: \$("#symbolSearch").val()}
                    }).done(function (response) {
                        var container = \$('#symbolSearchResult_${item.id}');
                        container.html('');
                        \$.each(response, function(){
                            container.append('<a href="' + this.link + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        });
                        if(response.length == 0){
                            container.html('<span class="noSearchResult">${message(code: 'globalSearch.noResult')}</span>');
                        }
                        globalSearchResultList_${item.id} = response;
                        globalSearchResultLoaded_${item.id} = true;
"""
                out << """
                        if(${group.items.collect { "globalSearchResultLoaded_${it.id}" }.join(' && ')}){
                            var total_${group.id} = [];
"""
                out << group.items.collect {
                    """
                            total_${group.id} = total_${group.id}.concat(globalSearchResultList_${it.id});
"""
                }.join('\r\n')
                out << """
                            total_${group.id}.sort(compareSearchResults)
                            var allContainer = \$('#symbolSearchResult_${group.id}_all');
                            allContainer.html('')
                            \$.each(total_${group.id}, function(){
                                allContainer.append('<a href="' + this.link + '">' + this.text + ' <span>' + this.type + '</span></a>');
                            });
                            if(total_${group.id}.length == 0){
                                allContainer.html('<span class="noSearchResult">${message(code: 'globalSearch.noResult')}</span>');
                            }
                        }
                    });
"""
            }
        }

        out << """
                }

                \$(document).ready(function () {
                    \$("#symbolSearchResultsTab").kendoTabStrip();
"""
        config.each { group ->
            out << """
                    \$("#symbolSearchResultsTab_${group.id}").kendoTabStrip();
"""
        }
        out << """
                    \$("#symbolSearch").keyup(showSymbolSearchResults).focus(showSymbolSearchResults);

                    \$(document).click(function (e) {
                        if (\$(e.target).is('#symbolSearchResults, #symbolSearchResults *, #symbolSearch'))return;
                        \$('#symbolSearchResults').fadeOut(200);
                    });

                });
            </script>
"""
    }
}
