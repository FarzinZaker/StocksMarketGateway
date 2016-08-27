package stocks.analysis

import com.jniwrapper.win32.gdi.Bitmap
import com.jniwrapper.win32.gdi.GdiObject
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory
import org.apache.commons.codec.binary.Base64
import stocks.Image
import stocks.RoleHelper
import stocks.User

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.awt.image.ImageObserver

class TechnicalController {

    def sharingService
    def springSecurityService
    def graphDBService

    def image() {

        def data = JSON.parse(params.images?.toString()).charts
        def timeAxis = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(data?.timeAxis?.content?.toString()?.replace('data:image/png;base64,', ''))))
        def content = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(data?.panes?.find()?.content?.toString()?.replace('data:image/png;base64,', ''))))
        def rightAxis = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(data?.panes?.find()?.rightAxis?.content?.toString()?.replace('data:image/png;base64,', ''))))

        def width = content.width + rightAxis.width
        def height = content.height + timeAxis.height

        def padding = 10;

        def chart = new BufferedImage(width + padding * 2, height + padding * 2, BufferedImage.TYPE_INT_ARGB)
        def g = chart.createGraphics() as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING as RenderingHints.Key, RenderingHints.VALUE_ANTIALIAS_ON);

        //font
        def fontSource = TechnicalController.classLoader.getResourceAsStream('fonts/eyekan.ttf')
        def fontFactory = Font.createFont(Font.TRUETYPE_FONT, fontSource)
        def font = fontFactory.deriveFont(1, 16f)

        def englishFont = new Font('times', 0, 11)

        //background
        g.setColor(new Color(241, 243, 246))
        g.fillRect(0, 0, chart.width, chart.height)

        //chart
        g.drawImage(timeAxis, 0 + padding, content.height + padding, timeAxis.width, timeAxis.height, null)
        g.drawImage(rightAxis, content.width + padding, 0 + padding, rightAxis.width, rightAxis.height, null)
        g.drawImage(content, 0 + padding, 0 + padding, content.width, content.height, null)

        //empty box
        g.setColor(Color.white)
        g.fillRect(content.width + padding, content.height + padding, rightAxis.width, timeAxis.height)
        g.setColor(new Color(85, 85, 85))
        g.fillRect(content.width + padding, content.height + padding + 1, 1, 1)
        g.fillRect(content.width + padding + 1, content.height + padding, 1, 1)
        g.fillRect(content.width + padding + 1, content.height + padding + 1, 1, 1)

        //border
        g.setColor(new Color(201, 203, 205))
        g.drawRect(padding - 1, padding - 1, content.width + rightAxis.width + 1, content.height + timeAxis.height + 1)


        def text = "${data.meta.symbol?.find()?.replace(':', ' - ')} (${data.meta.description?.find()})"
        g.setFont(font)
        def textWidth = g.getFontMetrics().stringWidth(text)
        def date = "${format.jalaliDate(date: new Date(), hm: true).split(' ').reverse().join('   ').replace('/', ' / ')}"
        g.setFont(englishFont)
        def dateWidth = g.getFontMetrics().stringWidth(date)

        g.setColor(new Color(241, 243, 246, 150))
        g.fillRect(15, 15, [textWidth, dateWidth].max() + 20, 50)

        //text
        g.setFont(font)
        g.setColor(Color.BLACK)
        g.drawString(text, 25, 35)

        //data
        g.setFont(englishFont)
        g.setColor(Color.BLACK)
        g.drawString(date, 25, 55)

        //watermark
        def watermarkSource = TechnicalController.classLoader.getResourceAsStream('images/watermark.png')
        def watermark = ImageIO.read(watermarkSource)
        g.drawImage(watermark, Math.round(width / 2 - watermark.width / 2 + padding) as Integer, Math.round(height / 2 - watermark.height / 2 + padding) as Integer, null)

        def output = new ByteArrayOutputStream()
        ImageIO.write(chart, 'PNG', output)

        def image = new Image()
        image.content = output.toByteArray()
        image.name = 'test'
        image.save(flush: true)

        render image?.id
    }

    def save() {
        println params

        def owner = springSecurityService.currentUser as User

        def tags = sharingService.extractTextRelations(params.body as String)

        if (params.predictionType != '-' && params.predictionItems) {
            if (params.predictionItems instanceof String)
                params.predictionItems = [params.predictionItems]
            params.predictionItems.each { String item ->
                def itemParts = item?.trim()?.split('-')
                def tag = tags.tagList.find {
                    it.type == itemParts?.first() && it.identifier == itemParts?.last()?.toLong()
                }
                if (tag) {
                    tag.prediction = [
                            type  : params.predictionType?.trim(),
                            period: params.predictionPeriod?.trim()?.toLowerCase(),
                            risk  : params.predictionRiskLevel?.trim()?.toInteger()
                    ]
                    def daysCount = 1
                    switch (tag.prediction.period) {
                        case '1w':
                            daysCount = 7
                            break;
                        case '4w':
                            daysCount = 4 * 7
                            break;
                        case '12w':
                            daysCount = 12 * 7
                            break;
                        case '26w':
                            daysCount = 26 * 7
                            break;
                    }
                    use(TimeCategory) {
                        tag.prediction.endDate = new Date().clearTime() + (1 + daysCount).days
                    }
                }
            }
        }

        sharingService.shareAnalysis(owner, tags.text,
                tags.tagList,
                tags.mentionList,
                params.data,
                params.image?.toLong(),
                [
                        type      : params.propertyType,
                        identifier: params.propertyId?.toLong(),
                        title     : params.propertyTitle
                ])
        render '1'
    }

    @Secured([RoleHelper.ROLE_USER])
    def delete(String id) {
        try {
            sharingService.removeMaterial(id)
            render '1'
        }
        catch (ignored) {
            render '0'
        }
    }
}
