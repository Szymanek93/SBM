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
    private String completedTasks;
    @Temporal(TemporalType.DATE)
    private Date worksDate;
    @Temporal(TemporalType.DATE)
    private Date developmentDate;
    private Long pointsAmount;
    private String otherTasks;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;
    @OneToOne (cascade = CascadeType.ALL)
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

