package com.zle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel(description="用户对象user")
@Data
public class UserVo {

    @ApiModelProperty(value="用户id",required=true,example="132")
    private String userId;
    @ApiModelProperty(value="用户名称",required=true)
    private String name;
    @ApiModelProperty(value="性别,1:男,2:女",required=true,allowableValues ="1,2")
    private Integer sex;

}
