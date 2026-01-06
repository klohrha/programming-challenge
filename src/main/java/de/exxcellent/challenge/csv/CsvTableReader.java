package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.App;
import de.exxcellent.challenge.core.TableReader;
import de.exxcellent.challenge.core.TabularData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV implementation of TableReader that loads a classpath resource into a TabularData instance.
 */
public final class CsvTableReader implements TableReader {

    @Override
    public TabularData read(String resourcePath) throws IOException {
        try (InputStream is = App.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Could not find resource: " + resourcePath);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String header = br.readLine();
                if (header == null) {
                    throw new IOException("Empty CSV: " + resourcePath);
                }
                String[] headers = header.split(",");

                List<String[]> rows = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isBlank()) continue;
                    rows.add(line.split(","));
                }
                return new CsvTableData(headers, rows);
            }
        }
    }
}
