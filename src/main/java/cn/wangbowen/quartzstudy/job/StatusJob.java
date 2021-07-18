package cn.wangbowen.quartzstudy.job;

import org.quartz.*;

/**
 * StatusJob class
 *
 * @author BoWenWang
 * @date 2021/7/15 15:41
 */
@PersistJobDataAfterExecution
public class StatusJob implements Job {

    // 获取数据方式一：通过定义数据+set方法
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 可以获得Job的基本信息
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println(jobKey.getGroup() + "," + jobKey.getName());

        // 获取信息方式二
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String msg = jobDataMap.getString("msg");

        System.out.println(msg + price);
        jobDataMap.put("price", ++price);
    }
}
