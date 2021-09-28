package com.last.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 22514
 */
public interface AliOssService {

    /**
     * 文件图片上传
     * @param file
     * @return List<String>
     */
    List<String> upload(MultipartFile file);
}
