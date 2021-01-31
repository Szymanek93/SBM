package com.szymansky.SBM.Entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OwnerDTO {
   //private Long id;
    private String ownerName;
    private String ownerCity;
    private Integer ownerPostCode;
    private String ownerStreet;
    private String ownerHouseNumber;
    private Long ownerPhone;

}
