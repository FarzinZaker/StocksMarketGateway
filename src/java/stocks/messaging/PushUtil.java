package stocks.messaging;

import org.jboss.aerogear.unifiedpush.DefaultPushSender;
import org.jboss.aerogear.unifiedpush.PushSender;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;

/**
 * Created by farzin on 7/20/2015.
 */
public class PushUtil {

    public static void push(String alias, String message) {
        final PushSender sender =
                DefaultPushSender.withRootServerURL("http://192.168.0.164:8080/ag-push/")
                        .pushApplicationId("ec702fd0-cc36-465d-8763-4a4e8f1a5155")
                        .masterSecret("0ba91fb0-181e-4e88-a595-ac50aa00470a")
                        .build();

        final UnifiedMessage unifiedMessage = UnifiedMessage.
                withMessage()
                .alert(message)
                .build();


        sender.send(unifiedMessage, new MessageResponseCallback() {

            @Override
            public void onComplete() {
                //do cool stuff
            }
        });
    }
}
