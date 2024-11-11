package com.caonguyen.fashionshop.services.other;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    @Autowired
    MinioClient minioClient;

    public String uploadFile(MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("products").object(fileName).stream(
                            file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build()
            );
            System.out.printf(fileName);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi upload file", e);
        }
    }
}
