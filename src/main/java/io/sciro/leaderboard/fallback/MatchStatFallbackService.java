package io.sciro.leaderboard.fallback;

import io.sciro.leaderboard.entity.Match;

import java.util.Collection;
import java.util.List;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 17:17
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface MatchStatFallbackService {
    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Saves the most recent match.
     *
     * @param match match to be saved.
     * @return saved {@link Match}
     */
    Match saveMatchStat(Match match);

    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Finds a {@link Collection <Match>} of Matches played by a single player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches played by the player queried.
     */
    Collection<Match> findAllMatchStatsByName(String name);

    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    Collection<Match> findAllMatchStats();

    /**
     * Assuming that the Leader-Data Microservice resurfaced back online:
     * 1. The cached records in the {@link List<Match>} have been merged in the DB.
     * 2. We need to flush/clear the records & keep it empty.
     */
    void flashCachedList();
}
