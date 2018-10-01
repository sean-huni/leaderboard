package io.sciro.leaderboard.service;

import io.sciro.leaderboard.entity.Match;

import java.util.Collection;
import java.util.List;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 21:32
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface MatchStatService {
    /**
     * Saves the most recent match.
     *
     * @param match match to be saved.
     * @return saved {@link Match}
     */
    Match saveMatchStat(Match match);

    /**
     * Finds a {@link Collection<Match>} of Matches played by a single player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches played by the player queried.
     */
    Collection<Match> findAllMatchStatsByName(String name);

    /**
     * Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    Collection<Match> findAllMatchStats();

    /**
     * Saves all matches.
     *
     * @param matches {@link List<Match>} to be saved.
     * @return saved {@link List<Match>}
     */
    Collection<Match> saveAllMatches(Iterable<Match> matches);
}
