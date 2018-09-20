package io.sciro.leaderboard.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * PROJECT   : leaderboard
 * PACKAGE   : io.sciro.leaderboard.entity
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 19:42
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class Match {
    @SerializedName("id")
    private Long id;
    @SerializedName("codeName")
    private String codeName;
    @SerializedName("round")
    private Long round;
    @SerializedName("me")
    private String me;
    @SerializedName("pc")
    private String pc;
    @SerializedName("result")
    private Character result;
    @SerializedName("timestamp")
    private Date timestamp;
    @SerializedName("lastUpdated")
    private Date lastUpdated;
    @SerializedName("created")
    private Date created;

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

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public Character getResult() {
        return result;
    }

    public void setResult(Character result) {
        this.result = result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
