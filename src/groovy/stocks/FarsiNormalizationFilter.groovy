package stocks


/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 7/5/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public final class FarsiNormalizationFilter {

    public final static String apply(String input) {

        normalize(input, input.size())
    }

    private final static String normalize(def input, def len)
    {
        if(input == 'تعداد نمادهاي کاهش يافته در شاخص') {
            println('-----------------------------------------')
            println('normilizing')
            println('-----------------------------------------')
        }

        def s = input.toString().toCharArray()
        if(input == 'تعداد نمادهاي کاهش يافته در شاخص') {
            println(2)
        }
        for(int i = 0; i < len; i++) {
            switch((int)s[i])
            {
                case 1740:
                    s[i] = '\u064A'
                    break
                case 1746:
                    s[i] = '\u064A'
                    break

                case 1705:
                    s[i] = '\u0643'
                    break

                case 1728:
                    s[i] = '\u0647'
                    break
                case 1729:
                    s[i] = '\u0647'
                    break

                case 1620:
                    len = delete(s, i, len)
                    i--
                    break
            }
        }

        if(input == 'تعداد نمادهاي کاهش يافته در شاخص') {
            println(s)
            println('-----------------------------------------')
        }
        s.toString()
    }

    public static final char YEH = 1610
    public static final char FARSI_YEH = 1740
    public static final char YEH_BARREE = 1746
    public static final char KEHEH = 1705
    public static final char KAF = 1603
    public static final char HAMZA_ABOVE = 1620
    public static final char HEH_YEH = 1728
    public static final char HEH_GOAL = 1729
    public static final char HEH = 1607


    private static int delete(def s, int pos, int len)
    {
        if(pos < len)
            System.arraycopy(s, pos + 1, s, pos, len - pos - 1)
        len - 1
    }
}
