package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String abbr;
    private String city;
    private String cityEn;
    private String code;
    private String conference;
    private String displayAbbr;
    private String displayConference;
    private String division;
    private String id;
    private String isAllStarTeam;
    private String isLeagueTeam;
    private String leagueId;
    private String name;
    private String nameEn;

    protected Team(Parcel in) {
        abbr = in.readString();
        city = in.readString();
        cityEn = in.readString();
        code = in.readString();
        conference = in.readString();
        displayAbbr = in.readString();
        displayConference = in.readString();
        division = in.readString();
        id = in.readString();
        isAllStarTeam = in.readString();
        isLeagueTeam = in.readString();
        leagueId = in.readString();
        name = in.readString();
        nameEn = in.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDisplayAbbr() {
        return displayAbbr;
    }

    public void setDisplayAbbr(String displayAbbr) {
        this.displayAbbr = displayAbbr;
    }

    public String getDisplayConference() {
        return displayConference;
    }

    public void setDisplayConference(String displayConference) {
        this.displayConference = displayConference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsAllStarTeam() {
        return isAllStarTeam;
    }

    public void setIsAllStarTeam(String isAllStarTeam) {
        this.isAllStarTeam = isAllStarTeam;
    }

    public String getIsLeagueTeam() {
        return isLeagueTeam;
    }

    public void setIsLeagueTeam(String isLeagueTeam) {
        this.isLeagueTeam = isLeagueTeam;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(abbr);
        dest.writeString(city);
        dest.writeString(cityEn);
        dest.writeString(code);
        dest.writeString(conference);
        dest.writeString(displayAbbr);
        dest.writeString(displayConference);
        dest.writeString(division);
        dest.writeString(id);
        dest.writeString(isAllStarTeam);
        dest.writeString(isLeagueTeam);
        dest.writeString(leagueId);
        dest.writeString(name);
        dest.writeString(nameEn);
    }
}
