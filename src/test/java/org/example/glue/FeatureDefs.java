package org.example.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class FeatureDefs {
    private TestableGame game;

    @Given("a new game with word {word} and {int} attempts")
    public void givenNewGame(String word, int attempts) {
        game = new TestableGame(word, attempts);
    }

    @Then("the game should be over")
    public void theGameShouldBeOver() {
        game.play();
        assertTrue(game.isOver());
    }
}
