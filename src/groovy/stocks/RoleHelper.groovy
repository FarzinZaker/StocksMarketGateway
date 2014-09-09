package stocks
/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 6/18/14
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoleHelper {
    public static final String ROLE_ADMIN = "ROLE_ADMIN"
    public static final String ROLE_USER = "ROLE_USER"
    public static final String ROLE_BROKER_ADMIN = "ROLE_BROKER_ADMIN"
    public static final String ROLE_BROKER_USER = "ROLE_BROKER_USER"

    public static final String[] ROLES = [ROLE_ADMIN, ROLE_USER, ROLE_BROKER_ADMIN, ROLE_BROKER_USER]
    public static final String[] SYSTEM_ROLES = [ROLE_ADMIN, ROLE_USER]
    public static final String[] BROKER_ROLES = [ROLE_BROKER_ADMIN, ROLE_BROKER_USER]
}
