package stocks

import org.watij.webspec.dsl.WebSpec

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class ImageService {

    static transactional = false

    def scaleImage(byte[] content, int width, int height) {

        BufferedImage sourceImage = ImageIO.read(new ByteArrayInputStream(content))
        def w = sourceImage.width
        def h = sourceImage.height

        def thumbnail
        if (w > h)
            thumbnail = sourceImage.getScaledInstance(width, -1, java.awt.Image.SCALE_SMOOTH);
        else
            thumbnail = sourceImage.getScaledInstance(-1, height, java.awt.Image.SCALE_SMOOTH);

        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
                thumbnail.getHeight(null), sourceImage.type);
        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
        bufferedThumbnail.getGraphics().dispose()
        def stream = new ByteArrayOutputStream()
        ImageIO.write(bufferedThumbnail, 'png', stream)
        stream.toByteArray()
    }
}
