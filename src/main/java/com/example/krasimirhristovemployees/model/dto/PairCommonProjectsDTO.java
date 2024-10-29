package com.example.krasimirhristovemployees.model.dto;

public class PairCommonProjectsDTO {

    private int firstEmployeeId;
    private int secondEmployeeId;
    private int projectId;
    private long daysWorkedTogether;

    public PairCommonProjectsDTO(int firstEmployeeId, int secondEmployeeId, int projectId, long daysWorkedTogether) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.projectId = projectId;
        this.daysWorkedTogether = daysWorkedTogether;
    }

    public int getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public int getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDaysWorkedTogether() {
        return daysWorkedTogether;
    }
}
