package cn.wangbowen.quartzstudy.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.listeners.JobListenerSupport;

/**
 * MyJobListener class
 *
 * @author BoWenWang
 * @date 2021/7/18 22:03
 */
// 也可以使用 extends JobListenerSupport ，针对需要的方法进行重写
public class MyJobListener extends JobListenerSupport {
    /**
     * 设置JOB名字
     * @return
     */
    @Override
    public String getName() {
        String simpleName = getClass().getSimpleName();
        System.out.println("监听器名字：" + simpleName);
        return simpleName;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name + "-> JobDetail将要被执行时，调用该方法");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name + "-> JobDetail将要被执行，但又被triggerListener否决时，调用该方法");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name + "-> JobDetail执行后，调用该方法");
    }
}
