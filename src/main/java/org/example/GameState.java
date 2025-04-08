package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState {
    private int attempts;
    private Set<String> guesses;
    private final String word;

    GameState(String word, int attempts) {
        this.attempts = attempts;
        this.word = word;
        this.guesses = new HashSet<>();
    }

    int getAttempts() {
        return attempts;
    }

    boolean isOver() {
        return lost() || won();
    }

    boolean lost() {
        return attempts == 0;
    }

    boolean won() {
        List<String> letterList = List.of(word.split("/./"));
        return guesses.containsAll(letterList);
    }

    String getMaskedWord() {
        if (guesses.isEmpty()) return word;
        String guessesPattern = "[" + String.join("", guesses) + "]";
        return word.replaceAll(guessesPattern, "_");
    }

    // TODO: Need to return if something went wrong?
    // Maybe return string of the error?
    String guessLetter(String letter) {
        // TODO: Fix logic
        guesses.add(letter);
        return null;
    }
}
