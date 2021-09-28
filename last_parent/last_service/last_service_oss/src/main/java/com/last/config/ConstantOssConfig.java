package com.last.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 22514
 */
@Data
@ApiModel(value = "oss", description = "阿里云oss图片配置")
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class ConstantOssConfig implements InitializingBean {

    /**
     * 服务地址
     */
    private String endPoInt;

    /**
     * AccessKey
     */
    private String keyId;
    private String keySecret;

    /**
     * 存储目录
     */
    private String bucketName;


    public static String ENDPOINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endPoInt;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
