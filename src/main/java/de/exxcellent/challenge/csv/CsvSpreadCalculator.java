package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.core.MinSpreadCalculator;
import de.exxcellent.challenge.core.TabularData;

import java.io.IOException;

/**
 * Utility for calculating minimal spread between two numeric columns in a CSV file on the classpath.
 */
public final class CsvSpreadCalculator {

    private CsvSpreadCalculator() {}

    /**
     * Finds the value from the key column for the row that has the smallest absolute difference between maxColumn and minColumn.
     *
     * @param resourcePath classpath resource path to CSV
     * @param keyColumn    name of the key column to return
     * @param maxColumn    name of the first numeric column
     * @param minColumn    name of the second numeric column
     * @return the key for the row with the smallest spread, or null if not found
     */
    public static String findMinSpread(String resourcePath, String keyColumn, String maxColumn, String minColumn) {
        CsvTableReader reader = new CsvTableReader();
        TabularData table;
        try {
            table = reader.read(resourcePath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }

        try {
            return MinSpreadCalculator.compute(table, keyColumn, maxColumn, minColumn);
        } catch (IllegalArgumentException ex) {
            System.err.println("Required columns not found in header");
            return null;
        }
    }
}
