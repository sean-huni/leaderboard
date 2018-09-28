package io.sciro.leaderboard.service.impl;

import io.sciro.leaderboard.constant.HandSignal;
import io.sciro.leaderboard.constant.Result;
import io.sciro.leaderboard.entity.LeagueEntry;
import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.exception.DefaultSwitchCaseException;
import io.sciro.leaderboard.service.UmpireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static io.sciro.leaderboard.constant.Constants.*;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.engine.impl
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 23:30
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class UmpireServiceImpl implements UmpireService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmpireServiceImpl.class);

    /**
     * Computes the Umpire's decision for all matches played by a single players.
     *
     * @param matches played by a single player.
     * @return a single {@link LeagueEntry} record.
     */
    @Override
    public LeagueEntry computeLeagueEntryForSinglePlayer(Collection<Match> matches) {
        return computePlayerLeagueEntry(matches, 1L, matches.stream().map(Match::getCodeName).findAny().orElse("No-Name"));
    }

    /**
     * Computes the Umpire's decision for all played competitions for all players.
     *
     * @param matches played by all players.
     * @return a {@link Collection<LeagueEntry>} of the entire LeagueTable.
     */
    @Override
    public Collection<LeagueEntry> computeLeagueEntriesForAllPlayers(Collection<Match> matches) {
        Map<String, LeagueEntry> leagueEntriesMap = new HashMap<>();

        Set<String> codeNames = matches.parallelStream().map(Match::getCodeName).collect(Collectors.toSet());

        long recordId = 1;
        for (String codeName : codeNames) {
            Collection<Match> playerMatches = matches.parallelStream().filter(x -> codeName.equalsIgnoreCase(x.getCodeName())).collect(Collectors.toCollection(ArrayList::new));
            leagueEntriesMap.put(codeName, computePlayerLeagueEntry(playerMatches, recordId, codeName));
            recordId++;
        }

        return leagueEntriesMap.values();
    }


    /**
     * Computes the following attributes:
     * - Matches-Played
     * - Won
     * - Drawn
     * - Lost
     * - Points
     *
     * @param playerMatches
     * @param entryId
     * @param playerName
     * @return
     */
    private LeagueEntry computePlayerLeagueEntry(Collection<Match> playerMatches, Long entryId, String playerName) {
        LeagueEntry leagueEntry = new LeagueEntry();
        leagueEntry.setId(entryId);
        leagueEntry.setCodeName(playerName);

        if (playerMatches != null) {
            long drew = playerMatches.stream().filter(match -> Result.DREW.getResult().compareTo(match.getResult()) == 0).count();
            long won = playerMatches.stream().filter(match -> Result.WON.getResult().compareTo(match.getResult()) == 0).count();
            long lost = playerMatches.stream().filter(match -> Result.LOST.getResult().compareTo(match.getResult()) == 0).count();
            long totalPlayed = playerMatches.size();

            long totalPoints = pointsOfMatchesWon(won) + pointsOfMatchesDrawn(drew) + pointsOfMatchesLost(lost);

            leagueEntry.setWon(won);
            leagueEntry.setDrew(drew);
            leagueEntry.setLost(lost);
            leagueEntry.setPlayed(totalPlayed);
            leagueEntry.setPoints(totalPoints);
        }

        return leagueEntry;
    }

    /**
     * Computes the total score from total matches won.
     *
     * @param n total number of matches won.
     * @return total points of matches won.
     */
    private long pointsOfMatchesWon(long n) {
        return n * 3;
    }

    /**
     * Computes the total score from total matches lost.
     * Note: May be redundant, but what if the points/scoring
     * system changes in the future?
     *
     * @param n total number of matches lost.
     * @return total points of matches lost.
     */
    private long pointsOfMatchesLost(long n) {
        return 0L;
    }

    /**
     * Computes the total score from total matches drawn.
     *
     * @param n total number of matches drawn.
     * @return total points of matches drawn.
     */
    private long pointsOfMatchesDrawn(long n) {
        return n;
    }

    /**
     * Referee is the decision maker. (Business Rule)
     * Determines who wins between the Player & the PC.
     *
     * @param player1  Person's choice.
     * @param pcPlayer Computer's/PC choice.
     * @return result of the match.
     * @throws DefaultSwitchCaseException unchecked exception.
     */
    public Character computeDecision(String player1, String pcPlayer) throws DefaultSwitchCaseException {
        Character c;

        if (player1.equals(pcPlayer)) {
            return Result.DREW.getResult();
        }

        switch (player1) {
            case ROCK:
                c = pcPlayer.equals(HandSignal.PAP.getSignal()) ? Result.LOST.getResult() : Result.WON.getResult();
                break;
            case PAPER:
                c = pcPlayer.equals(HandSignal.SCI.getSignal()) ? Result.LOST.getResult() : Result.WON.getResult();
                break;
            case SCISSORS:
                c = pcPlayer.equals(HandSignal.ROC.getSignal()) ? Result.LOST.getResult() : Result.WON.getResult();
                break;
            default:
                final String ex = "Bad request. Option not found! Only the following options are allowed: ROCK, PAPER, SCISSORS";
                LOGGER.error(ex);
                throw new DefaultSwitchCaseException(ex);
        }

        return c;
    }
}
