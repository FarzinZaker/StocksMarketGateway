package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar


class SnapshotJob {
    def cronExpression = "0 0 1 * * ?"

    def snapshotService

    def execute() {

        snapshotService.applyDailySnapshot()
        def jc = new JalaliCalendar(Calendar.getInstance() as GregorianCalendar)
        if (jc.getDayOfWeek() == Calendar.FRIDAY)
            snapshotService.applyWeeklySnapshot()
        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
            snapshotService.applyMonthlySnapshot()
    }
}
