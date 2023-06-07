package com.ltyy.nbascore.api;

import com.ltyy.nbascore.bean.Score;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/nba/list")
    Call<List<Score>> list();

    @GET("/nba/itemByIy")
    Call<Score> item(@Path("id") long id);

}
