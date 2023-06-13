package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeTeam implements Parcelable {
    private Team profile;
    private String matchup;

    protected HomeTeam(Parcel in) {
        profile = in.readParcelable(Team.class.getClassLoader());
        matchup = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(profile, flags);
        dest.writeString(matchup);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomeTeam> CREATOR = new Creator<HomeTeam>() {
        @Override
        public HomeTeam createFromParcel(Parcel in) {
            return new HomeTeam(in);
        }

        @Override
        public HomeTeam[] newArray(int size) {
            return new HomeTeam[size];
        }
    };

    public Team getProfile() {
        return profile;
    }

    public void setProfile(Team profile) {
        this.profile = profile;
    }

    public String getMatchup() {
        return matchup;
    }

    public void setMatchup(String matchup) {
        this.matchup = matchup;
    }
}
