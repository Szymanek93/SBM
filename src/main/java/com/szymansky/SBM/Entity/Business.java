package com.szymansky.SBM.Entity;


import lombok.*;

import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long Id;

//    @ManyToOne (optional = false)
//    @JoinColumn(name="Owner_Id")
//    private Business businessOwnerId;
    @OneToOne
    private Owner owner;

    @Column(name = "Type", nullable = true)
    private String businessType;
    @Column(name = "Name", nullable = true)
    private String businessName;
    @Column(name = "City", nullable = true)
    private String businessCity;
    @Column(name = "PostCode", nullable = true)
    private Integer businessPostCode;
    @Column(name = "Street", nullable = true)
    private String businessStreet;
    @Column(name = "HouseNumber", nullable = true)
    private String businessHouseNumber;
    @Column(name = "Details", nullable = true)
    private String businessDetails;

    public String getOwnerName() {
        return getBusinessName();
    }
}


