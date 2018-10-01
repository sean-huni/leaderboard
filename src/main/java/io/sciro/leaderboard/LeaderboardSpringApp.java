package io.sciro.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:12
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"io.sciro.leaderboard.feign", "io.sciro.leaderboard.config",
        "io.sciro.leaderboard.service", "io.sciro.leaderboard.controller", "io.sciro.leaderboard.task"})
public class LeaderboardSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(LeaderboardSpringApp.class, args);
    }


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }


    private ApiInfo generateApiInfo() {
        return new ApiInfo("Leaderboard Microservice", "The Leaderboard Microservice is used to store, process match-stats/scores & leaderboards league.", "Version 1.0",
                "urn:tos", "kudzai@bcs.org", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
