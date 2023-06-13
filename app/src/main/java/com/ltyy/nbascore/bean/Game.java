package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private Profile profile;
    private Boxscore boxscore;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;

    protected Game(Parcel in) {
        boxscore = in.readParcelable(Boxscore.class.getClassLoader());
        homeTeam = in.readParcelable(HomeTeam.class.getClassLoader());
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boxscore getBoxscore() {
        return boxscore;
    }

    public void setBoxscore(Boxscore boxscore) {
        this.boxscore = boxscore;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(boxscore, flags);
        dest.writeParcelable(homeTeam, flags);
    }
}
