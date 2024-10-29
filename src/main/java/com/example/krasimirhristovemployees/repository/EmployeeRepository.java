package com.example.krasimirhristovemployees.repository;

import com.example.krasimirhristovemployees.model.EmployeeProject;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {

    Map<Integer, List<EmployeeProject>> findAllEmployeeProjects();
}
