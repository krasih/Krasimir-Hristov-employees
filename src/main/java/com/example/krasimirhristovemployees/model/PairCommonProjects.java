package com.example.krasimirhristovemployees.model;

import java.time.Duration;
import java.time.Instant;

public class PairCommonProjects {

    private Pair pair;
    private int projectId;
    private long daysWorkedTogether;

    public PairCommonProjects(EmployeeProject p1, EmployeeProject p2) {

        int firstEmpId = Math.min(p1.getEmpId(), p2.getEmpId());
        int secondEmpId = Math.max(p1.getEmpId(), p2.getEmpId());
        this.pair = new Pair(firstEmpId, secondEmpId);
        this.projectId = p1.getProjectId();
        this.daysWorkedTogether = calculateOverlapDays(p1.getDateFrom(), p1.getDateTo(), p2.getDateFrom(), p2.getDateTo());
    }

    private long calculateOverlapDays(Instant start1, Instant end1, Instant start2, Instant end2) {

        // Ensure start1 is before end1 and start2 is before end2
        if (start1.isAfter(end1) || start2.isAfter(end2)) {
            return 0; // No overlap if start is after end
        }

        // Find the later start and earlier end
        Instant laterStart = start1.isAfter(start2) ? start1 : start2;
        Instant earlierEnd = end1.isBefore(end2) ? end1 : end2;

        // Calculate the overlap duration in milliseconds
        if (laterStart.isAfter(earlierEnd)) {
            return 0; // No overlap
        }

        Duration overlapDuration = Duration.between(laterStart, earlierEnd);

        // Convert the overlap duration to days
        long overlapDays = overlapDuration.toDays();

        return overlapDays;
    }

    public int getFirstEmployeeId() {

        return pair.getFirstEmployeeId();
    }

    public int getSecondEmployeeId() {

        return pair.getSecondEmployeeId();
    }

    public Pair getPair() {
        return pair;
    }

    public int getProjectId() {

        return projectId;
    }

    public long getDaysWorkedTogether() {

        return daysWorkedTogether;
    }
}
