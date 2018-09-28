package io.sciro.leaderboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.exception
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 23:51
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Option not found! Only the following uppercase-options are allowed: ROCK, PAPER, SCISSORS")
public final class DefaultSwitchCaseException extends Exception {
    /**
     * Constructs an <code>Exception</code> with a detail message.
     *
     * @param s the detail message.
     */
    public DefaultSwitchCaseException(String s) {
        super(s);
    }
}
