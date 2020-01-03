package com.algorithm.controller;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author renhao
 * @Date 2020/1/2 14:27
 **/
import com.algorithm.common.CommonUtils;
import com.algorithm.dto.MamaUser;
import com.algorithm.service.IUserService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/mama/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "显示用户" )
    @GetMapping("/getUser")
    public List<MamaUser> getUser() throws Exception {
        return userService.getUser();
    }

    @ApiOperation(value = "删除用户" )
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) throws Exception {
        userService.deleteUser(id);
        return "你已经删掉了id为"+id+"的用户";
    }

    @ApiOperation(value = "增加用户" )
    @PostMapping("/addUser")
    public String addUser() throws Exception {
        MamaUser user = new MamaUser();
        user.setUid( CommonUtils.get32UUID() );
        user.setLoginname( "woshixiaodonghong" );
        user.setPasswd( "passwd" );
        user.setName( "我是肖东红" );
        user.setCreateTime( new Date(  ) );
        user.setUpdateTime( new Date(  ) );
        user.setDescription( "测试用户我是肖东红" );
        user.setAddress( "北京市海淀区丰秀中路3号院1号楼" );
        userService.addUser(user);
        return "增加用户";
    }
}
