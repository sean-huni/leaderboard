package io.sciro.leaderboard.task;

import io.sciro.leaderboard.service.DBRecoveryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.task
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 18:08
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class MergeCachedRecordsToDBImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MergeCachedRecordsToDBImpl.class);
    private final DBRecoveryAdapter dbRecoveryAdapter;

    @Autowired
    public MergeCachedRecordsToDBImpl(DBRecoveryAdapter dbRecoveryAdapter) {
        this.dbRecoveryAdapter = dbRecoveryAdapter;
    }

    @Scheduled(cron = "${purge.cron.expression}")
    @Async
    public void purgeExpired() {
        LOGGER.info("Cron-Job Notification....");
        LOGGER.info("Cron-Job executed at: {}", new Timestamp(new Date().getTime()));
        dbRecoveryAdapter.saveAndFlushCachedData();
    }
}
