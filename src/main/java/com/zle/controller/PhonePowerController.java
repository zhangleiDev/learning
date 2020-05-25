package com.zle.controller;

import com.zle.entity.db.PhonePowerEntity;
import com.zle.service.PhonePowerService;
import com.zle.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/zle")
public class PhonePowerController {

    @Autowired
    PhonePowerService phonePowerService;


    @PostMapping("power")
    public ResObject savePower(PhonePowerEntity entity){
        System.out.println("createUser:::"+entity.getPower());
        phonePowerService.save(entity);
        return new ResObject(HttpStatus.OK.value(), "新增成功.");
    }
    @GetMapping("power")
    public Map queryPower(Integer page, Integer size){

        return phonePowerService.queryForpage(page,size);
    }


}
