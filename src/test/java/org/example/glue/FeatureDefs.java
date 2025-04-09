package org.example.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeatureDefs {
    private TestableGame game;

    @Given("a new game with word {word} and {int} attempts")
    public void givenNewGame(String word, int attempts) {
        game = new TestableGame(word, attempts);
    }

    // TODO: Make a type of "letter"
    // TODO: Should not be throwing
    @When("I guess {word}")
    public void iGuessLetter(String letter) {
        game.addGuess(letter);
    }

    @Then("the game should not be over")
    public void theGameShouldNotBeOver() {
        game.play();
        assertFalse(game.isOver());
    }

    @Then("the game should be over")
    public void theGameShouldBeOver() {
        game.play();
        assertTrue(game.isOver());
    }
}
