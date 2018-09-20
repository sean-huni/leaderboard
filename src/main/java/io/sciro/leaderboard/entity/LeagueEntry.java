package io.sciro.leaderboard.entity;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.entity
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 21:19
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class LeagueEntry {
    private Long id;
    private String codeName;    // Player's name.
    private Long played;        // Matches-Played
    private Long won;           // Matches-Won
    private Long drew;          // Matches-Drawn
    private Long lost;          // Matches-Lost
    private Long points;        // Total-Points

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Long getPlayed() {
        return played;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    public Long getWon() {
        return won;
    }

    public void setWon(Long won) {
        this.won = won;
    }

    public Long getDrew() {
        return drew;
    }

    public void setDrew(Long drew) {
        this.drew = drew;
    }

    public Long getLost() {
        return lost;
    }

    public void setLost(Long lost) {
        this.lost = lost;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "LeagueEntry{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", played=" + played +
                ", won=" + won +
                ", drew=" + drew +
                ", lost=" + lost +
                ", points=" + points +
                '}';
    }
}
