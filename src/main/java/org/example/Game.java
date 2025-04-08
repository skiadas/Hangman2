package org.example;

import java.io.PrintStream;
import java.util.Scanner;

public class Game {

    private final GameState state;
    protected final PrintStream out;
    private final Scanner in;

    public Game(String guess, int attempts) {
        state = new GameState(guess, attempts);
        out = System.out;
        in = new Scanner(System.in);
    }

    void play() {
        while (!state.isOver()) {
            String guess = askForLetter();
            result = state.guessLetter(guess);

        }
        announceResult(state.won());
    }

    protected void announceResult(boolean playerWon) {

    }

    protected String askForLetter() {

    }
}
