package com.example.krasimirhristovemployees.service.impl;

import com.example.krasimirhristovemployees.model.EmployeeProject;
import com.example.krasimirhristovemployees.model.Pair;
import com.example.krasimirhristovemployees.model.PairCommonProjects;
import com.example.krasimirhristovemployees.model.dto.PairCommonProjectsDTO;
import com.example.krasimirhristovemployees.repository.EmployeeRepository;
import com.example.krasimirhristovemployees.service.LongestPairService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LongestPairServiceImpl implements LongestPairService {

    private final EmployeeRepository employeeRepository;

    public LongestPairServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<PairCommonProjectsDTO> findEmployeePair() {

        // projectID, Employees
        Map<Integer, List<EmployeeProject>> employeeProjects = employeeRepository.findAllEmployeeProjects();

        Map<Pair, List<PairCommonProjects>> pairsCommonProjects = new HashMap<>();

        PairCommonProjects longestPair = null;

        for (Map.Entry<Integer, List<EmployeeProject>> entry : employeeProjects.entrySet()) {
            int projectId = entry.getKey();
            List<EmployeeProject> employees = entry.getValue();


            int currEmpl = 0;
            while (currEmpl < employees.size() - 1) {

                for (int nextEmpl = currEmpl + 1; nextEmpl < employees.size(); nextEmpl++) {

                    // Check if currEmployee and nextEmployee have overlap days
                    PairCommonProjects currPairDetails = new PairCommonProjects(employees.get(currEmpl), employees.get(nextEmpl));
                    Pair currPair = currPairDetails.getPair();

                    if (currPairDetails.getDaysWorkedTogether() > 0) {

                        pairsCommonProjects.putIfAbsent(currPair, new ArrayList<>());
                        pairsCommonProjects.get(currPair).add(currPairDetails);

                        if (longestPair == null || currPairDetails.getDaysWorkedTogether() > longestPair.getDaysWorkedTogether()) {
                            longestPair = currPairDetails;
                        }
                    }
                }

                currEmpl++;
            }
        }

        if (longestPair != null) {
            List<PairCommonProjectsDTO> longestPairCommonProjects = new ArrayList<>();

            pairsCommonProjects.get(longestPair.getPair())
                    .forEach(p -> {
                        longestPairCommonProjects.add(new PairCommonProjectsDTO(p.getFirstEmployeeId(),
                                p.getSecondEmployeeId(), p.getProjectId(), p.getDaysWorkedTogether()));
                    });

            // Sort the list by daysWorkedTogether in descending order
            List<PairCommonProjectsDTO> sortedPairs = longestPairCommonProjects.stream()
                    .sorted(Comparator.comparingLong(PairCommonProjectsDTO::getDaysWorkedTogether).reversed())
                    .collect(Collectors.toList());


            return sortedPairs;
        }

        return new ArrayList<>();
    }
}
