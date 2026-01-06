package de.exxcellent.challenge;

import de.exxcellent.challenge.csv.CsvSpreadCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvSpreadCalculatorTest {

    @Test
    void exampleWeatherHasSmallestSpreadDay() {
        String result = CsvSpreadCalculator.findMinSpread(
                "/de/exxcellent/challenge/example_weather.csv",
                "Day", "MxT", "MnT");
        assertNotNull(result, "Expected a day to be found");
        assertEquals("2", result, "Expected smallest spread day to be 2");
    }

    @Test
    void exampleFootballHasSmallestSpreadTeam() {
        String result = CsvSpreadCalculator.findMinSpread(
                "/de/exxcellent/challenge/example_football.csv",
                "Team", "Goals", "Goals Allowed");
        assertNotNull(result, "Expected a team to be found");
        assertEquals("TeamB", result, "Expected smallest spread team to be TeamB");
    }
}
