package com.szymansky.SBM.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Employee employeeId;

    @OneToOne
    private Business businessId;

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

