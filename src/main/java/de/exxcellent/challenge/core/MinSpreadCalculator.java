package de.exxcellent.challenge.core;

import java.util.List;

/**
 * calculator for finding the key of the row with the smallest absolute spread between two numeric columns.
 */
public final class MinSpreadCalculator {

    private MinSpreadCalculator() {}

    public static String compute(String[] headers, List<String[]> rows, String keyCol, String maxCol, String minCol) {
        if (headers == null) throw new IllegalArgumentException("headers must not be null");
        if (rows == null) throw new IllegalArgumentException("rows must not be null");

        int keyIdx = indexOf(headers, keyCol);
        int maxIdx = indexOf(headers, maxCol);
        int minIdx = indexOf(headers, minCol);
        if (keyIdx < 0 || maxIdx < 0 || minIdx < 0) {
            throw new IllegalArgumentException("Required columns not found in header");
        }

        String result = null;
        Integer smallestSpread = null;
        for (String[] parts : rows) {
            if (parts == null) continue;
            if (parts.length <= Math.max(keyIdx, Math.max(maxIdx, minIdx))) continue;
            String key = parts[keyIdx].trim();
            String maxS = parts[maxIdx].trim();
            String minS = parts[minIdx].trim();
            if (maxS.isEmpty() || minS.isEmpty()) continue;
            int spread = Math.abs(Integer.parseInt(maxS) - Integer.parseInt(minS));
            if (smallestSpread == null || spread < smallestSpread) {
                smallestSpread = spread;
                result = key;
            }
        }
        return result;
    }

    private static int indexOf(String[] headers, String name) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equals(name)) return i;
        }
        return -1;
    }

    public static String compute(TabularData table, String keyCol, String maxCol, String minCol) {
        return compute(table.getHeaders(), table.getRows(), keyCol, maxCol, minCol);
    }
}
