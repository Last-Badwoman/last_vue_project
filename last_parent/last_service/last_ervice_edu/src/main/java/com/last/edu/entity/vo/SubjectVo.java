package com.last.edu.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 22514
 */
@Data
public class SubjectVo {

    private String id;

    private String label;

    private List<SubjectVo> children;
}
