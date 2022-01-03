package com.company.api.controller;

import com.company.api.model.ExecutionAlgorithmData;
import com.company.api.service.AlgorithmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private AlgorithmsService service;

    @CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = RequestMethod.GET, allowCredentials = "false")
    @GetMapping("/brute-force")
    public ResponseEntity<ExecutionAlgorithmData> getDataByOneForEachLineByBruteForce(@RequestParam String text,
            @RequestParam String pattern) {
        ExecutionAlgorithmData data = service.getDataByOneForEachLineByBruteForce(text, pattern);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // TODO: Implementar kmp
    @GetMapping("/kmp")
    public ResponseEntity<ExecutionAlgorithmData> getDataByOneForEachLineByKMP(@RequestParam String text,
            @RequestParam String pattern) {
        ExecutionAlgorithmData data = service.getDataByOneForEachLineByKMP(text, pattern);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // TODO: Implementar boyer moore
    @GetMapping("/boyer-moore")
    public ResponseEntity<ExecutionAlgorithmData> getDataByOneForEachLineByBoyerMoore(@RequestParam String text,
            @RequestParam String pattern) {
        ExecutionAlgorithmData data = service.getDataByOneForEachLineByBoyerMoore(text, pattern);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
