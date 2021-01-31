package com.szymansky.SBM.Entity;


import lombok.*;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
   // private Long Id;
    private String employeeName;
    private String employeeLastName;
}
