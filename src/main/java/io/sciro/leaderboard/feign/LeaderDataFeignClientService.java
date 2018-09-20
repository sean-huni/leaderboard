package io.sciro.leaderboard.feign;

import io.sciro.leaderboard.entity.Match;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.service
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 19:41
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@FeignClient(value = "LEADER-DATA")
@RibbonClient("LEADER-DATA")
public interface LeaderDataFeignClientService {

    @GetMapping(path = "/match/")
    Collection<Match> getAllMatches();

    @GetMapping(path = "/match/by-name/{name}")
    Collection<Match> getAllMatchesByName(@PathVariable("name") String name);

    @GetMapping(path = "/match/{id}")
    Match getMatchById(@PathVariable("id") Long id);

    @PostMapping(path = "/match/")
    Match saveNewMatch(@RequestBody Match match);

}
