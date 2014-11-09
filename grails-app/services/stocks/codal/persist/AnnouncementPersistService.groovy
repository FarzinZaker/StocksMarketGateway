package stocks.codal.persist

import groovy.transform.TimedInterrupt
import stocks.alerting.QueryInstance
import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent

class AnnouncementPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(AnnouncementEvent event) {
        def announcement = Announcement.get(event.data.id)
        beforeUpdate(event, announcement)
        announcement.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(announcement)
        afterUpdate(event, announcement)
        false
    }

    Announcement create(AnnouncementEvent event) {
        beforeCreate(event)
        def data = new Announcement(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
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

    @TimedInterrupt(60L)
    private def downloadFile(String url, String path, Integer retryCount = 0) {
        if ((!url || url == '' || url == 'null') && retryCount == 0)
            return
        try {
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
        } catch (ignored) {
            if (retryCount < 3)
                downloadFile(url, path, retryCount + 1)
            else {
                println("unable to download ${url} because of ")
                ignored.printStackTrace()
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
