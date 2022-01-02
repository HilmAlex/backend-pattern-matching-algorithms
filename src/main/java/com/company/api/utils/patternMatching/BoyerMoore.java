package com.company.api.utils.patternMatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {
    /**
     * El metodo findOne() busca un patron en un texto y devuelve la primera coincidencia
     * @param text  Es el texto en el cual se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Numero entero correspondiente a la primera coincidencia del patron en el texto
     */
    public static int findOne(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();

        for (int i = 0; i <= textLength - patternLength; i++) { // Iterar sobre todo el texto
            int j = 0; // Iterar el patron
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                return i;
            }
        }
        return -1;
    }

    /**
     * El metodo findAll devuelve un arreglo de enteros con todas las posiciones
     * @param text  Es el texto en el cual se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Arreglo de enteros con todas las posiciones de las ocurrencias encontradas del patron en el texto
     */
    public static Integer[] findAll(String text, String pattern) {
        int textLength;
        int patternLength;
        List<Integer> occurrences;

        textLength = text.length();
        patternLength = pattern.length();
        occurrences = new ArrayList<>();

        for (int i = 0; i <= textLength - patternLength; i++) { // Iterar sobre todo el texto
            int j = 0; // Iterar el patron
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                occurrences.add(i);
            }
        }

        // Se crea un nuevo arreglo de entero con el tamaño de la lista de ocurrencias
        Integer[] response = new Integer[occurrences.size()];
        // Se un arreglo que se obtiene transformando la lista de ocurrencias en arreglo
        return occurrences.toArray(response);
    }

    /** 
     * El metodo findAllForEachLines() busca un patron en un texto dividido segun sus lineas
     * @param lines  Es el arreglo con las lineas en las cuales se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Hashmap en donde la clave es la linea y el valor es su correspondiente arreglo de coincidencias
     */
    public static Map<Integer, Integer[]> findAllForEachLines(String[] lines, String pattern) {
        // En el hashmap se almacenara la linea y un arreglo de occurencias en caso de existirlas
        Map<Integer, Integer[]> map;
        // En una arreglo de Strings se separa cada linea del texto ingresado para realizar una
        // busqueda especifica de cada linea

        map = new HashMap<Integer, Integer[]>();

        // Por cada linea del texto se realiza una busqueda del patron y en caso de existir una
        // coincidencia se almacena la linea y la posicion de las coincidencias en un hashmap
        for (int i = 0; i < lines.length; i++) {
            Integer[] currentOcurrences = findAll(lines[i], pattern);

            // Si el arreglo de ocurrencias actual tiene una longitud diferente de 0, es decir, no esta vacio
            // se almacena la linea actual con dicho arreglo de occurencias
            if (currentOcurrences.length != 0) {
                // Se guarda en el hashmap la clave de la linea con el valor de su arreglo de occurencias
                map.put(i + 1, currentOcurrences);
            }
        }

        return map;
    }
}
