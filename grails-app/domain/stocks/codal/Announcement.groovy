package stocks.codal

import stocks.tse.Company
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
                query: true,
                token: true,
                source: [
                        domain : Symbol,
                        value  : { Symbol item -> "${item.persianCode}" },
                        display: { Symbol item -> "${item.persianCode} - ${item.persianName}" },
                        filter : { Symbol item ->
                            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                                    item.persianCode.charAt(0) != 'ج'
                                    ['300', '400'].contains(item.type) &&
                                    item.marketCode == 'NO'
                        }
                ])
//                searchFields: [
//                        'code',
//                        'companyCode',
//                        'companyName',
//                        'companySmallCode',
//                        'name',
//                        'PersianCode',
//                        'PersianName',
//                        'shortCode'
//                ])
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
