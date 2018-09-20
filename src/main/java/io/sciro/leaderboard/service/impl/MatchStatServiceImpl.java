package io.sciro.leaderboard.service.impl;

import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.service.MatchStatService;
import io.sciro.leaderboard.feign.LeaderDataFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service.impl
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 22:37
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class MatchStatServiceImpl implements MatchStatService {
    private LeaderDataFeignClientService leaderDataFeignClientService;

    @Autowired
    public MatchStatServiceImpl(LeaderDataFeignClientService leaderDataFeignClientService) {
        this.leaderDataFeignClientService = leaderDataFeignClientService;
    }

    /**
     * Saves the most recent match.
     *
     * @param match match to be saved.
     */
    @Override
    public Match saveMatchStat(Match match) {
        return leaderDataFeignClientService.saveNewMatch(match);
    }

    /**
     * Finds a {@link Collection<Match>} of Matches played by a single player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches played by the player queried.
     */
    @Override
    public Collection<Match> findAllMatchStatsByName(String name) {
        return leaderDataFeignClientService.getAllMatchesByName(name);
    }

    /**
     * Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    @Override
    public Collection<Match> findAllMatchStats() {
        return leaderDataFeignClientService.getAllMatches();
    }
}
