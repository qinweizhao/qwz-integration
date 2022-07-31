package com.qinweizhao.minio.config;

import com.qinweizhao.minio.config.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qinweizhao
 * @since 2022/7/31
 */
@EnableConfigurationProperties(MinioProperties.class)
@AllArgsConstructor
@Configuration
public class MinioConfig {


    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getEndpoint(), minioProperties.getPort(), minioProperties.isSecure())
                .build();
    }
}
