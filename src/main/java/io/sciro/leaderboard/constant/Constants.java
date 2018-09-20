package io.sciro.leaderboard.constant;

import io.sciro.leaderboard.exception.ConstructorAccessException;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.contant
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 23:59
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class Constants {
    public static final String ROCK = "ROCK";
    public static final String PAPER = "PAPER";
    public static final String SCISSORS = "SCISSORS";

    /**
     * Instantiation of the class is not permitted.
     *
     * @throws ConstructorAccessException if an attempt to instantiate the class is picked up.
     */
    private Constants() throws ConstructorAccessException {
        throw new ConstructorAccessException("Not allowed to access Constants-Constructor.");
    }
}
