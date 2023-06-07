package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {
    private long id;
    private String homeField;
    private String homeFieldSimple;
    private String awayField;
    private String awayFieldSimple;
    private int homeFieldScores;
    private int awayFieldScores;
    private int status;
    private long startTime;
    private long createTime;
    private long updateTime;

    protected Score(Parcel in) {
        id = in.readLong();
        homeField = in.readString();
        homeFieldSimple = in.readString();
        awayField = in.readString();
        awayFieldSimple = in.readString();
        homeFieldScores = in.readInt();
        awayFieldScores = in.readInt();
        status = in.readInt();
        startTime = in.readLong();
        createTime = in.readLong();
        updateTime = in.readLong();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(homeField);
        dest.writeString(homeFieldSimple);
        dest.writeString(awayField);
        dest.writeString(awayFieldSimple);
        dest.writeInt(homeFieldScores);
        dest.writeInt(awayFieldScores);
        dest.writeInt(status);
        dest.writeLong(startTime);
        dest.writeLong(createTime);
        dest.writeLong(updateTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHomeField() {
        return homeField;
    }

    public void setHomeField(String homeField) {
        this.homeField = homeField;
    }

    public String getHomeFieldSimple() {
        return homeFieldSimple;
    }

    public void setHomeFieldSimple(String homeFieldSimple) {
        this.homeFieldSimple = homeFieldSimple;
    }

    public String getAwayField() {
        return awayField;
    }

    public void setAwayField(String awayField) {
        this.awayField = awayField;
    }

    public String getAwayFieldSimple() {
        return awayFieldSimple;
    }

    public void setAwayFieldSimple(String awayFieldSimple) {
        this.awayFieldSimple = awayFieldSimple;
    }

    public int getHomeFieldScores() {
        return homeFieldScores;
    }

    public void setHomeFieldScores(int homeFieldScores) {
        this.homeFieldScores = homeFieldScores;
    }

    public int getAwayFieldScores() {
        return awayFieldScores;
    }

    public void setAwayFieldScores(int awayFieldScores) {
        this.awayFieldScores = awayFieldScores;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
