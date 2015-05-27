package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar


class SnapshotJob {
    def cronExpression = "0 0 1 * * ?"

    def snapshotService
    def grailsApplication

    def execute() {
        if (grailsApplication.config.jobsDisabled)
            return

        snapshotService.applyDailySnapshot()
        def calendar = Calendar.getInstance() as GregorianCalendar
        def jc = new JalaliCalendar(calendar)
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            snapshotService.applyWeeklySnapshot()
        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
            snapshotService.applyMonthlySnapshot()
    }
}
