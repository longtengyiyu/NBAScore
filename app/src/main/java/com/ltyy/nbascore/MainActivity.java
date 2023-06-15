package com.ltyy.nbascore;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.google.gson.Gson;
import com.ltyy.nbascore.adapter.ScoreAdapter;
import com.ltyy.nbascore.bean.Date;
import com.ltyy.nbascore.bean.Game;
import com.ltyy.nbascore.bean.Payload;
import com.ltyy.nbascore.bean.Schedule;
import com.ltyy.nbascore.request.MainRequest;
import com.ltyy.nbascore.utils.DataUtils;
import com.ltyy.nbascore.utils.ViewsUtils;

import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends FragmentActivity implements MainRequest.OnScheduleResponse {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager2 viewPager;
    private CircleIndicator3 indicator;
    private TextView tvTips;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onRequest();
        keepScreenOn();
    }

    private void keepScreenOn(){
        // 获取 PowerManager 实例
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        // 设置 WakeLock 标志，SCREEN_DIM_WAKE_LOCK 表示屏幕保持暗淡亮起
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyApp:WakeLockTag");
        wakeLock.acquire(); // 获取 WakeLock
    }

    private void findViews(){
        viewPager = findViewById(R.id.view_pager);
        tvTips = findViewById(R.id.tv_tips);
        viewPager.registerOnPageChangeCallback(new OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        indicator = findViewById(R.id.indicator);
    }

    private void setIndicators(ScoreAdapter adapter){
        indicator.setViewPager(viewPager);
        adapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());
    }

    private void onRequest(){
        MainRequest.getInstance().loadData(this);
    }

    @Override
    public void schedule(Schedule schedule) {
        if (schedule != null){
            Log.d(TAG, "schedule --> " + new Gson().toJson(schedule));
            Payload payload = schedule.getPayload();
            if (payload != null){
                List<Date> dates = payload.getDates();
                if (dates != null && !dates.isEmpty()){
                    for (Date date : dates){
                        long utcMillis = date.getUtcMillis();
                        String gameDate = DataUtils.getCurrentDateFormat(utcMillis, "yyyy-MM-dd");
                        String currDate = DataUtils.getCurrentDateFormat(System.currentTimeMillis(), "yyyy-MM-dd");
                        Log.d(TAG, "gameDate: " + gameDate);
                        Log.d(TAG, "currDate: " + currDate);
                        if (gameDate.equals(currDate)){
                            List<Game> games = date.getGames();
                            if (games != null && !games.isEmpty()){
                                ViewsUtils.setViewsGone(tvTips);
                                ViewsUtils.setViewsVisible(viewPager);
                                ScoreAdapter adapter = new ScoreAdapter(this, games);
                                viewPager.setAdapter(adapter);
                                setIndicators(adapter);
                                Log.d(TAG, "adapter 设置成功");
                            }else {
                                ViewsUtils.setViewsVisible(tvTips);
                                ViewsUtils.setViewsGone(viewPager);
                            }
                            break;
                        }else{
                            ViewsUtils.setViewsVisible(tvTips);
                            ViewsUtils.setViewsGone(viewPager);
                        }
                    }
                }else{
                    ViewsUtils.setViewsVisible(tvTips);
                    ViewsUtils.setViewsGone(viewPager);
                }
            }
        }
    }

    @Override
    public void onError() {
        ViewsUtils.setViewsVisible(tvTips);
        ViewsUtils.setViewsGone(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release(); // 释放 WakeLock
        }
    }
}