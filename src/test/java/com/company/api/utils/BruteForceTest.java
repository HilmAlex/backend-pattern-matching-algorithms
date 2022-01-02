package com.company.api.utils;

import java.io.File;
import java.util.Map;

import com.company.api.utils.patternMatching.BruteForce;

import org.junit.jupiter.api.Test;


public class BruteForceTest {
    

    @Test
    void testFindAll() {

    }

    @Test
    void testFindAllInLines() {
        String src = new File(".").getAbsolutePath() + "/src/main/resources/test.txt";
        String data = Read.read(src);
        Map<Integer, Integer[]> res = BruteForce.findAllForEachLines(data.split("\n"), "lorem");

        System.out.println("Hola");
    }
}
