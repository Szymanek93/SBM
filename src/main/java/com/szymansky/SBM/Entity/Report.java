package com.szymansky.SBM.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="employeeId", referencedColumnName = "id")
    private Employee employee;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="businessId", referencedColumnName = "id")
    private Business business;

    private String completeTasks;
    @Temporal(TemporalType.DATE)
    private Date worksDate;
    @Temporal(TemporalType.DATE)
    private Date developmentDate;
    private Long pointsAmount;
    private String otherTasks;
}
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void InitializerWorks() {
//        completeTasks = new ArrayList<>();
//        completeTasks.add("Tyczenie");
//        completeTasks.add("Pomiar dodatkowy");
//        completeTasks.add("Aktualizacja");
//        completeTasks.add("Aktualizacja");
//    }

