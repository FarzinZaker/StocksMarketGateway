package stocks.codal

import stocks.DeliveryMethods
import stocks.portfolio.Portfolio
import stocks.portfolio.PortfolioItem
import stocks.tse.Company
import stocks.tse.IndustryGroup
import stocks.tse.IndustrySubgroup
import stocks.tse.Symbol

class Announcement {

    static notify = true

    Symbol symbol
    String symbolPersianCode
    Company company
    String companyName
    String title
    Date sendDate
    Date publishDate
    String detailsUrl
    String pdfUrl
    String excelUrl
    String xmlUrl
    String xbrlUrl
    String attachmentsUrl

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'codal_announcement'
    }


    static constraints = {
        symbol nullable: true
        symbolPersianCode(
                nullable: true,
                token: true,
                query: [
                        domain         : Symbol,
                        value          : 'persianCode',//{ Symbol item -> "${item.persianCode}" },
                        display        : { Symbol item -> "${item.persianCode} - ${item.persianName}" },
                        filter         : { Symbol item ->
                            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                                    ['300', '400', '309', '404'].contains(item.type) &&
                                    item.marketCode == 'MCNO'
                        },
                        deliveryMethods: [
                                [
                                        name: "search",
                                        type: DeliveryMethods.DIRECT
                                ],
                                [
                                        name     : "industryGroup",
                                        type     : DeliveryMethods.PARENT,
                                        relations: [
                                                [
                                                        domain    : IndustryGroup,
                                                        primaryKey: 'id',
                                                        foreignKey: 'industryGroup',
                                                        display   : 'name'
                                                ],
                                                [
                                                        domain    : IndustrySubgroup,
                                                        primaryKey: 'id',
                                                        foreignKey: 'industrySubgroup',
                                                        display   : 'name'
                                                ]
                                        ]
                                ],
                                [
                                        name     : "portfolio",
                                        type     : DeliveryMethods.PARENT,
                                        relations: [
                                                [
                                                        domain        : Portfolio,
                                                        primaryKey    : 'id',
                                                        filter        : [
                                                                'owner': 'currentUser'
                                                        ],
                                                        relationDomain: [
                                                                domain     : PortfolioItem,
                                                                parentField: 'portfolio',
                                                                childField : 'symbol'
                                                        ],
                                                        display       : 'name'
                                                ]
                                        ]
                                ]
                        ]
                ])
        company nullable: true
        companyName nullable: true, query: true, token: true
        title nullable: true, query: true, token: true
        sendDate nullable: true, query: true, token: true
        publishDate nullable: true, query: true, token: true
        detailsUrl nullable: true
        pdfUrl nullable: true
        excelUrl nullable: true
        xmlUrl nullable: true
        xbrlUrl nullable: true
        attachmentsUrl nullable: true
    }
}
