package org.example.glue;

import org.example.Game;

import java.util.ArrayList;
import java.util.List;

public class TestableGame extends Game {
    List<String> guesses = new ArrayList<>();
    List<String> lines = new ArrayList<>();
    // null: game not over
    // true: player won
    // false: player lost
    Boolean won = null;

    public TestableGame(String guess, int attempts) {
        super(guess, attempts);
    }

    protected boolean keepGoing() {
        if (interactionState == InteractionState.GuessLetter)
            return !guesses.isEmpty();
        return super.keepGoing();
    }

    protected void printLine(String s) {
        lines.add(s);
    }

    protected void announceResult(boolean playerWon) {
        won = playerWon;
    }

    protected String askForLetter() {
        return guesses.removeFirst();
    }

    void addGuess(String letter) {
        guesses.add(letter);
    }
}
