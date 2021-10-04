package com.last.edu.mapper;

import com.last.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.last.edu.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author last
 * @since 2021-10-03
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 查询全部课程分类
     * @param pid
     * @return List<Subject>
     */
    List<SubjectVo> findAll(String pid);
}
