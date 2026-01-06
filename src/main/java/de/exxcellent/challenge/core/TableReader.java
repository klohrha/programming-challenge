package de.exxcellent.challenge.core;

import java.io.IOException;

/**
 * Reads tabular data from a given resource path
 */
public interface TableReader {

    TabularData read(String resourcePath) throws IOException;
}
