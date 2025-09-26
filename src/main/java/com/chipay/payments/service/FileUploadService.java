package com.chipay.payments.service;

import com.chipay.payments.util.ClamAVScanner;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileUploadService {

    private final S3Client s3Client;
    private final ClamAVScanner clamAVScanner;

    public FileUploadService() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1) // ✅ choose your region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        this.clamAVScanner = new ClamAVScanner("localhost", 3310); // ✅ configure host/port
    }

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        // 1. Scan file
        boolean clean = clamAVScanner.scan(file.getInputStream());
        if (!clean) {
            throw new RuntimeException("Virus detected! Upload aborted.");
        }

        // 2. Save to temp file
        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        // 3. Upload to S3
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getOriginalFilename())
                .build();

        s3Client.putObject(putRequest, tempFile);

        return "File uploaded successfully: " + file.getOriginalFilename();
    }
}
