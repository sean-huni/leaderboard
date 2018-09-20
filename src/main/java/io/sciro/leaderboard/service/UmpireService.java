package io.sciro.leaderboard.service;

import io.sciro.leaderboard.entity.LeagueEntry;
import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.exception.DefaultSwitchCaseException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.engine
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 23:29
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public interface UmpireService {
    /**
     * Computes the Umpire's decision for all matches played by a single players.
     *
     * @param matches played by a single player.
     * @return a single {@link LeagueEntry} record.
     */
    LeagueEntry computeLeagueEntryForSinglePlayer(Collection<Match> matches);

    /**
     * Computes the Umpire's decision for all played competitions for all players.
     *
     * @param matches played by all players.
     * @return a {@link Collection< LeagueEntry >} of the entire LeagueTable.
     */
    Collection<LeagueEntry> computeLeagueEntriesForAllPlayers(Collection<Match> matches);

    /**
     * Compute the results of a single match.
     *
     * @param player1  Person's choice.
     * @param pcPlayer Computer's/PC choice.
     * @return {@link Character} of the result.
     * @throws DefaultSwitchCaseException Unchecked Exception of an option that isn't found
     */
    Character computeDecision(String player1, String pcPlayer) throws DefaultSwitchCaseException;
}
