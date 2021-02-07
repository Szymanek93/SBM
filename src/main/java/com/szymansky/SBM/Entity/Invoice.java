package com.szymansky.SBM.Entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    // faktura wystawiona
    private Boolean issued;
    //mfaktura zaksiegowana
    private Boolean booked;
    private Long price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id", referencedColumnName = "Id")
    private Report report;

}
