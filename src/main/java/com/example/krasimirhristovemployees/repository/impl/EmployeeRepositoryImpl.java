package com.example.krasimirhristovemployees.repository.impl;

import com.example.krasimirhristovemployees.model.EmployeeProject;
import com.example.krasimirhristovemployees.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final String csvFile = "src/main/resources/static/employee_data.csv";

    @Override
    public Map<Integer, List<EmployeeProject>> findAllEmployeeProjects() {

        // projectID, Employees
        Map<Integer, List<EmployeeProject>> employeeProjects = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(", ");

                int empId = Integer.parseInt(values[0]);
                int projectId = Integer.parseInt(values[1]);
                Instant dateFrom = parseInstant(values[2]);
                Instant dateTo = values[3].equals("NULL") ? getTodayAsInstant() : parseInstant(values[3]);

                employeeProjects.putIfAbsent(projectId, new ArrayList<>());
                if (employeeProjects.containsKey(projectId)) {
                    employeeProjects.get(projectId).add(new EmployeeProject()
                            .setEmpId(empId).setProjectId(projectId).setDateFrom(dateFrom).setDateTo(dateTo));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return employeeProjects;
    }

    private Instant parseInstant(String dateString) throws ParseException {

        // Create a DateTimeFormatter for the given date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the string to a LocalDate
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        // Convert LocalDate to Instant at the start of the day in UTC
        Instant instant = localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();

        return instant;
    }

    private Instant getTodayAsInstant() {

        // Get today's date as LocalDate
        LocalDate today = LocalDate.now();

        // Convert LocalDate to Instant at the start of the day in UTC
        Instant todayInstant = today.atStartOfDay(ZoneId.of("UTC")).toInstant();

        return todayInstant;
    }
}
