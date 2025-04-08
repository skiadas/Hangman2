package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {
    private int attempts;
    private final Set<String> guesses;
    private final String word;

    GameState(String word, int attempts) {
        this.attempts = attempts;
        this.word = word.toUpperCase();
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
        List<String> letterList = List.of(word.split(""));
        return guesses.containsAll(letterList);
    }

    String getMaskedWord() {
        return Stream.of(word.split(""))
                .map(s -> guesses.contains(s) ? s : "_")
                .collect(Collectors.joining());
    }

    boolean guessLetter(String letter) throws GameErrorException {
        letter = letter.toUpperCase();
        if (guesses.contains(letter))
            throw GameErrorException.alreadyGuessed();
        if (isInvalidLetter(letter))
            throw GameErrorException.invalidLetter();
        guesses.add(letter);
        if (word.contains(letter)) return true;
        attempts -= 1;
        return false;
    }

    private boolean isInvalidLetter(String letter) {
        return !letter.matches("^[A-Z]$");
    }

    public static class GameErrorException extends Exception {
        public enum GameError { AlreadyGuessed, InvalidLetter }
        public GameError error;

        GameErrorException(GameError error) {
            super();
            this.error = error;
        }

        static GameErrorException alreadyGuessed() {
            return new GameErrorException(GameError.AlreadyGuessed);
        }

        static GameErrorException invalidLetter() {
            return new GameErrorException(GameError.InvalidLetter);
        }
    }
}
