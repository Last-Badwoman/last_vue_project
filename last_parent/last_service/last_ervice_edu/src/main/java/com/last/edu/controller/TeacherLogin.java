package com.last.edu.controller;

import com.last.edu.entity.AclUser;
import com.last.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author 22514
 */
@Slf4j
@Api(value = "last/teacher", tags = {"用户登录"})
@CrossOrigin
@RequestMapping("last/service")
@RestController
public class TeacherLogin {

    @ApiOperation(value = "用户管理员登录", notes = "暂时性的数据写死",
            response = Response.class)
    @PostMapping("login")
    public Response login(@ApiParam(name = "user", value = "用户信息", required = true) @RequestBody AclUser aclUser) {
        log.info("user: {}",aclUser);
        return Response.ok().data("token", UUID.randomUUID().toString());
    }

    @ApiOperation(value = "获取用户信息", notes = "暂时性的数据写死", response = Response.class)
    @GetMapping("info")
    public Response info(@ApiParam(name = "token", value = "token信息", required = true) @RequestParam("token") String token) {
        log.info("token: {}", token);
        return Response.ok().data("roles", "[admin]")
                .data("name", "last")
                .data("avatar", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0160615c8a01bca801214168c92f5e.gif&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1635317715&t=e9b8fa3f98483c396cdba37da549284f");
    }
}
