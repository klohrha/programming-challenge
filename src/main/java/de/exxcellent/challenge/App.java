package de.exxcellent.challenge;

import de.exxcellent.challenge.core.SpreadService;
import de.exxcellent.challenge.csv.CsvTableReader;
import java.io.IOException;
import java.util.Objects;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        String mode = null;

        if (args != null) {
            for (String arg : args) {
                if ("--weather".equalsIgnoreCase(arg)) {
                    mode = "weather";
                } else if ("--football".equalsIgnoreCase(arg)) {
                    mode = "football";
                } else {
                    // ignore other args (e.g., file names) for this simple CLI
                }
            }
        }

        String resourcePath;
        String keyColumn;
        String maxColumn;
        String minColumn;

        boolean football;
        if (Objects.equals(mode, "football")) {
            football = true;
            resourcePath = "/de/exxcellent/challenge/football.csv";
            keyColumn = "Team";
            maxColumn = "Goals";
            minColumn = "Goals Allowed";
        } else if (Objects.equals(mode, "weather")) {
            football = false;
            resourcePath = "/de/exxcellent/challenge/weather.csv";
            keyColumn = "Day";
            maxColumn = "MxT";
            minColumn = "MnT";
        } else {
            System.err.println("Failed to parse mode");
            return;
        }

        try {
            SpreadService service = new SpreadService(new CsvTableReader());
            String result = service.findMinSpread(resourcePath, keyColumn, maxColumn, minColumn);
            if (football) {
                System.out.printf("Team with smallest goal spread : %s%n", result);
            } else {
                System.out.printf("Day with smallest temperature spread : %s%n", result);
            }
        } catch (IOException e) {
            System.err.println("Failed to read CSV: " + e.getMessage());
        }
    }
}
