package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar

class FormatTagLib {
    static defaultEncodeAs = [taglib: 'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "format"

    def jalaliDate = {attrs, body ->
        if (attrs.date) {
            def cal = Calendar.getInstance()
            cal.setTime(attrs.date)

            def jc = new JalaliCalendar(cal)
            if (Boolean.parseBoolean(attrs.hm))
                out << String.format("%02d:%02d ", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
            out << String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        }
    }

    def html = {attrs, body ->
        out << attrs.value
    }
}
