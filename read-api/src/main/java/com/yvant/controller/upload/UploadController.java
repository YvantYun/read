package com.yvant.controller.upload;

import com.yvant.common.CommonResult;
import com.yvant.service.fastdfs.IFastdfsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2020-03-30
 */

@RestController
@RequestMapping("/fastdfs")
public class UploadController {

    @Autowired
    private IFastdfsService fastdfsService;

    @Autowired
    private FileResource fileResource;

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        String path = "";

        // 开始文件上传
        if (file != null) {
            // 获得文件上传的文件名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                // 文件重命名  imooc-face.png -> ["imooc-face", "png"]
                String fileNameArr[] = fileName.split("\\.");
                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];
                if (!suffix.equalsIgnoreCase("png") &&
                        !suffix.equalsIgnoreCase("jpg") &&
                        !suffix.equalsIgnoreCase("jpeg")) {
                    return CommonResult.failed("图片格式不正确！");
                }

                path = fileResource.getHost() + fastdfsService.upload(file, suffix);
                return CommonResult.success(path);

            } else {
                return CommonResult.failed("文件不能为空！");
            }
        }
        return CommonResult.failed();
    }
}
