package io.sciro.leaderboard.config;

import feign.Logger;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.config
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 20:48
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        return new GsonDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
