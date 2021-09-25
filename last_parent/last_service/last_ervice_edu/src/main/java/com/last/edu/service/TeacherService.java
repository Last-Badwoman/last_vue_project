package com.last.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.last.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.last.edu.entity.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author last
 * @since 2021-09-25
 */
public interface TeacherService extends IService<Teacher> {


    /**
     * 分页条件查询讲师信息
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
