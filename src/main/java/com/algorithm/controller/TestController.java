package com.algorithm.controller;

import com.algorithm.vo.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "test相关接口")
@RequestMapping("/test")
public class TestController {
    @PostMapping("/")
    @ApiOperation("添加示例的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    })
    public ResponseModel<Demo> addUser( @RequestParam(required = true)String username, @RequestParam(required = true) String address) {
        ResponseModel<Demo> responseModel = new ResponseModel<Demo>();
        Demo demo = new Demo();
        demo.setId( "id" );
        demo.setPassword( address );
        demo.setUserName( username);
        responseModel.setStatus( 200 );
        responseModel.setData( demo );
        return responseModel;
    }

    //  @ApiImplicitParam 注解用来描述一个参数，可以配置参数的中文含义，也可以给参数设置默认值，这样在接口测试的时候可以避免手动输入。
    //    如果有多个参数，则需要使用多个 @ApiImplicitParam 注解来描述，多个 @ApiImplicitParam 注解需要放在一个 @ApiImplicitParams 注解中。
//    需要注意的是，@ApiImplicitParam 注解中虽然可以指定参数是必填的，但是却不能代替 @RequestParam(required = true) ，前者的必填只是在 Swagger2 框架内必填，抛弃了 Swagger2 ，这个限制就没用了，所以假如开发者需要指定一个参数必填，
//    @RequestParam(required = true) 注解还是不能省略。
    @GetMapping("/")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
    public ResponseModel<Demo> getUserById(@PathVariable Integer id) {
        ResponseModel<Demo> responseModel = new ResponseModel<Demo>();
        Demo demo = new Demo();
        demo.setId( "id" );
        demo.setPassword( "pwd" );
        demo.setUserName( "username" );
        responseModel.setStatus( 200 );
        responseModel.setData( demo );
        return responseModel;
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    public ResponseModel<Demo> updateUserById(@RequestBody Demo user) {
        ResponseModel<Demo> responseModel = new ResponseModel<Demo>();
        Demo demo = new Demo();
        demo.setId( "id" );
        demo.setPassword( "pwd" );
        demo.setUserName( "username" );
        responseModel.setStatus( 200 );
        responseModel.setData( demo );
        return responseModel;
    }
}
