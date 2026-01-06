package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

    @Test
    void weatherHasSmallestSpreadDay() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App.main("--weather", "weather.csv");
        String output = out.toString();
        assertTrue(output.contains("Day with smallest temperature spread : 14"),
                "Expected smallest spread day to be 14, but output was: " + output);
    }

    @Test
    void footballHasSmallestSpreadTeam() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        App.main("--football", "football.csv");
        String output = out.toString();
        assertTrue(output.contains("Team with smallest goal spread : Aston_Villa"),
                "Expected smallest spread team to be Aston_Villa, but output was: " + output);
    }
}