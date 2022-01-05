package com.company.api.model;

import java.util.Map;

import lombok.Data;

@Data
public class ExecutionAlgorithmData {

    // Linea y posicion donde se encuentra la ocurrencia
    private String name;
    private String text;
    private String pattern;
    // Ocurrencias: { linea: [posiciones], linea: [posiciones] }
    private Map<Integer, Integer[]> occurrencesByLine;
    private double executionTimeMS;
    private double executionTimeNS;

    public ExecutionAlgorithmData(String name, String text, String pattern, Map<Integer, Integer[]> occurrencesByLine,
            double executionTimeMS, double executionTimeNS) {
        this.name = name;
        this.text = text;
        this.pattern = pattern;
        this.occurrencesByLine = occurrencesByLine;
        this.executionTimeMS = executionTimeMS;
        this.executionTimeNS = executionTimeNS;
    }
}
