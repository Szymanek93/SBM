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
    @Column (name="Issued")
    private Boolean issued;
    //mfaktura zaksiegowana
    @Column (name="Booked")
    private Boolean booked;
    @Column (name="Price")
    private Long price;
    @OneToOne
    @JoinColumn(name = "report_id", referencedColumnName = "Id")
    private Report report;

}
