package io.sciro.leaderboard.exception;

public final class ConstructorAccessException extends IllegalAccessException {

    /**
     * Constructs an <code>IllegalAccessException</code> with a detail message.
     *
     * @param s the detail message.
     */
    public ConstructorAccessException(String s) {
        super(s);
    }
}