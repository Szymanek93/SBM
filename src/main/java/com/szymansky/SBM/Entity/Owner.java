package com.szymansky.SBM.Entity;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="Name", nullable=true)
    private String ownerName;
    @Column(name="City", nullable=true)
    private String ownerCity;
    @Column(name="PostCode", nullable=true)
    private Integer ownerPostCode;
    @Column(name="Street", nullable=true)
    private String ownerStreet;
    @Column(name="HouseNumber", nullable=true)
    private String ownerHouseNumber;
    @Column(name="PhoneNumber")
    private  Long ownerPhone;

    //private String ownerBusiness;
//    @OneToMany (mappedBy = "businessOwnerId")
//    private List<Business> ownerBusinessId;



}
