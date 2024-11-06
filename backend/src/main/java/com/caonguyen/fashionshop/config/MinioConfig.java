package com.caonguyen.fashionshop.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.host}")
    private String host;

    @Value("${minio.port}")
    private int port;

    @Value("${minio.key.access}")
    private String accessKey;

    @Value("${minio.key.secret}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(host, port, false)
                .credentials(accessKey, secretKey)
                .build();
    }
}
