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
        symbolPersianCode nullable: true, query: true, token: true
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
