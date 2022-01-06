package com.company.api.utils.patternMatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {
    /**
     * El metodo findOne() busca un patron en un texto y devuelve la primera
     * coincidencia
     * 
     * @param text    Es el texto en el cual se requiere buscar un patron
     * @param pattern El patron a buscar en el texto
     * @return Numero entero correspondiente a la primera coincidencia del patron en
     *         el texto
     */
    public static int findOne(String t, String p) {
        char[] text = t.toCharArray(); // convertimos el String en un arreglo de Char
        char[] pattern = p.toCharArray();
        return indexOf(text, pattern); // Llamamos a la fución indexOf
    }

    /** Función para calcular el índice de la subcadena de patrón **/
    private static int indexOf(char[] text, char[] pattern) {
        // si la longitud del patrón es cero se devuelve cero
        if (pattern.length == 0) {
            return 0;
        }
        int charTable[] = makeCharTable(pattern); // Llamamos a la fución makeCharTable
        int offsetTable[] = makeOffsetTable(pattern); // Llamamos a la fución makeOffsetTable
        // i empieza con la longitud del patron -1
        for (int i = pattern.length - 1, j; i < text.length;) {
            // j tambien empieza con la longitud del patron -1 pero aqui vamos a ir
            // disminuyendo i y j en uno
            for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
        }
        return -1;
    }

    /**
     * Hace que la tabla de salto en base a la información de los caracteres que no
     * coinciden
     **/
    private static int[] makeCharTable(char[] pattern) {
        // ponemos el tamaño del alfabeto que es 256
        final int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        // creamos un arreglo de int con este tamaño
        for (int i = 0; i < table.length; ++i)
            table[i] = pattern.length; // en el arreglo table guardamos en cada posicion la longitud del patrón
        // empezamos con i =0 hasta reccorrer todo el tamaño del patrón
        for (int i = 0; i < pattern.length - 1; ++i)
            table[pattern[i]] = pattern.length - 1 - i; // en table guardamos la longitud del patrón -1 - i
        return table;
    }

    /**
     * Hace que la tabla de salto se base en el desplazamiento de exploración en el
     * que se produce la falta de coincidencia.
     **/
    private static int[] makeOffsetTable(char[] pattern) {
        int[] table = new int[pattern.length]; // creamos un arreglo de int con la longitud del patrón
        int lastPrefixPosition = pattern.length; // ponemos como ultima posición de prefijo el mismo valor que la long
                                                 // del patrón
        // i empieza con la longitud del patrón -1 y va disminuyendo hasta llegar a 0
        for (int i = pattern.length - 1; i >= 0; --i) {
            // llamamos a la función isPrefix para comprobar si es un prefijo o no
            if (isPrefix(pattern, i + 1))
                lastPrefixPosition = i + 1; // cambiamos el valor de la ultima posición de prefijo
            table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
        }
        for (int i = 0; i < pattern.length - 1; ++i) {
            // llamamos a la función suffixLength para saber la long máxima de la subcadena
            // que es un sufijo
            int slen = suffixLength(pattern, i);
            table[slen] = pattern.length - 1 - i + slen;
        }
        return table;
    }

    /** función para comprobar si la aguja [p: end] es un prefijo de patrón **/
    private static boolean isPrefix(char[] pattern, int p) {
        for (int i = p, j = 0; i < pattern.length; ++i, ++j) {
            // comprobamos que sean iguales caso contrario retornamos falso
            if (pattern[i] != pattern[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Función para devuelve la longitud máxima de la subcadena terminada en p y es
     * un sufijo
     **/
    private static int suffixLength(char[] pattern, int p) {
        // inicializamos en 0
        int len = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j) {
            // conforme pasa el ciclo for aumentamos 1
            len += 1;
        }
        return len;
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
        int textLength;
        int patternLength;
        List<Integer> occurrences;

        textLength = text.length();
        patternLength = pattern.length();
        occurrences = new ArrayList<>();
        // i empieza en cero e itera todo el texto
        for (int i = 0; i <= textLength - patternLength; i++) {
            // para poder iterar el patrón creamos una variable j y la inicializamos en 0
            int j = 0;
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                // a medida que encuentra una similitud aumenta j
                j++;
            }
            // comprobamos que j es igual a la long del patrón
            if (j == patternLength) {
                // añadimos a la lista de ocurrencias
                occurrences.add(i);
            }
        }
        // creamos un nuevo arreglo de int con el tamaño de la lista de ocurrencias
        Integer[] response = new Integer[occurrences.size()];
        // retornamos una lista de ocurrencias
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
        Map<Integer, Integer[]> map;
        // en el arreglo lines separamos linea por linea para buscar en cada una de
        // ellas
        map = new HashMap<Integer, Integer[]>();
        // en cada linea del texto realizamos una busqueda y en caso de existir una
        // concidencia la almacenamos
        for (int i = 0; i < lines.length; i++) {
            Integer[] currentOcurrences = findAll(lines[i], pattern);
            // verificamos que el arreglo no se encuentre vacio para poder almacenar la
            // linea
            if (currentOcurrences.length != 0) {
                map.put(i + 1, currentOcurrences);// guardamos la clave de la linea y el valor de su arreglo de
                                                  // ocurrencias
            }
        }

        return map;
    }
}