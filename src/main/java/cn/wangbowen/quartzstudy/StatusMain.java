package cn.wangbowen.quartzstudy;

import cn.wangbowen.quartzstudy.job.HelloJob;
import cn.wangbowen.quartzstudy.job.StatusJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * HelloMain class
 *
 * @author BoWenWang
 * @date 2021/7/15 9:57
 */
public class StatusMain {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 2. 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 3. 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()                   // 构建Buidler
                .withIdentity("name", "group")              // 添加名字和组
                .startNow()                                             // 指定开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()    // 指定调度类型（有基于时间间隔的和日历的）
                    .withIntervalInSeconds(5)                           // 设置触发时间间隔
                    .repeatForever()                                    // 设置触发次数
                )
                .build();                                               // 构建实例

        // 4. 创建任务
        JobDetail jobDetail = JobBuilder.newJob(StatusJob.class)
                .withIdentity("name1", "group1")
                .usingJobData("msg", "I pay for ")          // 添加信息
                .usingJobData("price", 123d)
                .build();

        // 5.调度
        scheduler.scheduleJob(jobDetail, trigger);

        // 6.启动(只有start之后，才会对触发器有作用。且启动后就不会停止，直到调用 shutdown)
        scheduler.start();

        Thread.sleep(1000 * 20);

        // 7.关闭
        scheduler.shutdown();
    }
}
