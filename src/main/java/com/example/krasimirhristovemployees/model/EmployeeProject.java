package com.example.krasimirhristovemployees.model;

import java.time.Instant;

public class EmployeeProject {

    private int empId;
    private int projectId;
    private Instant dateFrom;
    private Instant dateTo;

    public EmployeeProject() {}

    public EmployeeProject setEmpId(int empId) {
        this.empId = empId;
        return this;
    }

    public EmployeeProject setProjectId(int projectId) {
        this.projectId = projectId;
        return this;
    }

    public EmployeeProject setDateFrom(Instant dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public EmployeeProject setDateTo(Instant dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public int getEmpId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public Instant getDateFrom() {
        return dateFrom;
    }

    public Instant getDateTo() {
        return dateTo;
    }
}
