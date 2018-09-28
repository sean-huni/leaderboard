package io.sciro.leaderboard.fallback.impl;

import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.fallback.MatchStatFallbackService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service.impl
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 16:43
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class MatchStatStatFallbackServiceImpl implements MatchStatFallbackService {
    private static List<Match> matchList = new ArrayList<>();

    /**
     * Thread safe method to cache/add records to the {@link List<Match>}.
     *
     * @param match records to be cached in memory for the time being.
     * @return {@link Match}
     */
    private static synchronized Match saveNewMatchThreadSafe(Match match) {
//        long id = matchList.size() + 1L; //increment the id value per new entry.
//        match.setId(id);
        matchList.add(match);
        return match;
    }

    private static synchronized void clearCachedList() {
        matchList.clear();
    }

    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Saves the most recent match.
     *
     * @param match match to be saved.
     * @return saved {@link Match}
     */
    @Override
    public Match saveMatchStat(Match match) {
        return saveNewMatchThreadSafe(match);
    }

    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Finds a {@link Collection <Match>} of Matches played by a single player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches played by the player queried.
     */
    @Override
    public Collection<Match> findAllMatchStatsByName(String name) {
        return matchList.parallelStream().filter(match -> name.equalsIgnoreCase(match.getCodeName())).collect(Collectors.toList());
    }

    /**
     * (Fallback: Triggered when the Circuit-Breaker is Open)
     * Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    @Override
    public Collection<Match> findAllMatchStats() {
        return matchList;
    }

    /**
     * Assuming that the Leader-Data Microservice resurfaced back online:
     * 1. The cached records in the {@link List<Match>} have been merged in the DB.
     * 2. We need to flush/clear the records & keep it empty.
     */
    @Override
    public void flashCachedList() {
        clearCachedList();
    }


}
