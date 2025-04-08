package org.example;

import java.io.PrintStream;
import java.util.Scanner;

import static org.example.GameState.GameErrorException.GameError.AlreadyGuessed;
import static org.example.GameState.GameErrorException.GameError.InvalidLetter;

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
            printState();
            String guess = askForLetter();
            try {
                boolean guessedCorrectly = state.guessLetter(guess);
                if (guessedCorrectly)
                    printLine("The letter is there!");
                else
                    printLine("The letter is not there!");
            } catch (GameState.GameErrorException e) {
                if (e.error == InvalidLetter)
                    printLine("Not a valid letter, try again!");
                else if (e.error == AlreadyGuessed)
                    printLine("You already guessed that letter, try again!");
            }
        }
        announceResult(state.won());
    }

    private void printState() {
        printLine(state.getMaskedWord());
        printLine("Attempts: " + state.getAttempts());
    }

    protected void printLine(String s) {
        out.println(s);
    }

    protected void announceResult(boolean playerWon) {
        if (playerWon)
            printLine("Congratulations, you guessed correctly!");
        else
            printLine("I'm sorry you are out of attempts!");
    }

    protected String askForLetter() {
        out.print("Please guess: ");
        return in.next().trim();
    }
}
