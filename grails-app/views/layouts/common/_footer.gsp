<div class="clear-fix"></div>

<a href="#" class="backToTop"><i class="fa fa-chevron-up"></i></a>

<div class="footerContainer">
    <div class="footer footer1">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 quickLinks">
                    <h2><g:message code="quickLinks.title"/></h2>
                    <ul>
                        <li>
                            <a href="${createLink(controller: 'user', action: 'home')}"><g:message
                                    code="quickLinks.twitter"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'article', action: 'create')}"><g:message
                                    code="quickLinks.newArticle"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'query', action: 'instanceList')}"><g:message
                                    code="quickLinks.alerting"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'portfolio', action: 'list')}"><g:message
                                    code="quickLinks.portfolio"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'screener', action: 'list')}"><g:message
                                    code="quickLinks.screener"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'screener', action: 'build')}"><g:message
                                    code="quickLinks.newScreener"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'tradeStrategy', action: 'list')}"><g:message
                                    code="quickLinks.backTest"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'tradeStrategy', action: 'build')}"><g:message
                                    code="quickLinks.newBackTest"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'report', action: 'heatMap')}"><g:message
                                    code="quickLinks.heatMap"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'tools', action: 'calculator')}"><g:message
                                    code="quickLinks.calculator"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'tools', action: 'correlation')}"><g:message
                                    code="quickLinks.correlation"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="footer footer2">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 footer-link-list first">
                    <h2><g:message code="about"/></h2>
                    <ul>
                        <li>
                            <a href="${createLink(controller: 'about', action: 'site')}"><g:message
                                    code="about.site"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'about', action: 'company')}"><g:message
                                    code="about.company"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'about', action: 'it')}"><g:message code="about.it"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'about', action: 'history')}"><g:message
                                    code="about.history"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'about', action: 'contact')}"><g:message
                                    code="about.contact"/></a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 footer-link-list">
                    <h2><g:message code="support"/></h2>
                    <ul>
                        <li>
                            <a href="${createLink(controller: 'help')}"><g:message code="support.help"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'screener')}"><g:message
                                    code="support.screener"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'tradeStrategy')}"><g:message
                                    code="support.backtest"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'alerting')}"><g:message
                                    code="support.alerting"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'portfolio')}"><g:message
                                    code="support.portfolio"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'calculator')}"><g:message
                                    code="support.calculator"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'correlation')}"><g:message
                                    code="support.correlation"/></a>
                        </li>
                        <li>
                            <a href="${createLink(controller: 'help', action: 'faq')}"><g:message
                                    code="support.faq"/></a>
                        </li>
                        <li>
                            <a href="javascript:openOnlineSupport()"><g:message code="support.online"/></a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-5">

                    <div class="row">
                        <div class="col-sm-6 linkList">
                            <h2><g:message code="brokers.title"/></h2>
                            <ul>
                                <li>
                                    <a href="${createLink(controller: 'brokersHelp', action: 'cooperationPlans')}"><g:message
                                            code="brokers.collaboration.plans"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'brokersHelp', action: 'cooperationRequest')}"><g:message
                                            code="brokers.collaboration.request"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'brokersHelp', action: 'bulkService')}"><g:message
                                            code="brokers.collaboration.buy"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'brokersHelp', action: 'cooperationRules')}"><g:message
                                            code="brokers.collaboration.rules"/></a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-sm-6 linkList">
                            <h2><g:message code="rules.title"/></h2>
                            <ul>
                                <li>
                                    <a href="${createLink(controller: 'rules', action: 'copyright')}"><g:message
                                            code="rules.copyRight"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'rules', action: 'rulesAndRegulations')}"><g:message
                                            code="rules.rulesAndRegulations"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'rules', action: 'privacy')}"><g:message
                                            code="rules.privacy"/></a>
                                </li>
                                <li>
                                    <a href="${createLink(controller: 'rules', action: 'criminalContentTabs')}"><g:message
                                            code="rules.ir"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col-sm-7 thirdParties">
                    <h3><g:message code="thirdParty.list"/></h3>

                    <div>
                        <div class="col-sm-2">
                            <a href="http://www.seo.ir" target="_blank">
                                <asset:image src="third-party/bourse.png"/>
                                <span><g:message code="thirdParty.bourse"/></span>
                            </a>
                        </div>

                        <div class="col-sm-2">
                            <a href="http://www.tsetmc.com" target="_blank">
                                <asset:image src="third-party/tse.png"/>
                                <span><g:message code="thirdParty.tse"/></span>
                            </a>
                        </div>

                        <div class="col-sm-2">
                            <a href="http://www.irenex.ir" target="_blank">
                                <asset:image src="third-party/energy.png"/>
                                <span><g:message code="thirdParty.energy"/></span>
                            </a>
                        </div>

                        <div class="col-sm-2">
                            <a href="http://www.ime.co.ir" target="_blank">
                                <asset:image src="third-party/ime.png"/>
                                <span><g:message code="thirdParty.ime"/></span>
                            </a>
                        </div>

                        <div class="col-sm-2">
                            <a href="http://www.tgju.org/" target="_blank">
                                <asset:image src="third-party/tgju.png"/>
                                <span><g:message code="thirdParty.tgju"/></span>
                            </a>
                        </div>

                        <div class="col-sm-2">
                            <a href="http://www.bloomberg.com" target="_blank">
                                <asset:image src="third-party/bloomberg.png"/>
                                <span><g:message code="thirdParty.bloomberg"/></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer footer3">
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <asset:image src="logo-white.png" class="logoWhite"/>
                    <g:message code="copyright"/>
                </div>

                <div class="col-sm-3 socialLinks">
                    <a href="https://www.facebook.com/sharer/sharer.php?u=${createLink(uri: '/', absolute: true)?.toString()?.encodeAsURL()}" target="_blank">
                        <i class="fa fa-facebook"></i>
                    </a>
                    <a href="https://twitter.com/home?status=${createLink(uri: '/', absolute: true)?.toString()?.encodeAsURL()}" target="_blank">
                        <i class="fa fa-twitter"></i>
                    </a>
                    <a href="https://plus.google.com/share?url=${createLink(uri: '/', absolute: true)?.toString()?.encodeAsURL()}" target="_blank">
                        <i class="fa fa-google"></i>
                    </a>
                    <a href="https://www.linkedin.com/shareArticle?mini=true&url=${createLink(uri: '/', absolute: true)?.toString()?.encodeAsURL()}&title=${message(code: 'site.title')?.encodeAsURL()}&summary=&source=" target="_blank">
                        <i class="fa fa-linkedin"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<g:render template="/layouts/common/onlineSupport"/>

<script language="javascript" type="text/javascript">
    jQuery('.backToTop').click(function () {
        jQuery('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
</script>
