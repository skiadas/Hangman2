Feature: Game end

  The game ends if you have won or lost

  Scenario: Game ends if you have lost
    Given a new game with word DOG and 0 attempts
    Then the game should be over

  Scenario: Game ends if you have won
    Given a new game with word D and 4 attempts
    When I guess D
    Then the game should be over

  Scenario: Game doesn't end if you have attempts left and not guessed correctly
    Given a new game with word DOG and 1 attempts
    Then the game should not be over
