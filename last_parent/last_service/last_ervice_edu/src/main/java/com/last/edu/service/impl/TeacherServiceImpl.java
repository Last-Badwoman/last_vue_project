package com.last.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.last.edu.entity.Teacher;
import com.last.edu.entity.TeacherQuery;
import com.last.edu.mapper.TeacherMapper;
import com.last.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author last
 * @since 2021-09-25
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");

        if (teacherQuery == null) {
            baseMapper.selectPage(pageParam, null);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (StringUtils.isNoneBlank(name)) {
            wrapper.like("name", name);
        }

        if (level != null) {
            wrapper.eq("level", level);
        }

        if (StringUtils.isNoneBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (StringUtils.isNoneBlank(end)) {
            wrapper.le("gmt_modified", end);
        }

        baseMapper.selectPage(pageParam, wrapper);
    }
}
