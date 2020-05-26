package com.zle.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.zle.dao.PhonePowerDao;
import com.zle.entity.db.PhonePowerEntity;
import com.zle.entity.db.PhonePowerEntityExample;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PhonePowerService {

    @Autowired
    PhonePowerDao phonePowerDao;

    public void save(PhonePowerEntity entity){
        phonePowerDao.insert(entity);
    }
    public Map<String,Object> queryForpage(Integer pageNo,Integer size){

        Map<String,Object> result= Maps.newHashMap();

        Page<PhonePowerEntity> page = new Page(pageNo,size);

        QueryWrapper<PhonePowerEntity> wrapper = new QueryWrapper();

        IPage iPage = phonePowerDao.selectMapsPage(page, wrapper);
        result.put("rows",iPage.getRecords());
        result.put("total",iPage.getTotal());
        return result;
    }
    public PhonePowerEntity getLatest(){
        return phonePowerDao.selectLatestOne();
    }

}
