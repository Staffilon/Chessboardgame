package it.unicam.cs.pa.chessboardgame.api;

import it.unicam.cs.pa.chessboardgame.api.controller.CheckersGameController;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.view.CheckersCLI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class CheckersGameControllerTest {
    private CheckersGameController controller;

    @BeforeEach
    void setup() {
        //controller = new CheckersGameController();
    }

    @Test
    public void shouldRequestPlayerNameCorrectly() {
        // Create a mock CLI object
        CheckersCLI mockCli = mock(CheckersCLI.class);

        // Set up the mock CLI object to return a fixed string when readInput() is called
        when(mockCli.readInput()).thenReturn("Michael");

        // Create a new instance of the class to be tested, passing in the mock CLI object
        controller = new CheckersGameController(mockCli);

        // Call the method being tested
        String playerName = controller.getPlayerNameFromInput(CheckersColor.WHITE);

        // Verify that the CLI object was used to display the correct message
        verify(mockCli).displayMessage("Insert name for White player: ");

        // Verify that the returned player name is the same as the string returned by the mock CLI object
        assertEquals("Michael", playerName);
    }

    @Test
    public void shouldRequestPlayerNameIncorrectly() {
        CheckersCLI mockCli = mock(CheckersCLI.class);

        // Set up the mock CLI object to simulate the user entering an invalid name
        when(mockCli.readInput())
                .thenReturn("") // empty string on first call
                .thenReturn("    ") // whitespace string on second call
                .thenReturn(null) // null on third call
                .thenReturn("J") // string shorter than 2 characters on fourth call
                .thenReturn("John") // valid name on fifth call
                .thenReturn("1234") // string containing only digits on sixth call
                .thenReturn("M1chael") // string containing digits and letters on seventh call
                .thenReturn("Michael"); // valid name on eighth call

        controller = new CheckersGameController(mockCli);

        String playerName = null;
        int attempts = 0;
        while (attempts < 7) {
            playerName = controller.getPlayerNameFromInput(CheckersColor.BLACK);
            assertNotEquals("Michael", playerName);
            attempts++;
        }
        playerName = controller.getPlayerNameFromInput(CheckersColor.BLACK);
        // Verify that the readInput() method was called exactly 8 times
        verify(mockCli, times(8)).displayMessage("Insert name for Black player: ");

        assertEquals("Michael", playerName);
    }
}