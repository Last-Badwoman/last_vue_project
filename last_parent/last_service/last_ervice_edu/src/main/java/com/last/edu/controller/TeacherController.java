package com.last.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.last.edu.entity.LastException;
import com.last.edu.entity.Teacher;
import com.last.edu.entity.TeacherQuery;
import com.last.edu.service.TeacherService;
import com.last.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Get;
import org.apache.commons.collections4.Put;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author last
 * @since 2021-09-25
 */
@CrossOrigin
@Api(value = "last/service", tags = {"讲师管理"})
@RestController
@RequestMapping("last/service/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "查询所有讲师列表",
            notes = "查询所有讲师列表,不包含逻辑删除的讲师",
            response = Response.class, produces = "application/json")
    @GetMapping("findAll")
    public Response findAll() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new LastException(20001, "自定义异常出现");
        }
        List<Teacher> teachers = teacherService.list(null);
        return CollectionUtils.isNotEmpty(teachers) ? Response.ok().data("items", teachers) : Response.error();
    }

    @ApiOperation(value = "根据主键id删除讲师",
            notes = "根据主键id删除讲师,目前尚无对不存在id进行判断",
            response = Response.class)
    @DeleteMapping("del/{id}")
    public Response delTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        return teacherService.removeById(id) ? Response.ok() : Response.error();
    }

    @ApiOperation(value = "分页查询讲师信息",
            notes = "根据page和limit查询讲师分页数据",
            response = Response.class, produces = "application/json")
    @PostMapping("find/{page}/{limit}")
    public Response findPage(@ApiParam(name = "page", value = "分页", required = false, defaultValue = "1") @PathVariable("page") Integer page,
                             @ApiParam(name = "limit", value = "条数", required = false, defaultValue = "5") @PathVariable("limit") Integer limit,
                             @ApiParam(name = "teacherQuery", value = "查询条件", required = false, type = "application/json") @RequestBody TeacherQuery teacherQuery) {

        System.out.println(teacherQuery);
        Page<Teacher> teacherPage = new Page<>(page, limit);
        teacherService.pageQuery(teacherPage, teacherQuery);
        return Response.ok().data("total", teacherPage.getTotal()).data("rows", teacherPage.getRecords());
    }


    @ApiOperation(value = "添加讲师信息", notes = "暂无特定要求",
            response = Response.class, consumes = "application/json")
    @PostMapping("save")
    public Response save(@ApiParam(name = "teacher", value = "讲师信息", required = true) @RequestBody Teacher teacher) {
        if (!Objects.isNull(teacher)) {
            return teacherService.save(teacher) ? Response.ok() : Response.error();
        }
        return Response.error().message("添加不能信息为空");
    }

    @ApiOperation(value = "根据id查询讲师信息", response = Response.class, produces = "application/json")
    @GetMapping("find/{id}")
    public Response findTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Teacher teacher = teacherService.getOne(queryWrapper);
        return teacher != null ? Response.ok().data("item", teacher) : Response.error();
    }

    @ApiOperation(value = "修改讲师信息", notes = "暂无特定要求", response = Response.class, consumes = "application/json")
    @PostMapping("update")
    public Response updateTeacher(@ApiParam(name = "teacher", value = "讲师信息", required = true)
                                  @RequestBody Teacher teacher) {
        if (Objects.nonNull(teacher)) {
            return teacherService.updateById(teacher) ? Response.ok() : Response.error();
        }
        return Response.error().message("修改信息不能为空!");
    }
}

