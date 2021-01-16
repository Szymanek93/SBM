package com.szymansky.SBM.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDTO{

    private Long id;
    private String businessType;
    private String businessName;
    private String businessCity;
    private Integer businessPostCode;
    private String businessStreet;
    private String businessHouseNumber;
    private String businessDetails;
    private Long ownerId;
}
