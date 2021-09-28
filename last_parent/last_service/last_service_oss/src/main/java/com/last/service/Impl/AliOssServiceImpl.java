package com.last.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.last.config.ConstantOssConfig;
import com.last.service.AliOssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author 22514
 */
@Slf4j
@Service
public class AliOssServiceImpl implements AliOssService {

    @Override
    public List<String> upload(MultipartFile file) {
        String checkFile = checkFile(file);
        if (!"success".equals(checkFile)) {
            return Arrays.asList("文件格式不对 !", "");
        }
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantOssConfig.ENDPOINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantOssConfig.KEY_ID;
        String accessKeySecret = ConstantOssConfig.KEY_SECRET;
        String bucketName = ConstantOssConfig.BUCKET_NAME;
        OSS ossClient = null;
        String url = null;
        String fileName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12) + "-" + file.getOriginalFilename();
        fileName = LocalDate.now().getYear() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getDayOfMonth() + "/" + LocalTime.now().getHour() + "/" + fileName;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, fileName, inputStream);

            url = "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            log.error("oss图片上传失败: {}", e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
        return Arrays.asList("success", url);
    }

    public String checkFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return null;
        }
        int index = filename.indexOf(".");
        String suffix = filename.substring(index + 1);
        List<String> suffixCheck = Arrays.asList("txt", "exe", "ppt", "pptx", "xls", "xlsx", "doc", "docx");
        if (suffixCheck.contains(suffix)) {
            return null;
        }
        return "success";
    }

}
