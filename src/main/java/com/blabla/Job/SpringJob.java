package com.blabla.Job;

import com.blabla.bean.HelloBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Spring Quartz 需要覆盖executeInternal方法
 * Created by panting1 on 2017/8/11.
 */
public class SpringJob extends QuartzJobBean {
    //Spring根据Setter方法自动注入JobDataMap
    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

    @Autowired
    private HelloBean helloBean;

    @Override
   public void executeInternal(JobExecutionContext var1) throws JobExecutionException{
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        System.out.println(simpleDateFormat.format(date) + " SpringJob executeInternal()");

        //调用JobDataMap数据
        helloBean.fun();
    }

}
