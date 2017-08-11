package com.blabla.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Job类必须实现execute()方法
 * Created by panting1 on 2017/8/11.
 */

@Component
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext var1) throws JobExecutionException{
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date) + " HelloJob");
    }

    public void fun(){
        System.out.println("HelloJob fun()");
    }
}
