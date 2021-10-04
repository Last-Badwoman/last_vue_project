package com.last.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.last.edu.entity.Subject;
import com.last.edu.entity.excel.SubjectData;
import com.last.edu.entity.vo.SubjectVo;
import com.last.edu.listener.SubjectDataListener;
import com.last.edu.mapper.SubjectMapper;
import com.last.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author last
 * @since 2021-10-03
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void saveExcel(SubjectService subjectService, MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectDataListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取课程excel error!");
        }
    }

    @Override
    public List<SubjectVo> findAll(String pid) {
        return subjectMapper.findAll(pid);
    }
}
