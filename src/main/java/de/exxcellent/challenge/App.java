package de.exxcellent.challenge;

import java.io.IOException;

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
        boolean football = args != null && args.length > 0 && "--football".equalsIgnoreCase(args[0]);
        String resourcePath;
        String keyColumn;
        String maxColumn;
        String minColumn;

        if (football) {
            resourcePath = "/de/exxcellent/challenge/football.csv";
            keyColumn = "Team";
            maxColumn = "Goals";
            minColumn = "Goals Allowed";
        } else {
            resourcePath = "/de/exxcellent/challenge/weather.csv";
            keyColumn = "Day";
            maxColumn = "MxT";
            minColumn = "MnT";
        }

        try {
            String result = CsvSpreadCalculator.findMinSpread(resourcePath, keyColumn, maxColumn, minColumn);
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
