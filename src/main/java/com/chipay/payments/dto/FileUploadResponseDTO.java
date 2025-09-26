package com.chipay.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response returned to UI after scanning & upload
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDTO {

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("scanStatus")
    private String scanStatus; // CLEAN | INFECTED | ERROR

    @JsonProperty("message")
    private String message;

    @JsonProperty("s3ObjectKey")
    private String s3ObjectKey; // present if uploaded

    @JsonProperty("presignedUrl")
    private String presignedUrl; // optional
}
