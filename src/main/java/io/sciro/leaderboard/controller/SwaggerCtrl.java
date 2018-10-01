package io.sciro.leaderboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.controller
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 15:42
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 // */
@Controller
@ApiIgnore
public class SwaggerCtrl {

    @RequestMapping("/swagger-2")
    public String home() {
        return "redirect:swagger-ui.html";
    }

}
