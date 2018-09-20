package io.sciro.leaderboard.constant;

/**
 * PROJECT   : sciroc
 * PACKAGE   : io.game.sciroc.utils
 * USER      : sean
 * DATE      : 16-Sun-Sep-2018
 * TIME      : 21:18
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public enum Result {
    WON('W'),
    DREW('D'),
    LOST('L');

    private Character result;

    Result(Character result) {
        this.result = result;
    }

    public Character getResult() {
        return result;
    }
}
