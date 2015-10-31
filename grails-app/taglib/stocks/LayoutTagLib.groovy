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

        out << """
            <div class="symbolSearchBox">
                <input id="symbolSearch" placeholder="${message(code: 'symbolSearch.placeHolder')}"/>

                <div id="symbolSearchResults" style="display: none;">
                    <div id="symbolSearchResultsTab">
                        <ul>
                            <li class='k-state-active'>
                                ${message(code: 'all')}
                            </li>
"""
        config.eachWithIndex { item, index ->
            out << """
                            <li>
                                ${item.title}
                            </li>
"""
        }
        out << """
                        </ul>
                        <div>
                            <div id="symbolSearchResult_all" class="symbolSearchResult">
"""
        out << form.loading()
        out << """
                            </div>
                        </div>
"""
        config.each {
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
                </div>
            </div>

            <script language="javascript" type="text/javascript">
"""
        config.each {
            out << """
                var globalSearchResultLoaded_${it.id} = false;
                var globalSearchResultList_${it.id} = [];
"""
        }

        out << """
                var lastGlobalSearchTerm;
                function showSymbolSearchResults(){
                    var searchBox = \$("#symbolSearch");
                    var resultsPane = \$('#symbolSearchResults');
                    resultsPane.css('top', searchBox.offset().top + 35).css('left', searchBox.offset().left).width(searchBox.width() + 200).fadeIn(200).slideDown();
                    if(searchBox.val() == lastGlobalSearchTerm)
                        return;
                    lastGlobalSearchTerm = searchBox.val();
                    \$('#symbolSearchResult_all').find('.loading').show();
"""

        config.each {
            out << """
                    globalSearchResultLoaded_${it.id} = false;
                    \$('#symbolSearchResult_${it.id}').find('.loading').show();
                    \$.ajax({
                        type: "POST",
                        url: '${it.link}',
                        data: {term: \$("#symbolSearch").val()}
                    }).done(function (response) {
                        var container = \$('#symbolSearchResult_${it.id}');
                        container.html('')
                        \$.each(response, function(){
                            container.append('<a href="' + this.link + '">' + this.text + ' <span>' + this.type + '</span></a>');
                        });
                        globalSearchResultList_${it.id} = response;
                        globalSearchResultLoaded_${it.id} = true;

                        if(${config.collect { "globalSearchResultLoaded_${it.id}" }.join(' && ')}){
                            var total = [];
"""
            config.each {
                out << """
                            total = total.concat(globalSearchResultList_${it.id});
"""
            }
            out << """
                            total.sort(compareSearchResults)
                            var allContainer = \$('#symbolSearchResult_all');
                            allContainer.html('')
                            \$.each(total, function(){
                                allContainer.append('<a href="' + this.link + '">' + this.text + ' <span>' + this.type + '</span></a>');
                            });
                        }
                    });
"""
        }

        out << """
                }

                \$(document).ready(function () {
                    \$("#symbolSearchResultsTab").kendoTabStrip();
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
