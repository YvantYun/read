package com.yvant.service.fastdfs;

import org.springframework.web.multipart.MultipartFile;

public interface IFastdfsService {

    String upload(MultipartFile file, String fileExtName) throws Exception;
}