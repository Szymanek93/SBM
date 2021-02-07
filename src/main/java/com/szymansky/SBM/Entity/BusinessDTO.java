package com.szymansky.SBM.Entity;

import lombok.*;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDTO {

    private Long businessId;
    private String businessType;
    private String businessName;
    private String businessCity;
    private Integer businessPostCode;
    private String businessStreet;
    private String businessHouseNumber;
    private String businessDetails;
    private Long ownerId;
}
