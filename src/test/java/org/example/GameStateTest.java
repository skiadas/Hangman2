package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getMaskedWord() {
        GameState state = new GameState("CAT", 5);
        state.guessLetter("A");
        assertEquals("C_T", state.getMaskedWord());
    }
}