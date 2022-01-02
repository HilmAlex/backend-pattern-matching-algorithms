package com.company.api.service;

import com.company.api.utils.AlgorithmsPerformance;
import com.company.api.model.ExecutionAlgorithmData;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmsService {

    public ExecutionAlgorithmData getDataByOneForEachLineByBruteForce(String text, String pattern){
        ExecutionAlgorithmData data = AlgorithmsPerformance.testAlgorithm("brute-force",text, pattern);
        return data;
    }

    public ExecutionAlgorithmData getDataByOneForEachLineByKMP(String text, String pattern){
        ExecutionAlgorithmData data = AlgorithmsPerformance.testAlgorithm("kmp",text, pattern);
        return data;
    }

    public ExecutionAlgorithmData getDataByOneForEachLineByBoyerMoore(String text, String pattern){
        ExecutionAlgorithmData data = AlgorithmsPerformance.testAlgorithm("boyer-moore",text, pattern);
        return data;
    }
}
