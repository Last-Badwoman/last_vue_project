package com.last.edu.service;

import com.last.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.last.edu.entity.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author last
 * @since 2021-10-03
 */
public interface SubjectService extends IService<Subject> {

    /**
     * excel添加课程分类
     *
     * @param subjectService
     * @param file
     */
    void saveExcel(SubjectService subjectService, MultipartFile file);

    /**
     * 查询全部课程分类
     * @return List<Subject>
     */
    List<SubjectVo> findAll(String pid);
}
