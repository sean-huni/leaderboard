package io.sciro.leaderboard.task;

import io.sciro.leaderboard.service.DBRecoveryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
//Couldn't get it to work with Spring-Cloud-Config
@PropertySource("classpath:bootstrap.yml")
public class MergeCachedRecordsToDBImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MergeCachedRecordsToDBImpl.class);
    private final DBRecoveryAdapter dbRecoveryAdapter;

    @Autowired
    public MergeCachedRecordsToDBImpl(DBRecoveryAdapter dbRecoveryAdapter) {
        this.dbRecoveryAdapter = dbRecoveryAdapter;
    }

    //ToDo: Couldn't get it to work with Spring-Cloud Config, but works with bootstrap.yml
    @Scheduled(cron = "${cron.exe.expression}")
    @Async
    public void purgeExpired() {
        LOGGER.info("Cron-Job Notification....");
        LOGGER.info("Cron-Job executed at: {}", new Timestamp(new Date().getTime()));
        dbRecoveryAdapter.saveAndFlushCachedData();
    }
}
