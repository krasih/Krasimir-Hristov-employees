package com.example.krasimirhristovemployees.service;

import com.example.krasimirhristovemployees.model.dto.PairCommonProjectsDTO;

import java.util.List;

public interface LongestPairService {

    List<PairCommonProjectsDTO> findEmployeePair();
}
