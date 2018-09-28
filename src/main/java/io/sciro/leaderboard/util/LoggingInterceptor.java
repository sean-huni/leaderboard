package io.sciro.leaderboard.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.util
 * USER      : sean
 * DATE      : 28-Fri-Sep-2018
 * TIME      : 15:56
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class LoggingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("method: ").append(request.getMethod()).append("\t");
        logMessage.append("uri: ").append(request.getRequestURI()).append("\t");
        logMessage.append("status: ").append(response.getStatus()).append("\t");
        logMessage.append("remoteAddress: ").append(request.getRemoteAddr()).append("\t");

        if (ex != null) {
            LoggingInterceptor.LOGGER.error(logMessage.toString(), ex);
        } else {
            LoggingInterceptor.LOGGER.info(logMessage.toString());
        }
    }
}
