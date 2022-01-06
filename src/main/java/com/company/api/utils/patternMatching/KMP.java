package com.company.api.utils.patternMatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMP {
    /**
     * El metodo findOne() busca un patron en un texto y devuelve la primera
     * coincidencia
     * 
     * @param text    Es el texto en el cual se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Numero entero correspondiente a la primera coincidencia del patron en
     *         el texto
     */
    public static int findOne(String text, String pattern) {
        char[] ctext, cpattern;
        int n, m;
        ctext = text.toCharArray();
        cpattern = pattern.toCharArray();
        int textLength = text.length();
        int patternLength = pattern.length();
        int respuesta = -1;
        int[] fail = computeFailKMP(pattern); // búsqueda trivial de una cadena vacía
        int j = 0; // tamanio del texto original
        int k = 0; // tamanio del patron
        n = text.length();
        m = pattern.length();
        while (j < n) {
            if (ctext[j] == cpattern[k]) { // patrón [0..k] coincidió hasta ahora
                if (k == m - 1) {
                    respuesta = (j - m + 1);
                    k = 0;
                }
                j++; // caso contrario intenta extender la coincidencia
                k++;
            } else if (k > 0)
                k = fail[k - 1]; // reutilizar el sufijo de P[0..k-1]
            else
                j++;
        }
        return respuesta; // llegó al final coincidencia
    }

    private static int[] computeFailKMP(String pattern) {
        char[] cpattern = pattern.toCharArray();
        int m = cpattern.length;
        int[] fail = new int[m]; // mismo tamanio que el patron
        int j = 1;
        int k = 0;
        while (j < m) { //// calcular falla [j] durante este paso, si es distinto de cero
            if (cpattern[j] == cpattern[k]) {
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) // k sigue un prefijo coincidente
                k = fail[k - 1];
            else
                j++; // no se encontró ninguna coincidencia a partir de j
        }
        return fail;
    }

    /**
     * El metodo findAll devuelve un arreglo de enteros con todas las posiciones
     * 
     * @param text    Es el texto en el cual se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Arreglo de enteros con todas las posiciones de las ocurrencias
     *         encontradas del patron en el texto
     */
    public static Integer[] findAll(String text, String pattern) {
        int textLength, n, m;
        int patternLength;
        List<Integer> occurrences;
        char[] ctext, cpattern;

        textLength = text.length();
        patternLength = pattern.length();
        occurrences = new ArrayList<>();
        ctext = text.toCharArray();

        n = text.length();
        m = pattern.length();
        cpattern = pattern.toCharArray();
        int respuesta = -1;
        int[] fail = computeFailKMP(pattern);
        int j = 0;
        int k = 0;
        while (j < n) {
            if (ctext[j] == cpattern[k]) {
                if (k == m - 1) {
                    respuesta = (j - m + 1);
                    k = 0;
                }
                j++;
                k++;
            } else if (k > 0)
                k = fail[k - 1];
            else
                occurrences.add(j);
        }

        // Se crea un nuevo arreglo de entero con el tamaño de la lista de ocurrencias
        Integer[] response = new Integer[occurrences.size()];
        // Se un arreglo que se obtiene transformando la lista de ocurrencias en arreglo
        return occurrences.toArray(response);
    }

    /**
     * El metodo findAllForEachLines() busca un patron en un texto dividido segun
     * sus lineas
     * 
     * @param lines   Es el arreglo con las lineas en las cuales se requiere buscar
     *                un patron
     * @param pattern El patron a buscar en el texto
     * @return Hashmap en donde la clave es la linea y el valor es su
     *         correspondiente arreglo de coincidencias
     */
    public static Map<Integer, Integer[]> findAllForEachLines(String[] lines, String pattern) {
        // En el hashmap se almacenara la linea y un arreglo de occurencias en caso de
        // existirlas
        Map<Integer, Integer[]> map;
        // En una arreglo de Strings se separa cada linea del texto ingresado para
        // realizar una
        // busqueda especifica de cada linea

        map = new HashMap<Integer, Integer[]>();

        // Por cada linea del texto se realiza una busqueda del patron y en caso de
        // existir una
        // coincidencia se almacena la linea y la posicion de las coincidencias en un
        // hashmap
        for (int i = 0; i < lines.length; i++) {
            Integer[] currentOcurrences = findAll(lines[i], pattern);

            // Si el arreglo de ocurrencias actual tiene una longitud diferente de 0, es
            // decir, no esta vacio
            // se almacena la linea actual con dicho arreglo de occurencias
            if (currentOcurrences.length != 0) {
                // Se guarda en el hashmap la clave de la linea con el valor de su arreglo de
                // occurencias
                map.put(i + 1, currentOcurrences);
            }
        }

        return map;
    }
}
