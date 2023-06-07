package com.ltyy.nbascore.api;

import com.ltyy.nbascore.BuildConfig;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.request.MainRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public volatile static Api INSTANCE = null;
    private ApiService apiService;

    private Api(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static void create(){
        if (INSTANCE == null){
            synchronized (Api.class){
                if (INSTANCE == null){
                    INSTANCE = new Api();
                }
            }
        }
    }

    public static Api getInstance(){
        return INSTANCE;
    }

    public void getList(final MainRequest.OnListResponse callBack){
        Call<List<Score>> call = apiService.list();
        call.enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                List<Score> scores = response.body();
                if (callBack != null){
                    if (scores != null){
                        callBack.list(scores);
                    }else{
                        callBack.onListEmpty();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable t) {
                callBack.onListEmpty();
            }
        });
    }

    public void getItemById(long id, final MainRequest.OnItemResponse callback){
        Call<Score> call = apiService.item(id);
        call.enqueue(new Callback<Score>() {
            @Override
            public void onResponse(Call<Score> call, Response<Score> response) {
                Score score = response.body();
                if (callback != null){
                    if (score != null){
                        callback.item(score);
                    }else{
                        callback.onItemEmpty();
                    }
                }
            }

            @Override
            public void onFailure(Call<Score> call, Throwable t) {
                if (callback != null){
                    callback.onItemEmpty();
                }
            }
        });
    }
}
