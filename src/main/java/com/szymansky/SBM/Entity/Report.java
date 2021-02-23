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
    @Column(name = "CompletedTasks")
    private String completedTasks;
    @Temporal(TemporalType.DATE)
    @Column(name = "WorksDate")
    private Date worksDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "DevelopmentDate")
    private Date developmentDate;
    @Column(name = "PointsAmount")
    private Long pointsAmount;
    @Column(name = "OtherTasks")
    private String otherTasks;
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name="business_id", referencedColumnName = "id")
    private Business business;
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

