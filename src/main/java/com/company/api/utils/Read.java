package com.company.api.utils;

import java.io.File;
import java.util.Scanner;

public class Read {
    public static String read(String src) {
        try {
            File file = new File(src);
            Scanner myReader = new Scanner(file);
            String data = "";
    
            System.out.println("\nReading File\n" + "Source: "+src);
            while (myReader.hasNextLine()) {
                String currentLine = myReader.nextLine();
                data += currentLine + "\n";
            }
    
            myReader.close();
            return data;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            throw new Error(e.getMessage());
        }
    }
}
