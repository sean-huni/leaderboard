package io.sciro.leaderboard.controller;

import io.sciro.leaderboard.service.UmpireService;
import io.sciro.leaderboard.entity.LeagueEntry;
import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.service.MatchStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.controller
 * USER      : sean
 * DATE      : 18-Tue-Sep-2018
 * TIME      : 07:41
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@RestController
@RequestMapping("/game-match")
public class LeaderboardCtrl {
    private UmpireService umpireService;
    private MatchStatService matchStatService;

    @Autowired
    public LeaderboardCtrl(MatchStatService matchStatService) {
        this.matchStatService = matchStatService;
    }

    @Autowired
    public void setUmpireService(UmpireService umpireService) {
        this.umpireService = umpireService;
    }

    /**
     * Finds a list of all the matches.
     *
     * @return a list of all the matches.
     */
    @GetMapping(value = "/get-all", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Match> getAllMatches() {
        return matchStatService.findAllMatchStats();
    }

    /**
     * Matches played by a singles queried player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches that meet the criteria.
     */
    @GetMapping(value = "/get/{name}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<Match> getMatchByPlayerName(@PathVariable String name) {
        return matchStatService.findAllMatchStatsByName(name);
    }

    /**
     * Save a new match.
     *
     * @param match to be saved.
     */
    @PostMapping(value = "/save", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Match saveLatestMatch(@RequestBody Match match) {
        return matchStatService.saveMatchStat(match);
    }

    /**
     * Computes the decision of the recent match.
     *
     * @param player1 choice.
     * @param player2 choice.
     * @return Result {@link Character} of the match.
     */
    @GetMapping(value = "/decision/{player1}/{player2}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Character getUmpireDecision(@PathVariable("player1") String player1, @PathVariable("player2") String player2) {
        return umpireService.computeDecision(player1, player2);
    }

    /**
     * Finds all League entries in the database.
     *
     * @return a League-Table of all {@link List < LeagueEntry >}.
     */
    @GetMapping(value = "/get-league", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<LeagueEntry> getLeagueTable() {
        return umpireService.computeLeagueEntriesForAllPlayers(matchStatService.findAllMatchStats());
    }
}
