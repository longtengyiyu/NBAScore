package com.ltyy.nbascore.bean;

import java.util.List;

public class Date {
    private List<Game> games;
    private int gameCount;
    private long utcMillis;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public long getUtcMillis() {
        return utcMillis;
    }

    public void setUtcMillis(long utcMillis) {
        this.utcMillis = utcMillis;
    }
}
