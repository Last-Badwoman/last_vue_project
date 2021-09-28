package com.last.controller;

import com.last.service.AliOssService;
import com.last.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 22514
 */
@Api(value = "last/oss", tags = {"文件图片上传"})
@RequestMapping("last/oss")
@RestController
@CrossOrigin
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @ApiOperation(value = "oss图片上传", notes = "请注意上传格式,暂时只支持上传图片格式", response = Response.class)
    @PostMapping("upload")
    public Response upload(@ApiParam(name = "file", value = "图片文件", required = true) @RequestParam("file") MultipartFile file) {
        List<String> urlMsg = aliOssService.upload(file);
        if ("success".equals(urlMsg.get(0)) && StringUtils.isNoneBlank(urlMsg.get(1))) {
            return Response.ok().data("url", urlMsg.get(1));
        }
        return Response.error().message(urlMsg.get(0));
    }
}
