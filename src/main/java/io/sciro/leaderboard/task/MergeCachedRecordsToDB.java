package io.sciro.leaderboard.task;

import io.sciro.leaderboard.service.DBRecoveryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.task
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 18:08
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
@PropertySource("classpath:leaderboard-service-dev.yml")
public class MergeCachedRecordsToDB {
    private final DBRecoveryAdapter dbRecoveryAdapter;

    @Autowired
    public MergeCachedRecordsToDB(DBRecoveryAdapter dbRecoveryAdapter) {
        this.dbRecoveryAdapter = dbRecoveryAdapter;
    }

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {
        dbRecoveryAdapter.saveAndFlushCachedData();
    }
}
