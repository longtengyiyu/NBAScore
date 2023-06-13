package com.ltyy.nbascore.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Boxscore implements Parcelable {
     private String attendance;
     private int awayScore;
     private int homeScore;
     private String gameLength;
     private String leadChanges;
     private String period; //第几节
     private String status; //1未开始 2进行中3已结束
     private String statusDesc; //状态描述
     private String ties; //总场次

     protected Boxscore(Parcel in) {
          attendance = in.readString();
          awayScore = in.readInt();
          homeScore = in.readInt();
          gameLength = in.readString();
          leadChanges = in.readString();
          period = in.readString();
          status = in.readString();
          statusDesc = in.readString();
          ties = in.readString();
     }

     public static final Creator<Boxscore> CREATOR = new Creator<Boxscore>() {
          @Override
          public Boxscore createFromParcel(Parcel in) {
               return new Boxscore(in);
          }

          @Override
          public Boxscore[] newArray(int size) {
               return new Boxscore[size];
          }
     };

     public String getAttendance() {
          return attendance;
     }

     public void setAttendance(String attendance) {
          this.attendance = attendance;
     }

     public int getAwayScore() {
          return awayScore;
     }

     public void setAwayScore(int awayScore) {
          this.awayScore = awayScore;
     }

     public int getHomeScore() {
          return homeScore;
     }

     public void setHomeScore(int homeScore) {
          this.homeScore = homeScore;
     }

     public String getGameLength() {
          return gameLength;
     }

     public void setGameLength(String gameLength) {
          this.gameLength = gameLength;
     }

     public String getLeadChanges() {
          return leadChanges;
     }

     public void setLeadChanges(String leadChanges) {
          this.leadChanges = leadChanges;
     }

     public String getPeriod() {
          return period;
     }

     public void setPeriod(String period) {
          this.period = period;
     }

     public String getStatus() {
          return status;
     }

     public void setStatus(String status) {
          this.status = status;
     }

     public String getStatusDesc() {
          return statusDesc;
     }

     public void setStatusDesc(String statusDesc) {
          this.statusDesc = statusDesc;
     }

     public String getTies() {
          return ties;
     }

     public void setTies(String ties) {
          this.ties = ties;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(attendance);
          dest.writeInt(awayScore);
          dest.writeInt(homeScore);
          dest.writeString(gameLength);
          dest.writeString(leadChanges);
          dest.writeString(period);
          dest.writeString(status);
          dest.writeString(statusDesc);
          dest.writeString(ties);
     }
}
