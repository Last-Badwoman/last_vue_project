package com.last.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.last.edu.entity.Subject;
import com.last.edu.entity.vo.SubjectVo;
import com.last.edu.service.SubjectService;
import com.last.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author last
 * @since 2021-10-03
 */
@Api(value = "last/service/subject", tags = "课程管理")
@RestController
@RequestMapping("last/service/subject")
@CrossOrigin
public class SubjectController {


    @Autowired
    private SubjectService subjectService;


    @ApiOperation(value = "课程分类上传", notes = "只支持excel格式", response = Response.class)
    @PostMapping("upload")
    public Response upload(@ApiParam(name = "file", value = "excel文件", required = true) @RequestParam("file") MultipartFile file) {
        try {
            subjectService.saveExcel(subjectService, file);
            return Response.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error().message(e.getMessage());
        }
    }


    @ApiOperation(value = "添加分类", notes = "暂只添加一二两级", response = Response.class)
    @GetMapping("save")
    public Response save(@ApiParam(name = "pid", value = "父级id", required = true) @RequestParam("pid") String pid,
                         @ApiParam(name = "name", value = "分类名", required = true) @RequestParam("name") String name) {
        if (StringUtils.isBlank(pid) || StringUtils.isBlank(name)) {
            return Response.error().message("提供信息不完整 !");
        }
        Subject subject = new Subject();
        subject.setParentId(pid);
        subject.setTitle(name);
        subjectService.save(subject);
        return Response.ok();
    }

    @ApiOperation(value = "查询课程分类", notes = "查询全部", response = Response.class, produces = "application/json")
    @GetMapping("find/{pid}")
    public Response findSubjectAll(@ApiParam(name = "pid", value = "父级id", defaultValue = "0") @PathVariable("pid") String pid) {
        List<SubjectVo> subjects = subjectService.findAll(pid);
        return subjects != null ? Response.ok().data("items", subjects) : Response.error();
    }

    @ApiOperation(value = "根据主键删除课程", notes = "被删除课程不允许存在子级", response = Response.class)
    @DeleteMapping("del/{id}")
    public Response deleteSubjectById(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable("id") String id) {
        if (StringUtils.isNotBlank(id)) {
            QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            return subjectService.remove(queryWrapper) ? Response.ok() : Response.error();
        }
        return Response.error();
    }
}

