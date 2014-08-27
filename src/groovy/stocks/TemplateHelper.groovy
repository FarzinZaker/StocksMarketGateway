package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar

/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 8/17/14
 * Time: 10:53 AM
 */

public class TemplateHelper {
    static final String SYSTEM_TOKEN_DATE = 'date'
    static final String SYSTEM_TOKEN_TIME = 'time'

    static final ArrayList<String> SYSTEM_TOKENS = [
            SYSTEM_TOKEN_DATE,
            SYSTEM_TOKEN_TIME
    ]

    static final def getSystemTokenValue(String token){
        switch (token){
            case SYSTEM_TOKEN_DATE:
                return formatDate(new Date())
            case SYSTEM_TOKEN_TIME:
                return formatTime(new Date())
        }
    }

    private static def formatDate = { date ->
        def cal = Calendar.getInstance()
        cal.setTime(date)
        def jc = new JalaliCalendar(cal)
        String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())

    }

    private static def formatTime = { time ->
        def cal = Calendar.getInstance()
        cal.setTime(time)

        String.format(" %02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))

    }
}
