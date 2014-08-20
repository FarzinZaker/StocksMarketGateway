package stocks.codal.persist

import stocks.alerting.QueryInstance
import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent

class AnnouncementPersistService {

    def grailsApplication
    def queryService
    def smsService

    Boolean update(AnnouncementEvent event) {
        def announcement = event.data as Announcement
        beforeUpdate(event, announcement)
        def result = announcement.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate'])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        announcement.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        announcement.save()
        afterUpdate(event, announcement)
        result
    }

    Announcement create(AnnouncementEvent event) {
        beforeCreate(event)
        def data = new Announcement(event.properties + [creationDate: new Date(), modificationDate: new Date()]).save()
        afterCreate(event, data)
        data
    }

    def grabFiles(AnnouncementEvent event) {
        def filesPath = grailsApplication.config.codal.filesPath
        def announcement = event.data
        if (announcement.pdfUrl)
            downloadFile(announcement.pdfUrl, "${filesPath}/pdf/${announcement.id}.pdf")
        if (announcement.excelUrl)
            downloadFile(announcement.excelUrl, "${filesPath}/excel/${announcement.id}.xls")
        if (announcement.xmlUrl)
            downloadFile(announcement.xmlUrl, "${filesPath}/xml/${announcement.id}.xml")
    }

    private static def downloadFile(String url, String path) {
        def file = new File(path)
        if (file.exists())
            file.delete()
        file.parentFile.mkdirs()
        new URL(url).openConnection().with { conn ->
            conn.instanceFollowRedirects = false
            url = conn.getHeaderField("Location")
            if (!url) {
                new File(path).withOutputStream { out ->
                    conn.inputStream.with { inp ->
                        out << inp
                        inp.close()
                    }
                }
            }
        }
    }

    protected void beforeCreate(AnnouncementEvent event) {

    }

    protected void afterCreate(AnnouncementEvent event, Announcement data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(AnnouncementEvent event, Announcement data) {

    }

    protected void afterUpdate(AnnouncementEvent event, Announcement data) {
    }
}
