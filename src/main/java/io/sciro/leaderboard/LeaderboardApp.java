package io.sciro.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:12
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"io.sciro.leaderboard.feign", "io.sciro.leaderboard.config", "io.sciro.leaderboard.service",
        "io.sciro.leaderboard.engine", "io.sciro.leaderboard.controller"})
public class LeaderboardApp {
    public static void main(String[] args) {
        SpringApplication.run(LeaderboardApp.class, args);
    }
}
