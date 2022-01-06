package com.company.api.utils;

import java.util.Map;

import com.company.api.model.ExecutionAlgorithmData;
import com.company.api.utils.patternMatching.BruteForce;

public class AlgorithmsPerformance {

    // Mide el tiempo de ejecucion y devuelve las coincidencias en cada linea
    public static ExecutionAlgorithmData testAlgorithm(String name, String text, String pattern) {
        ExecutionAlgorithmData data;
        Map<Integer, Integer[]> occurrencesByLine;
        String[] lines;

        // Variables para medir el tiempo
        double executionTimeMS;
        double executionTimeNS;
        Long startMS;
        Long startNS;
        Long endMS;
        Long endNS;

        // Separacion del texto en un arreglo con todas sus lineas
        lines = text.split("\n");

        // Inicio de la toma del tiempo
        startMS = System.currentTimeMillis();
        startNS = System.nanoTime();

        // Ejecucion del algoritmo
        occurrencesByLine = BruteForce.findAllForEachLines(lines, pattern);

        // Fin de la toma del tiempo
        endMS = System.currentTimeMillis();
        endNS = System.nanoTime();

        // Calculo tiempo final - tiempo inicial
        executionTimeMS = endMS - startMS;
        executionTimeNS = endNS - startNS;

        data = new ExecutionAlgorithmData(name, text, pattern, occurrencesByLine, executionTimeMS, executionTimeNS);

        return data;
    }

}
