package de.exxcellent.challenge.csv;

import de.exxcellent.challenge.core.TabularData;

import java.util.List;
import java.util.Objects;

final class CsvTableData implements TabularData {

    private final String[] headers;
    private final List<String[]> rows;

    CsvTableData(String[] headers, List<String[]> rows) {
        this.headers = Objects.requireNonNull(headers, "headers");
        this.rows = Objects.requireNonNull(rows, "rows");
    }

    @Override
    public String[] getHeaders() {
        return headers;
    }

    @Override
    public List<String[]> getRows() {
        return rows;
    }
}
