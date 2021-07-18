package cn.wangbowen.quartzstudy.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HelloJob class
 *
 * @author BoWenWang
 * @date 2021/7/15 9:54
 */
// 1.实现Job接口
public class HelloJob implements Job {

    public HelloJob() {
        // 每次调用都会创建一个新的实例，执行完成后，将删除对作业类实例的引用，然后对该实例进行垃圾回收。
        System.out.println("init new one");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": Hello World!");
    }
}
