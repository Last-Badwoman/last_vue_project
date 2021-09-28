package com.last.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.last.edu.entity.Subject;
import com.last.edu.entity.excel.SubjectData;
import com.last.edu.service.SubjectService;

/**
 * @author 22514
 */
public class SubjectDataListener extends AnalysisEventListener<SubjectData> {

    private SubjectService subjectService;

    public SubjectDataListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new RuntimeException("excel read is null");
        }

        Subject oneCeSubject = existsOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneCeSubject == null) {
            oneCeSubject = new Subject();
            oneCeSubject.setTitle(subjectData.getOneSubjectName());
            oneCeSubject.setParentId("0");
            subjectService.save(oneCeSubject);
        }

        String pid = oneCeSubject.getId();
        Subject twoCeSubject = existsTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoCeSubject == null) {
            twoCeSubject = new Subject();
            twoCeSubject.setTitle(subjectData.getTwoSubjectName());
            twoCeSubject.setParentId(pid);
            subjectService.save(twoCeSubject);
        }

    }


    public Subject existsOneSubject(SubjectService subjectService, String subjectName) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", subjectName);
        queryWrapper.eq("parent_id", "0");
        return subjectService.getOne(queryWrapper);
    }

    public Subject existsTwoSubject(SubjectService subjectService, String subjectName, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", subjectName);
        queryWrapper.eq("parent_id", parentId);
        return subjectService.getOne(queryWrapper);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
