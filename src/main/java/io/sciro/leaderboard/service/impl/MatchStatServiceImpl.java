package io.sciro.leaderboard.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.sciro.leaderboard.entity.Match;
import io.sciro.leaderboard.feign.LeaderDataFeignClientService;
import io.sciro.leaderboard.service.DBRecoveryAdapter;
import io.sciro.leaderboard.service.MatchStatFallbackService;
import io.sciro.leaderboard.service.MatchStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
public class MatchStatServiceImpl implements MatchStatService, DBRecoveryAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchStatServiceImpl.class);
    private LeaderDataFeignClientService leaderDataFeignClientService;
    private MatchStatFallbackService matchStatFallbackService;

    @Autowired
    public MatchStatServiceImpl(LeaderDataFeignClientService leaderDataFeignClientService) {
        this.leaderDataFeignClientService = leaderDataFeignClientService;
    }

    /**
     * Fallback service should the database go down, or remains offline.
     *
     * @param matchStatFallbackService field to be injected by the IoC BeanFactory.
     */
    @Autowired
    @Qualifier("fallback")
    public void setFallbackMatchStatService(MatchStatFallbackService matchStatFallbackService) {
        this.matchStatFallbackService = matchStatFallbackService;
    }


    /**
     * Saves the most recent match.
     *
     * @param match match to be saved.
     */
    @Override
    @HystrixCommand(fallbackMethod = "saveMatchStatFallback")
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
    @HystrixCommand(fallbackMethod = "findAllMatchStatsByNameFallback")
    public Collection<Match> findAllMatchStatsByName(String name) {
        return leaderDataFeignClientService.getAllMatchesByName(name);
    }

    /**
     * Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    @Override
    @HystrixCommand(fallbackMethod = "findAllMatchStatsFallback")
    public Collection<Match> findAllMatchStats() {
        return leaderDataFeignClientService.getAllMatches();
    }

    /**
     * Saves all matches.
     *
     * @param matches {@link List <Match>} to be saved.
     * @return saved {@link List<Match>}
     */
    public Collection<Match> saveAllMatches(Collection<Match> matches) {
        return leaderDataFeignClientService.saveAllMatches(matches);
    }


    /**
     * When the Circuit-Breaker is Closed... Happy Days.
     * No need to keep cached data.
     * Save the cached data to the DB & clear the cache.
     */
    public void saveAndFlushCachedData() {
        LOGGER.info("Attempting to execute saveAndFlushCachedData()...");

        Collection<Match> matches = findAllMatchStatsFallback();
        if (matches.size() > 0) {
            try {
                Collection<Match> matchCollection = saveAllMatches(matches);
                // if any of the records has an ID, lets assume the batch-transaction was successful.
                Optional<Match> optionalMatch = matchCollection.stream().findAny();

                if (optionalMatch.isPresent() && null != optionalMatch.get().getId()) {
                    // Lets clear the records from the cachedList & free-up memory, since the Leader-data DB is back online.
                    matchStatFallbackService.flashCachedList();
                }

            } // ConnectException might be thrown if Feign fails to connect to the Leader-Data Db.
            catch (Exception connExc) {
                LOGGER.warn("Leader-Data microservice is still OFFLINE/DOWN!!! :-(", connExc);
            }
        } else {
            LOGGER.info("All is well... Leader-Data microservice is still ONLINE!!! :-) THUMBS-UP!!!");
        }
    }


    /*
        ALL PRIVATE FALLBACK SERVICES UTILISED HERE!!!

        Spring-Cloud Netflix Hystrix
        Only triggered when the circuit is open.
     */

    /**
     * (Fallback) method to Save the most recent match.
     *
     * @param match match to be saved.
     */
    private Match saveMatchStatFallback(Match match) {
        LOGGER.warn("Leader-Data microservice is still OFFLINE/DOWN!!! :-(");
        logMessage("saveMatchStatFallback");
        return matchStatFallbackService.saveMatchStat(match);
    }


    /**
     * (Fallback) Finds a {@link Collection<Match>} of Matches played by a single player.
     *
     * @param name of the player.
     * @return a {@link Collection<Match>} of matches played by the player queried.
     */
    private Collection<Match> findAllMatchStatsByNameFallback(String name) {
        logMessage("findAllMatchStatsByNameFallback");
        return matchStatFallbackService.findAllMatchStatsByName(name);
    }

    /**
     * (Fallback) Finds a {@link Collection<Match>} of Matches played by all single player.
     *
     * @return a {@link Collection<Match>} of matches played by all players.
     */
    private Collection<Match> findAllMatchStatsFallback() {
        return matchStatFallbackService.findAllMatchStats();
    }


    private void logMessage(String operation) {
        LOGGER.info("Fallback Operation: {} is intact... We got your back for now... :-)", operation);
    }
}
