package com.company.api.utils.patternMatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {
    public static int findOne(String t, String p) {
        char[] text = t.toCharArray();
        char[] pattern = p.toCharArray();
        return indexOf(text, pattern);
    }

    private static int indexOf(char[] text, char[] pattern) {
        if (pattern.length == 0) {
            return 0;
        }
        int charTable[] = makeCharTable(pattern);
        int offsetTable[] = makeOffsetTable(pattern);
        for (int i = pattern.length - 1, j; i < text.length;) {
            for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j) {
                if (j == 0) {
                    return i;
                }
            }
            i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
        }
        return -1;
    }

    private static int[] makeCharTable(char[] pattern) {
        final int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i)
            table[i] = pattern.length;
        for (int i = 0; i < pattern.length - 1; ++i)
            table[pattern[i]] = pattern.length - 1 - i;
        return table;
    }

    private static int[] makeOffsetTable(char[] pattern) {
        int[] table = new int[pattern.length];
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length - 1; i >= 0; --i) {
            if (isPrefix(pattern, i + 1))
                lastPrefixPosition = i + 1;
            table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
        }
        for (int i = 0; i < pattern.length - 1; ++i) {
            int slen = suffixLength(pattern, i);
            table[slen] = pattern.length - 1 - i + slen;
        }
        return table;
    }

    private static boolean isPrefix(char[] pattern, int p) {
        for (int i = p, j = 0; i < pattern.length; ++i, ++j) {
            if (pattern[i] != pattern[j]) {
                return false;
            }
        }
        return true;
    }

    private static int suffixLength(char[] pattern, int p) {
        int len = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j) {
            len += 1;
        }
        return len;
    }

    public static Integer[] findAll(String text, String pattern) {
        int textLength;
        int patternLength;
        List<Integer> occurrences;

        textLength = text.length();
        patternLength = pattern.length();
        occurrences = new ArrayList<>();

        for (int i = 0; i <= textLength - patternLength; i++) {
            int j = 0;
            while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == patternLength) {
                occurrences.add(i);
            }
        }
        Integer[] response = new Integer[occurrences.size()];
        return occurrences.toArray(response);
    }

    public static Map<Integer, Integer[]> findAllForEachLines(String[] lines, String pattern) {
        Map<Integer, Integer[]> map;

        map = new HashMap<Integer, Integer[]>();

        for (int i = 0; i < lines.length; i++) {
            Integer[] currentOcurrences = findAll(lines[i], pattern);

            if (currentOcurrences.length != 0) {
                map.put(i + 1, currentOcurrences);
            }
        }

        return map;
    }
}