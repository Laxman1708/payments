package com.chipay.payments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.*;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3StorageService {

    private final S3Client s3Client;
    private final software.amazon.awssdk.regions.Region region =
            software.amazon.awssdk.regions.Region.of(System.getProperty("aws.s3.region", "us-east-1"));

    @org.springframework.beans.factory.annotation.Value("${aws.s3.bucket}")
    private String bucket;

    @org.springframework.beans.factory.annotation.Value("${aws.s3.upload-prefix}")
    private String prefix;

    @org.springframework.beans.factory.annotation.Value("${aws.s3.server-side-encryption:AES256}")
    private String sse;

    @org.springframework.beans.factory.annotation.Value("${aws.s3.presigned-url-ttl-seconds:3600}")
    private int presignTtlSeconds;

    public String upload(byte[] bytes, String contentType, String originalFilename) {
        String key = prefix + UUID.randomUUID().toString() + "-" + sanitizeFilename(originalFilename);
        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .serverSideEncryption(sse)
                .build();

        s3Client.putObject(putReq, RequestBody.fromBytes(bytes));
        log.info("Uploaded object to s3://{}/{}", bucket, key);
        return key;
    }

    public String generatePresignedUrl(String key) {
        try (S3Presigner presigner = S3Presigner.builder().region(region).build()) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofSeconds(presignTtlSeconds))
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presigned = presigner.presignGetObject(presignRequest);
            return presigned.url().toString();
        }
    }

    private String sanitizeFilename(String filename) {
        if (filename == null) return "file";
        return filename.replaceAll("[^a-zA-Z0-9.\\-_]", "_");
    }
}
