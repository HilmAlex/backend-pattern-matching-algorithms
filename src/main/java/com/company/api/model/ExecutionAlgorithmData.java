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
        this.setName(name);
        this.text = text;
        this.pattern = pattern;
        this.occurrencesByLine = occurrencesByLine;
        this.executionTimeMS = executionTimeMS;
        this.executionTimeNS = executionTimeNS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<Integer, Integer[]> getOccurrencesByLine() {
        return occurrencesByLine;
    }

    public void setOccurrencesByLine(Map<Integer, Integer[]> occurrencesByLine) {
        this.occurrencesByLine = occurrencesByLine;
    }

    public double getExecutionTimeMS() {
        return executionTimeMS;
    }

    public void setExecutionTimeMS(double executionTimeMS) {
        this.executionTimeMS = executionTimeMS;
    }

    public double getExecutionTimeNS() {
        return executionTimeNS;
    }

    public void setExecutionTimeNS(double executionTimeNS) {
        this.executionTimeNS = executionTimeNS;
    }

}
