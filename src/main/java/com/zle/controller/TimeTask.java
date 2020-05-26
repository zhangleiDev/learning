package com.zle.controller;

import com.zle.entity.db.PhonePowerEntity;
import com.zle.service.PhonePowerService;
import com.zle.tools.SendDingMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeTask {

    @Autowired
    PhonePowerService phonePowerService;

    @Scheduled(cron= "0 */30 * * * ?")
    public void check(){
        PhonePowerEntity latest = phonePowerService.getLatest();
        if(latest == null){
            return;
        }
        long lon = new Date().getTime() - latest.getCreateTime().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(lon > 1*60*60*1000 && lon < 3*60*60*1000){
            SendDingMsg.sendMsg("警告:app停止工作，最后一次上报时间:"
                    +sdf.format(latest.getCreateTime())+",手机电量剩余:"+latest.getPower()+"%,当前时间:"
            +sdf.format(new Date()));
        }
        System.out.println(sdf.format(latest.getCreateTime()));

    }
}

