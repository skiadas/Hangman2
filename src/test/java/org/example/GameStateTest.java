package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getMaskedWord() throws GameState.GameErrorException {
        GameState state = new GameState("CAT", 5);
        state.guessLetter("A");
        assertEquals("_A_", state.getMaskedWord());
    }
}