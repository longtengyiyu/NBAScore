package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Score{
   private String homeTeam;
    private String homeTeamNameEn;
    private String awayTeam;
    private String awayTeamNameEn;
    private int homeTeamScore;
    private int awayTeamScore;
    private int status;
    private String startTime;
    private int gameType;
    private String period;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeTeamNameEn() {
        return homeTeamNameEn;
    }

    public void setHomeTeamNameEn(String homeTeamNameEn) {
        this.homeTeamNameEn = homeTeamNameEn;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeamName) {
        this.awayTeam = awayTeam;
    }

    public String getAwayTeamNameEn() {
        return awayTeamNameEn;
    }

    public void setAwayTeamNameEn(String awayTeamNameEn) {
        this.awayTeamNameEn = awayTeamNameEn;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
