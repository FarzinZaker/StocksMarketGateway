package stocks.alerting

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/19/15
 * Time: 6:48 PM
 */
public class MessageHelper {

    public static final String STATUS_WAITING = 'waiting'
    public static final String STATUS_SENT = 'sent'
    public static final String STATUS_FAILED = 'failed'
    public static final String STATUS_SUCCEED = 'succeed'

    public static final STATUS_LIST = [STATUS_WAITING, STATUS_SENT, STATUS_FAILED, STATUS_SUCCEED]

    public static final String DELIVERY_METHOD_PUSH = 'push'
    public static final String DELIVERY_METHOD_SMS = 'sms'

    public static final DELIVERY_METHOD_LIST = [DELIVERY_METHOD_PUSH, DELIVERY_METHOD_SMS]

}
