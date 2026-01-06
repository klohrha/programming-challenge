package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Utility for calculating minimal spread between two numeric columns in a CSV file on the classpath.
 */
final class CsvSpreadCalculator {

    private CsvSpreadCalculator() {}

    /**
     * Finds the value from the key column for the row that has the smallest absolute difference between maxColumn and minColumn.
     *
     * @param resourcePath classpath resource path to CSV
     * @param keyColumn    name of the key column to return
     * @param maxColumn    name of the first numeric column
     * @param minColumn    name of the second numeric column
     * @return the key for the row with the smallest spread, or null if not found
     * @throws IOException if reading fails
     */
    static String findMinSpread(String resourcePath, String keyColumn, String maxColumn, String minColumn) throws IOException {
        try (InputStream is = App.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("Could not find resource: " + resourcePath);
                return null;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String header = br.readLine();
                if (header == null) {
                    System.err.println("Empty CSV: " + resourcePath);
                    return null;
                }
                String[] headers = header.split(",");
                int keyIdx = indexOf(headers, keyColumn);
                int maxIdx = indexOf(headers, maxColumn);
                int minIdx = indexOf(headers, minColumn);
                if (keyIdx < 0 || maxIdx < 0 || minIdx < 0) {
                    System.err.println("Required columns not found in header");
                    return null;
                }

                String line;
                String result = null;
                Integer smallestSpread = null;
                while ((line = br.readLine()) != null) {
                    if (line.isBlank()) continue;
                    String[] parts = line.split(",");
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
        }
    }

    static int indexOf(String[] headers, String name) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equals(name)) return i;
        }
        return -1;
    }
}
