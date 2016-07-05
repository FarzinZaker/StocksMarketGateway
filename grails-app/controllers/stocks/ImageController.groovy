package stocks

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.text.DateFormat
import java.text.SimpleDateFormat

class ImageController {

    def imageService

    def index() {
        try {
            if (!params.id || params.id == 'null') {
                render ''
                return
            }
            def image = Image.get(params.id)
            def content = image?.content
            def sizeFlag = ''
            if (params.size)
                sizeFlag = "${params.size}x${params.size}-"
            if (content)
                try {
                    content = new File("${grailsApplication.config.user.files.imagesPath}/image/${image?.id}/${sizeFlag}${image?.name}").getBytes()
                }
                catch (ignored) {
                }
            else if (params.default)
                content = new File("${grailsApplication.config.user.files.imagesPath}/default/${params.default}/${sizeFlag}${params.default}.png").getBytes()
            if (!content)
                content = new File("${grailsApplication.config.user.files.imagesPath}/image/no-image/${params.size}x${params.size}.jpg").getBytes()
            if (!content) {
                render ''
                return
            }
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
        } catch (ignored) {
            render ""
        }
    }

    def profile() {
        try {
            def image = Image.get(User.get(params.id as Long)?.imageId)
            def content = image?.content
            def sizeFlag = ''
            if (params.size)
                sizeFlag = "${params.size}x${params.size}-"
            if (content) {
                try {
                    content = new File("${grailsApplication.config.user.files.imagesPath}/image/${image?.id}/${sizeFlag}${image?.name}").getBytes()
                }
                catch (ignored) {
                }
            } else if (params.default)
                content = new File("${grailsApplication.config.user.files.imagesPath}/default/${params.default}/${sizeFlag}${params.default}.png").getBytes()
            if (!content)
                content = new File("${grailsApplication.config.user.files.imagesPath}/image/no-image/user-noImage.png").getBytes()
            if (!content) {
                render ''
                return
            }
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
        } catch (ignored) {
            render ""
        }
    }

    def property() {
        try {
            def image = Image.get(User.get(params.id as Long)?.imageId)
            def content = image?.content
            def sizeFlag = ''
            if (params.size)
                sizeFlag = "${params.size}x${params.size}-"
            if (content) {
                try {
                    content = new File("${grailsApplication.config.user.files.imagesPath}/image/${image?.id}/${sizeFlag}${image?.name}").getBytes()
                }
                catch (ignored) {
                }
            } else if (params.default)
                content = new File("${grailsApplication.config.user.files.imagesPath}/default/${params.default}/${sizeFlag}${params.default}.png").getBytes()
            if (!content)
                content = new File("${grailsApplication.config.user.files.imagesPath}/image/no-image/${params.type}-noImage.png").getBytes()
            if (!content) {
                render ''
                return
            }
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
        } catch (ignored) {
            render ""
        }
    }

    def uploadImage() {

        def image = new Image()
        image.name = params."${params.name}".fileItem.fileName
        image.content = []
        if (!image.validate() || !image.save()) {
            render '0'
            return
        }

        def path = "${grailsApplication.config.user.files.imagesPath}/image/${image.id}/${params."${params.name}".fileItem.fileName}"
        def file = new File(path)

        def directory = file.parentFile
        if (!directory.exists())
            directory.mkdirs()

        if (file.exists())
            file.delete()

        params."${params.name}".transferTo(file)

        def bytes = new File(path).getBytes()

        [
                [width: 500, height: 500],
                [width: 450, height: 450],
                [width: 400, height: 400],
                [width: 350, height: 350],
                [width: 300, height: 300],
                [width: 250, height: 250],
                [width: 200, height: 200],
                [width: 150, height: 150],
                [width: 120, height: 120],
                [width: 100, height: 100],
                [width: 80, height: 80]
        ].each {
            def thumbBytes = imageService.scaleImage(bytes, it.width, it.height)
            def thumbPath = "${grailsApplication.config.user.files.imagesPath}/image/${image.id}/${it.width}x${it.height}-${params."${params.name}".fileItem.fileName}"
            def thumbFile = new File(thumbPath)
            def thumbDirectory = thumbFile.parentFile
            if (!thumbDirectory.exists())
                thumbDirectory.mkdirs()

            if (thumbFile.exists())
                thumbFile.delete()

            thumbFile.withOutputStream { str ->
                str.write(thumbBytes)
            }
        }

        image.content = imageService.scaleImage(bytes, 50, 50)

        if (image.validate() && image.save())
            render image.id
        else
            render '0'
    }
}
