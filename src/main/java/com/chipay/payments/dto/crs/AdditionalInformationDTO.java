package com.chipay.payments.dto.crs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalInformationDTO {

    @JsonProperty("Owner City")
    private String ownerCity;

    @JsonProperty("Violation Site")
    private String violationSite;

    @JsonProperty("Owner ZipCode")
    private String ownerZipCode;

    @JsonProperty("Original Fine")
    private String originalFine;

    @JsonProperty("Date Issued")
    private String dateIssued;

    @JsonProperty("Lic Plate State")
    private String licPlateState;

    @JsonProperty("Notice Number")
    private String noticeNumber;

    @JsonProperty("Ticket Number")
    private String ticketNumber;

    @JsonProperty("Owner Name")
    private String ownerName;

    @JsonProperty("Last Notice Date")
    private String lastNoticeDate;

    @JsonProperty("Collection Agency Name")
    private String collectionAgencyName;

    @JsonProperty("Vin Num")
    private String vinNum;

    @JsonProperty("Final Determination Amount")
    private String finalDeterminationAmount;

    @JsonProperty("Payment Plan Indicator")
    private String paymentPlanIndicator;

    @JsonProperty("License Plate Type")
    private String licensePlateType;

    @JsonProperty("Vehicle Make")
    private String vehicleMake;

    @JsonProperty("Notice Level Date")
    private String noticeLevelDate;

    @JsonProperty("Lic Plate Number")
    private String licPlateNumber;

    @JsonProperty("Violation Description")
    private String violationDescription;

    @JsonProperty("Ticket Queue")
    private String ticketQueue;

    @JsonProperty("Owner Address")
    private String ownerAddress;

    @JsonProperty("Fee Type")
    private String feeType;

    @JsonProperty("Owner State")
    private String ownerState;

    @JsonProperty("Notice Level")
    private String noticeLevel;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Ticket Type")
    private String ticketType;

    @JsonProperty("DL Number")
    private String dlNumber;
}
