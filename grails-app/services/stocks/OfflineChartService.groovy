package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder

import java.io.*;

import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

class OfflineChartService{

    def heatMap(){
        def command = "/usr/local/bin/node ${ServletContextHolder.servletContext.getRealPath('/')}heat-map/heatMap.js"
        def proc = command.execute()
        proc.waitFor()

        // Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                new Float(.8));

        // Create the transcoder input.
        String svgURI = new File(args[0]).toURL().toString();
        TranscoderInput input = new TranscoderInput(svgURI);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        t.transcode(input, output);

        // Flush and close the stream.
        ostream.flush();
        ostream.close();

        println "heatMap created"
    }
}
