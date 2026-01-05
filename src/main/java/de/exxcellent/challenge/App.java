package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
        String resourcePath = "/de/exxcellent/challenge/weather.csv";
        try (InputStream is = App.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("Could not find resource: " + resourcePath);
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String header = br.readLine();
                if (header == null) {
                    System.err.println("Empty CSV: " + resourcePath);
                    return;
                }
                String[] headers = header.split(",");
                int dayIdx = indexOf(headers, "Day");
                int maxIdx = indexOf(headers, "MxT");
                int minIdx = indexOf(headers, "MnT");
                if (dayIdx < 0 || maxIdx < 0 || minIdx < 0) {
                    System.err.println("Required columns not found in header");
                    return;
                }

                String line;
                String result = null;
                Integer smallestSpread = null;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    String day = parts[dayIdx].trim();
                    String maxS = parts[maxIdx].trim();
                    String minS = parts[minIdx].trim();

                    int spread = Math.abs(Integer.parseInt(maxS) - Integer.parseInt(minS));
                    if (smallestSpread == null || spread < smallestSpread) {
                        smallestSpread = spread;
                        result = day;
                    }
                }

                System.out.printf("Day with smallest temperature spread : %s%n", result);
            }
        } catch (IOException e) {
            System.err.println("Failed to read CSV: " + e.getMessage());
        }
    }

    private static int indexOf(String[] headers, String name) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equals(name)) return i;
        }
        return -1;
    }
}
