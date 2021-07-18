package cn.wangbowen.quartzstudy;

import cn.wangbowen.quartzstudy.job.HelloJob;
import cn.wangbowen.quartzstudy.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * JobListenerMain class
 *
 * @author BoWenWang
 * @date 2021/7/18 22:11
 */
public class JobListenerMain {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("name", "group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever()
                )
                .build();
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("name", "group")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);

        // 全局监听
        //scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

        // 部分监听
        // 1.通过job名字和组
        scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("name", "group")));

        // 2.通过组
        //scheduler.getListenerManager().addJobListener(new MyJobListener(), GroupMatcher.groupEquals("group"));

        // 其他：AndMatcher、OrMatcher

        scheduler.start();
        Thread.sleep(1000 * 20);
        scheduler.shutdown();
    }
}
