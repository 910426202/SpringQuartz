import com.blabla.Job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by panting1 on 2017/8/11.
 */
public class mainApp {
    //普通的Quartz
    public static void runNomral() {
        //1 创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob").build();

        Date date = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3000);//ms

        //2 SimpleTrigger
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "group1")
                .startNow()
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .withRepeatCount(3))
                .build();

        //3 Scheduler
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = sfact.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        //Scheduler将jobDetail和Simpletrigger关联起来
//        try {
//            scheduler.scheduleJob(jobDetail, simpleTrigger);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        };


        //CronTrigger
        CronTrigger cronTrigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("cronTrigger", "group1")
                .startAt(date)
                .endAt(endDate)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        };

        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //执行Spring整合Quartz
    public static void runSpring(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring-config.xml"}
        );

        //启动spring容器会自动启动scheduler
        context.start();
    }

    public static void main(String[] args) {
        //runNomral();
        runSpring();
    }
}
