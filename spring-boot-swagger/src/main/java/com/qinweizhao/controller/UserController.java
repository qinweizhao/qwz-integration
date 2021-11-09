package com.qinweizhao.controller;


import com.qinweizhao.common.DataType;
import com.qinweizhao.common.ParamType;
import com.qinweizhao.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinweizhao
 * @since 2021/10/13
 */
@Api(tags = "用户管理")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {


    /**
     * 多个参数用  @ApiImplicitParams
     *
     * @param current 当前页
     * @param size    大小
     * @return list
     */
    @ApiOperation(value = "查询用户", notes = "备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = DataType.INT, paramType = ParamType.QUERY),
            @ApiImplicitParam(name = "size", value = "大小", dataType = DataType.INT, paramType = "path")
    })
    @GetMapping("/list")
    public List<User> list(String current, String size) {
        System.out.println(current + "" + size);
        return new ArrayList<>();
    }


    /**
     * 单个参数用 ApiImplicitParam
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "备注")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = DataType.INT, paramType = ParamType.PATH)
    public void delete(@PathVariable Integer id) {
        System.out.println(id);
    }


    /**
     * 如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam
     *
     * @param user user
     * @return user
     */
    @PostMapping
    @ApiOperation(value = "添加用户")
    public User post(@RequestBody User user) {
        return user;
    }


    /**
     * 如果不写 @ApiImplicitParam ，swagger 会使用默认的参数名作为描述信息
     *
     * @param id   id
     * @param user user
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户")
    public void put(@PathVariable Long id, @RequestBody User user) {
        System.out.println(id + "" + user);
    }
}
