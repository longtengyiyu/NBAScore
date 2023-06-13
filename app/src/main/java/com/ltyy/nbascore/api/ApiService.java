package com.ltyy.nbascore.api;

import com.ltyy.nbascore.bean.Schedule;
import com.ltyy.nbascore.bean.Score;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/nba/list")
    Call<List<Score>> list();

    @GET("/nba/itemByIy")
    Call<Score> item(@Path("id") long id);

    //https://m.china.nba.cn/stats2/season/schedule.json?gameDate=2023-06-08&locale=zh_CN&tz=%2B8
    @GET("stats2/season/schedule.json")
    Call<Schedule> getSchedule(@Query(value = "gameDate", encoded = false) String date,
                               @Query(value = "locale", encoded = false) String locale,
                               @Query(value = "tz", encoded = false) String tz
    );


}
