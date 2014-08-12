package stocks.codal.persist

import stocks.codal.Announcement
import stocks.codal.event.AnnouncementEvent

class AnnouncementPersistService {

    def grailsApplication

    Boolean update(AnnouncementEvent event) {
        def announcement = event.data as Announcement
        def result = announcement.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate'])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        announcement.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        announcement.save()
        result
    }

    Announcement create(AnnouncementEvent event) {
        new Announcement(event.properties + [creationDate: new Date(), modificationDate: new Date()]).save()
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
}
