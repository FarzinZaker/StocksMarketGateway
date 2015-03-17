package stocks.codal.event

import stocks.codal.Announcement
import stocks.tse.Company
import stocks.tse.Symbol

class AnnouncementEvent {

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
    Announcement data

    static mapping = {
        table 'codal_announcement_ev'
    }


    static constraints = {
        symbol nullable: true
        symbolPersianCode nullable: true
        company nullable: true
        companyName nullable: true
        title nullable: true
        sendDate nullable: true
        publishDate nullable: true
        detailsUrl nullable: true
        pdfUrl nullable: true
        excelUrl nullable: true
        xmlUrl nullable: true
        xbrlUrl nullable: true
        attachmentsUrl nullable: true

        data nullable: true
    }
}
