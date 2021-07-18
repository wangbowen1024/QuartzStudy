package cn.wangbowen.quartzstudy;

import cn.wangbowen.quartzstudy.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * HelloMain class
 *
 * @author BoWenWang
 * @date 2021/7/15 9:57
 */
public class CronMain {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 2. 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 3. 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("name", "group")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))  // Cron表达式
                .build();

        // 4. 创建任务
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)         // 构建任务类型
                .withIdentity("name", "group")              // 指定名字和组
                .build();                                               // 构建实例

        // 5.调度
        scheduler.scheduleJob(jobDetail, trigger);

        // 6.启动(只有start之后，才会对触发器有作用。且启动后就不会停止，直到调用 shutdown)
        scheduler.start();

        Thread.sleep(1000 * 20);

        // 7.关闭
        scheduler.shutdown();
    }
}
