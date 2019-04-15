package com.zle.controller;

import com.zle.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *tags 分组,以及目录
 */
@Api(tags = {"用户模块"},description = "用户服务接口")
@RestController
@RequestMapping("/demoController")
public class DemoController {
    //设置了返回类型后response以设置的为准,方法返回值不生效
    @ApiOperation(value = "新增用户1" ,  notes="说明:XXX",response = UserVo.class)
    @RequestMapping(value="/createUser",method=RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResObject createUser(@RequestBody @ApiParam(value = "新建用户对象", required = true)User user){
        System.out.println("createUser:::"+user.toString());
        return new ResObject(HttpStatus.OK.value(), "新增成功.");
    }

    @ApiOperation(value = "修改用户1" ,  notes="修改用户")
    @RequestMapping(value="/updateUser",method=RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResObject updateUser(@RequestBody UserVo user){
        System.out.println("updateUser:::"+user.toString());
        return new ResObject(HttpStatus.OK.value(), "修改成功.");
    }

    @ApiOperation(value = "删除用户" ,  notes="删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户标识", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value="/deleteUser",method=RequestMethod.DELETE)
    public ResObject deleteUser(@RequestParam("userId") String userId){
        System.out.println("deleteUser:::"+userId);
        return new ResObject(HttpStatus.OK.value(), "删除成功.");
    }

    @ApiOperation(value = "查询用户" ,  notes="查询用户")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @RequestMapping(value="/queryUser/{id}",method=RequestMethod.GET)
    public ResObject queryUser(@ApiParam(value = "用户id", allowableValues = "range[1,5]", required = true) @PathVariable("id") String userId){
        System.out.println("queryUser:::"+userId);
        User user = new User(userId, "张三");
        return new ResObject(HttpStatus.OK.value(), user);
    }

}
