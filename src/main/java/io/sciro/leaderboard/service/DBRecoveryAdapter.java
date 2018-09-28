package io.sciro.leaderboard.service;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 19:07
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */

public interface DBRecoveryAdapter {

    /**
     * When the Circuit-Breaker is Closed... Happy Days.
     * No need to keep cached data.
     * Save the cached data to the DB & clear the cache.
     */
    void saveAndFlushCachedData();
}
