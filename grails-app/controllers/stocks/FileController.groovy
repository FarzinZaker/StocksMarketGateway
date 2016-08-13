package stocks

import grails.converters.JSON
import groovy.io.FileType

import javax.imageio.ImageIO
import java.text.DateFormat
import java.text.SimpleDateFormat

class FileController {

    def grailsApplication
    def springSecurityService

    def index() {
    }

    def read() {

        def path = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/${params.path}"

        def folders = []
        def files = []

        def dir = new File(path)
        if (!dir.exists())
            dir.mkdirs()
        dir.eachFile(FileType.DIRECTORIES) { file ->
            if (file.getName() != 'thumbnail')
                folders << file
        }
        dir.eachFile(FileType.FILES) { file ->
            files << file
        }

        render "[${(folders.collect { "{\"name\": \"${it.getName()}\", \"type\": \"d\", \"size\": \"0\"}" } + files.collect { "{\"name\": \"${it.getName()}\", \"type\": \"f\", \"size\": \"${it.size()}\"}" }).join(',')}]"
    }

    def thumbnail() {
        def thumbnailFilePath = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/thumbnail/${params.path}"
        def thumbnailFile = new File(thumbnailFilePath)

        def thumbnailDirectoryPath = thumbnailFile.parentFile.absolutePath
        def thumbnailDirectory = new File(thumbnailDirectoryPath)

        def isImage = true
        int dot = params.path?.lastIndexOf('.');
        isImage = dot != -1
        if (!["png", "jpg", "jpeg", "gif", "bmp"].any { it == params.path?.substring(dot + 1)?.toLowerCase() })
            isImage = false

        if (isImage) {
            if (!thumbnailFile.exists()) {
                if (!thumbnailDirectory.exists())
                    thumbnailDirectory.mkdirs()


                def filePath = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/${params.path}"
                ImageTool imageTool = new ImageTool();
                imageTool.load(filePath);
                imageTool.thumbnailSpecial(80, 80, 3, 2);
                imageTool.swapSource();
                imageTool.square();
                new File(thumbnailFilePath).createNewFile()
                imageTool.writeResult(thumbnailFilePath, "PNG");
            }
        } else {
            thumbnailFilePath = "${grailsApplication.config.user.files.imagesPath}/file.png"
        }

        thumbnailFile = new File(thumbnailFilePath)
        def content = thumbnailFile.getBytes()
        if (content) {
            def seconds = 3600 * 24
            DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.SECOND, seconds);
            response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds + ", must-revalidate");
            response.setHeader("Expires", httpDateFormat.format(cal.getTime()));
            response.contentType = 'image/png'
            response.setStatus(200)
            response.outputStream << content
            response.outputStream.flush()
        } else
            render ""
    }

    def create() {
        def path = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/${params.path}/${params.name}"
        def directory = new File(path)
        if (!directory.exists())
            directory.mkdirs()
        render ''
    }

    def destroy() {
        def path = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/${params.path}/${params.name}"
        def item = new File(path)
        if (item.exists()) {
            if (params.type == 'f') {
                item.delete()

                def thumbnailPath = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/thumbnail/${params.path}${params.name}"
                def thumbnailFile = new File(thumbnailPath)
                if (thumbnailFile.exists())
                    thumbnailFile.delete()
            } else
                item.deleteDir()
        }
        render ''
    }

    def upload() {
        def fileName = FarsiNormalizationFilter.apply(params.file.fileItem.fileName)
        def path = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/${params.path}${fileName}"
        def file = new File(path)

        def directory = file.parentFile
        if (!directory.exists())
            directory.mkdirs()

        if (file.exists())
            file.delete()

        def thumbnailPath = "${grailsApplication.config.user.files.imagesPath}/${springSecurityService.currentUser?.id}/thumbnail/${params.path}${fileName}"
        def thumbnailFile = new File(thumbnailPath)
        if (thumbnailFile.exists())
            thumbnailFile.delete()

        params.file.transferTo(file)

        file = new File(path)

        render "{ \"name\": \"${file.name}\", \"type\": \"f\", \"size\": ${file.size()}}"
    }

    def image() {
        def path = "${grailsApplication.config.user.files.imagesPath}/${params.id}/${params.path}"
        def content = null
        try {
            path = new File(path)
            content = path?.getBytes()
        }catch(ignored){}
        if (content) {
            def seconds = 3600 * 24
            DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.SECOND, seconds);
            response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds + ", must-revalidate");
            response.setHeader("Expires", httpDateFormat.format(cal.getTime()));
            response.contentType = 'image/png'
            response.setStatus(200)
            response.outputStream << content
            response.outputStream.flush()
        } else
            render ""
    }

    def imageBrowser(){
        render template: 'imageBrowser', model: [userId: springSecurityService.currentUser?.id, imageBrowserPath : createLink(uri:'/File')]
    }

    def imageUploader(){
        render template: 'imageUploader', model: [userId: springSecurityService.currentUser?.id]
    }
}
