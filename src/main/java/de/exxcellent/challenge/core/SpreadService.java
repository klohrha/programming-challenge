package de.exxcellent.challenge.core;

import java.io.IOException;

/**
 * Format-agnostic service for computing spreads using a provided TableReader.
 */
public final class SpreadService {

    private final TableReader reader;

    public SpreadService(TableReader reader) {
        this.reader = reader;
    }

    /**
     * Finds the value from the key column for the row that has the smallest absolute difference
     * between maxColumn and minColumn.
     *
     * @param resourcePath location to read the tabular data from (semantics defined by reader)
     * @param keyColumn    name of the key column to return
     * @param maxColumn    name of the first numeric column
     * @param minColumn    name of the second numeric column
     * @return the key for the row with the smallest spread, or null if not found
     * @throws IOException if reading fails
     */
    public String findMinSpread(String resourcePath, String keyColumn, String maxColumn, String minColumn) throws IOException {
        TabularData table = reader.read(resourcePath);
        try {
            return MinSpreadCalculator.compute(table, keyColumn, maxColumn, minColumn);
        } catch (IllegalArgumentException ex) {
            System.err.println("Required columns not found in header");
            return null;
        }
    }
}
