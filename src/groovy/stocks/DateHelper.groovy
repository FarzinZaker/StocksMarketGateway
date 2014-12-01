package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar

/**
 * Created by farshid on 12/1/14.
 */
class DateHelper {
    static String jalali(date, hm) {
        def cal = Calendar.getInstance()
        cal.setTime(date)

        def jc = new JalaliCalendar(cal)
        def hmPart = ""
        if (hm)
            hmPart = String.format("%02d:%02d ", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
        String.format("${hmPart} %04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
    }
}
