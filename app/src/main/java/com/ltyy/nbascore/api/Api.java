package com.ltyy.nbascore.api;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.ltyy.nbascore.BuildConfig;
import com.ltyy.nbascore.bean.Schedule;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.request.MainRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String TAG = Api.class.getSimpleName();
    public volatile static Api INSTANCE = null;
    private ApiService apiService;

    private Api(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
        Interceptor requestInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36")
                    .addHeader("content-type", "application/json")
                    .build();
            return chain.proceed(request);
        };
        builder.addInterceptor(requestInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
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

    public void getSchedule(String data, String locale, String tz, MainRequest.OnScheduleResponse callback){
        Call<Schedule> call = apiService.getSchedule(data, locale, tz);
        Log.d(TAG, "request url --> " + call.request().url().toString());
        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Schedule s = response.body();
                Log.d(TAG, "schedule --> " + new Gson().toJson(s));
                if (callback != null){
                    if (s != null){
                        callback.schedule(s);
                    }else {
                        callback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Log.d(TAG, "onFailure --> " + t.getMessage());
                callback.onError();
            }
        });
    }
}
