import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author LiAixing
 * @version 1.0
 * @className Client
 * @description TODO
 * @date 2020/3/4 14:25
 **/
public class Client {
    private static final Logger logger = Logger.getLogger(Client.class);

    public static void main(String[] args) throws SchedulerException {

        logger.info("--Bilibili Live Auto Sign System Start--");
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // start
        scheduler.start();
        JobKey jobKey = new JobKey("Sign","Sign-1");
        JobDetail jobDetail = JobBuilder.newJob(AutoSign.class).withIdentity(jobKey).build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0 0 8,22 1/1 * ? *")).build();
        scheduler.scheduleJob(jobDetail,trigger);
    }

}
