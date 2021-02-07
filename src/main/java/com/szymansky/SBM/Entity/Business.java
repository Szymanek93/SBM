package com.szymansky.SBM.Entity;


import lombok.*;

import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

//    @ManyToOne (optional = false)
//    @JoinColumn(name="Owner_Id")
//    private Business businessOwnerId;


    @Column(name = "Type")
    private String businessType;
    @Column(name = "Name")
    private String businessName;
    @Column(name = "City")
    private String businessCity;
    @Column(name = "PostCode")
    private Integer businessPostCode;
    @Column(name = "Street")
    private String businessStreet;
    @Column(name = "HouseNumber")
    private String businessHouseNumber;
    @Column(name = "Details")
    private String businessDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    private Owner owner;
//    public String getOwnerName() {
//        return getBusinessName();
//    }


}


