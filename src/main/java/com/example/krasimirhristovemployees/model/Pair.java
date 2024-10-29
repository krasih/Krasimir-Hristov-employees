package com.example.krasimirhristovemployees.model;

public class Pair {

    private int firstEmployeeId;
    private int secondEmployeeId;

    public Pair(int firstEmployeeId, int secondEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
    }

    public int getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public int getSecondEmployeeId() {
        return secondEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (firstEmployeeId != pair.firstEmployeeId) return false;
        return secondEmployeeId == pair.secondEmployeeId;
    }

    @Override
    public int hashCode() {
        int result = firstEmployeeId;
        result = 31 * result + secondEmployeeId;
        return result;
    }
}
