package org.example.glue;

import org.example.Game;

public class TestableGame extends Game {
    public TestableGame(String guess, int attempts) {
        super(guess, attempts);
    }

    public boolean isOver() {
        return state.isOver();
    }
}
