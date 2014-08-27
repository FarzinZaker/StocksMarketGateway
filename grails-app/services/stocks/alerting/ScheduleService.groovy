package stocks.alerting

import groovy.time.TimeCategory


class ScheduleService {

    def static final WEEK_DAYS = ['saturday', 'sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday']

    void calculateQueryInstanceNextExecutionTime(QueryInstance queryInstance) {

        if (!queryInstance.lastExecutionTime)
            queryInstance.lastExecutionTime = new Date()
        switch (queryInstance.schedule.type) {
            case 'eventBased':
                queryInstance.nextExecutionTime = calculateEventBasedQueryInstanceNextExecutionTime(queryInstance)
                break;
            case 'periodic':
                queryInstance.nextExecutionTime = calculatePeriodicQueryInstanceNextExecutionTime(queryInstance)
                break;
            case 'specificTime':
                queryInstance.nextExecutionTime = calculateSpecificTimeQueryInstanceNextExecutionTime(queryInstance)
                break;
        }
        queryInstance.save()

    }

    private Date calculateEventBasedQueryInstanceNextExecutionTime(QueryInstance queryInstance) {
        use(TimeCategory) {
            queryInstance.lastExecutionTime + 5.minutes
        }
    }

    private static Date calculatePeriodicQueryInstanceNextExecutionTime(QueryInstance queryInstance) {

        def now = new Date()
        def calendar = Calendar.getInstance()
        calendar.setTime(queryInstance.lastExecutionTime)
        def lastExecutionDayIndex = calendar.get(Calendar.DAY_OF_WEEK)
        def lastExecutionTimeInMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)

        //check if next time is in same day as previous
        def daySchedule = ScheduleDay.findByScheduleAndDay(queryInstance.schedule, WEEK_DAYS[lastExecutionDayIndex])
        if (daySchedule) {
            def nextExecutionTime = lastExecutionTimeInMinutes + queryInstance.schedule.intervalStep
            if (nextExecutionTime <= daySchedule.endTimeInMinute) {
                calendar.set(Calendar.HOUR_OF_DAY, (nextExecutionTime / 60).toInteger())
                calendar.set(Calendar.MINUTE, nextExecutionTime % 60)
                calendar.set(Calendar.SECOND, 0)
                return now < calendar.time ? calendar.time : now
            }
        }

        //find next execution day
        def nextExecutionDay
        def indexer = (lastExecutionDayIndex + 1) % 7;
        def addedDays = 1
        while (!nextExecutionDay) {
            nextExecutionDay = ScheduleDay.findByScheduleAndDay(queryInstance.schedule, WEEK_DAYS[indexer])
            if(!nextExecutionDay) {
                indexer = (indexer + 1) % 7
                addedDays++
            }
        }
        def nextExecutionTime = nextExecutionDay.startTimeInMinute
        if(nextExecutionTime != null) {
            calendar.add(Calendar.DAY_OF_YEAR, addedDays)
            calendar.set(Calendar.HOUR_OF_DAY, (nextExecutionTime / 60).toInteger())
            calendar.set(Calendar.MINUTE, nextExecutionTime % 60)
            calendar.set(Calendar.SECOND, 0)
            return now < calendar.time ? calendar.time : now
        }

        return null
    }

    private static Date calculateSpecificTimeQueryInstanceNextExecutionTime(QueryInstance queryInstance) {

        def now = new Date()
        def calendar = Calendar.getInstance()
        calendar.setTime(queryInstance.lastExecutionTime)
        def lastExecutionDayIndex = calendar.get(Calendar.DAY_OF_WEEK)
        def lastExecutionTimeInMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)

        //check if next time is in same day as previous
        if (ScheduleDay.countByScheduleAndDay(queryInstance.schedule, WEEK_DAYS[lastExecutionDayIndex]) > 0) {
            def nextExecutionTime = ScheduleTime.findByScheduleAndTimeInMinuteGreaterThan(queryInstance.schedule, lastExecutionTimeInMinutes)
            if (nextExecutionTime) {
                calendar.set(Calendar.HOUR_OF_DAY, (nextExecutionTime.timeInMinute / 60).toInteger())
                calendar.set(Calendar.MINUTE, nextExecutionTime.timeInMinute % 60)
                calendar.set(Calendar.SECOND, 0)
                return now < calendar.time ? calendar.time : now
            }
        }

        //find next execution day
        def nextExecutionDay
        def indexer = (lastExecutionDayIndex + 1) % 7;
        def addedDays = 1
        while (!nextExecutionDay) {
            nextExecutionDay = ScheduleDay.findByScheduleAndDay(queryInstance.schedule, WEEK_DAYS[indexer])
            if(!nextExecutionDay) {
                indexer = (indexer + 1) % 7
                addedDays++
            }
        }
        def nextExecutionTime = ScheduleTime.findAllBySchedule(queryInstance.schedule)?.collect {it.timeInMinute}?.min()
        if(nextExecutionTime != null) {
            calendar.add(Calendar.DAY_OF_YEAR, addedDays)
            calendar.set(Calendar.HOUR_OF_DAY, (nextExecutionTime / 60).toInteger())
            calendar.set(Calendar.MINUTE, nextExecutionTime % 60)
            calendar.set(Calendar.SECOND, 0)
            return now < calendar.time ? calendar.time : now
        }

        return null
    }
}
