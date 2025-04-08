Feature: Game end

  The game ends if you have won or lost

  Scenario: Game ends if you have lost
    Given a new game with word DOG and 0 attempts
    Then the game should be over