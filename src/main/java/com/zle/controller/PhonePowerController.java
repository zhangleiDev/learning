package com.zle.controller;

import com.google.common.base.Strings;
import com.zle.entity.db.PhonePowerEntity;
import com.zle.service.PhonePowerService;
import com.zle.tools.SendDingMsg;
import com.zle.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController()
@RequestMapping("/zle")
public class PhonePowerController {

    @Autowired
    PhonePowerService phonePowerService;


    @PostMapping("power")
    public ResObject savePower(PhonePowerEntity entity){
        if(Strings.isNullOrEmpty(entity.getPower())){
            return new ResObject(HttpStatus.OK.value(), "失败：电量为空");
        }
        if(Integer.parseInt(entity.getPower())<=60){
            SendDingMsg.sendMsg("低电量警告:"+entity.getPower()+"%,时间:"+
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        phonePowerService.save(entity);
        return new ResObject(HttpStatus.OK.value(), "新增成功.");
    }
    @GetMapping("power")
    public Map queryPower(Integer page, Integer size){

        return phonePowerService.queryForpage(page,size);
    }


}
