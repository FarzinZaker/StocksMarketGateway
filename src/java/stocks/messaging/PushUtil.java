package stocks.messaging;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.aerogear.unifiedpush.DefaultPushSender;
import org.jboss.aerogear.unifiedpush.PushSender;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;


/**
 * Created by farzin on 7/20/2015.
 */
public class PushUtil {

    static {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                // ip address of the service URL(like.23.28.244.244)
                if (hostname.equals("91.98.102.75") || hostname.equals("127.0.0.1"))
                    return true;
                return false;
            }
        });
    }

    public static void push(String alias, String message) {
        final PushSender sender =
                DefaultPushSender.withRootServerURL("https://127.0.0.1:8080/ag-push/")
                        .pushApplicationId("38f38440-7a0f-4984-ae80-7a11bc5b43e0")
                        .masterSecret("27e2145a-be3b-4b5a-a50d-a0e5510805d0")
                        .build();

        final UnifiedMessage unifiedMessage = UnifiedMessage.
                withMessage()
                .alert(message)
                .build();


        sender.send(unifiedMessage, new MessageResponseCallback() {

            @Override
            public void onComplete() {
                int x = 10;
            }
        });
    }
}
