package org.example;

import java.io.PrintStream;
import java.util.Scanner;

import static org.example.Game.InteractionState.*;
import static org.example.GameState.GameErrorException.GameError.AlreadyGuessed;
import static org.example.GameState.GameErrorException.GameError.InvalidLetter;

public class Game {
    protected enum InteractionState { Start, PrintGame, GuessLetter, End }

    protected final GameState state;
    protected final PrintStream out;
    protected final Scanner in;
    protected InteractionState interactionState;

    public Game(String guess, int attempts) {
        state = new GameState(guess, attempts);
        out = System.out;
        in = new Scanner(System.in);
        interactionState = Start;
    }

    public void play() {
        while (keepGoing()) interactionState = takeStep();
    }

    protected boolean keepGoing() {
        return interactionState != End;
    }

    InteractionState takeStep() {
        return switch (interactionState) {
            case Start -> takeStepStart();
            case PrintGame -> takeStepPrintGame();
            case GuessLetter -> takeStepGuessLetter();
            case End -> throw new RuntimeException("takeStep on End");
        };
    }

    protected InteractionState takeStepGuessLetter() {
        String guess = askForLetter();
        applyGuess(guess);
        return PrintGame;
    }

    protected InteractionState takeStepPrintGame() {
        printState();
        if (isOver()) {
            announceResult(state.won());
            return End;
        }
        return GuessLetter;
    }

    protected InteractionState takeStepStart() {
        return PrintGame;
    }

    private void applyGuess(String guess) {
        try {
            boolean guessedCorrectly = state.guessLetter(guess);
            if (guessedCorrectly) {
                printLine("The letter is there!");
            } else printLine("The letter is not there!");
        } catch (GameState.GameErrorException e) {
            if (e.error == InvalidLetter)
                printLine("Not a valid letter, try again!");
            else if (e.error == AlreadyGuessed)
                printLine("You already guessed that letter, try again!");
        }
    }

    public boolean isOver() {
        return state.isOver();
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
