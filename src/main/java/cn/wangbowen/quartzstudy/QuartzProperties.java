package cn.wangbowen.quartzstudy;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * QuartzProperties class
 *
 * @author BoWenWang
 * @date 2021/7/18 18:12
 */
public class QuartzProperties {
    public static void main(String[] args) throws SchedulerException {
        // 创建工厂实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

        // 创建配置对象
        Properties properties = new Properties();
        properties.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "5");

        // 配置
        stdSchedulerFactory.initialize(properties);
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        scheduler.start();
    }
}
