package com.example.krasimirhristovemployees.web;

import com.example.krasimirhristovemployees.model.dto.PairCommonProjectsDTO;
import com.example.krasimirhristovemployees.service.LongestPairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LongestPairController {

    private final LongestPairService longestPairService;

    public LongestPairController(LongestPairService longestPairService) {

        this.longestPairService = longestPairService;
    }

    @PostMapping("/longest-pair-common-projects")
    public ResponseEntity<List<PairCommonProjectsDTO>> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String rootPath = System.getProperty("user.dir");
        File testFile = new File(rootPath + "/src/main/resources/static/employee_data.csv");
        file.transferTo(testFile);

        List<PairCommonProjectsDTO> longestPairCommonProjects = longestPairService.findEmployeePair();

        return ResponseEntity.ok(longestPairCommonProjects);
    }
}
