package stocks

import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.core.QuartzScheduler
import org.quartz.impl.StdScheduler
import org.quartz.impl.StdSchedulerFactory
import org.springframework.scheduling.quartz.*

class DataService {

    def grailsApplication

    def dataServicePackages = [
            'stocks.codal.data',
            'stocks.tse.data'
    ]

    def initializeJobs() {
        grailsApplication.serviceClasses.findAll { service ->
            dataServicePackages.any {
                service.fullName.contains(it)
            }
        }.each {
            def dataService = grailsApplication.mainContext[it.propertyName]
            if (dataService.hasProperty('schedules')) {
                def schedules = dataService.schedules
                schedules?.each { schedule ->

                    MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
                    jobDetail.setGroup('data-job');
                    jobDetail.setName("${dataService.metaClass.delegate.theClass.name}_${schedule.method}");
                    jobDetail.setConcurrent(false);
                    jobDetail.setTargetObject(dataService);
                    jobDetail.setTargetMethod(schedule.method as String);
                    jobDetail.afterPropertiesSet();

                    def trigger = this.class.classLoader.loadClass("org.springframework.scheduling.quartz.${schedule.trigger.type as String}TriggerBean")?.newInstance()
                    trigger.setBeanName("${dataService.metaClass.delegate.theClass.name}_${schedule.method}_trigger");
                    trigger.setJobDetail((JobDetail) jobDetail.getObject());
                    schedule.trigger.parameters.each { key, value ->
                        trigger."${key}" = value
                    }
                    trigger.afterPropertiesSet();

                    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                    if (!scheduler.getJobNames("data-job").contains("${dataService.metaClass.delegate.theClass.name}_${schedule.method}")) {
                        scheduler.scheduleJob(jobDetail.getObject() as JobDetail, trigger as Trigger);
                        scheduler.start();
                    }
                }
            }
        }
    }

    def getJobList() {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.getJobNames("data-job").collect() { jobName ->
            Trigger[] triggers = scheduler.getTriggersOfJob(jobName, "data-job");
            [jobName: jobName, nextFireTime: triggers[0].getNextFireTime(), previousFireTime: triggers[0].getPreviousFireTime(), timesTriggered: triggers[0] instanceof SimpleTriggerBean ? triggers[0].timesTriggered : '-']
        }
    }

    def restartJob(String name) {

        def namingParameters = name.split('_')
        def serviceName = namingParameters[0]
        def methodName = namingParameters[1]
        def service = grailsApplication.serviceClasses.find { service ->
            service.fullName == serviceName
        }

        def dataService = grailsApplication.mainContext[service.propertyName]
        if (dataService.hasProperty('schedules')) {
            def schedules = dataService.schedules
            def schedule = schedules?.find { it.method == methodName }

            MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
            jobDetail.setGroup('data-job');
            jobDetail.setName("${dataService.metaClass.delegate.theClass.name}_${schedule.method}");
            jobDetail.setConcurrent(false);
            jobDetail.setTargetObject(dataService);
            jobDetail.setTargetMethod(schedule.method as String);
            jobDetail.afterPropertiesSet();
            def trigger = this.class.classLoader.loadClass("org.springframework.scheduling.quartz.${schedule.trigger.type as String}TriggerBean")?.newInstance()
            trigger.setBeanName("${dataService.metaClass.delegate.theClass.name}_${schedule.method}_trigger");
            trigger.setJobDetail((JobDetail) jobDetail.getObject());
            schedule.trigger.parameters.each { key, value ->
                trigger."${key}" = value
            }
            trigger.afterPropertiesSet();

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.deleteJob("${dataService.metaClass.delegate.theClass.name}_${schedule.method}", 'data-job')
            if (!scheduler.getJobNames("data-job").contains("${dataService.metaClass.delegate.theClass.name}_${schedule.method}")) {
                scheduler.scheduleJob(jobDetail.getObject() as JobDetail, trigger as Trigger);
                scheduler.start();
            }
        }
    }
}
