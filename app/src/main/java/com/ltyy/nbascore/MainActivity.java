package com.ltyy.nbascore;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.ltyy.nbascore.adapter.ScoreAdapter;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.request.MainRequest;
import com.ltyy.nbascore.utils.ViewsUtils;

import java.util.List;

public class MainActivity extends FragmentActivity implements MainRequest.OnListResponse, MainRequest.OnItemResponse {

    private ViewPager2 viewPager;
    private ScoreAdapter adapter;
    private TextView tvTips;
    private MainRequest request;
    private int currentPage;
    private List<Score> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        onRequest();
    }

    private void findViews(){
        viewPager =  findViewById(R.id.view_pager);
        tvTips = findViewById(R.id.tv_tips);
        viewPager.registerOnPageChangeCallback(new OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPage = position;
                onItemRequest();
            }
        });
    }

    private void onRequest(){
        request = new MainRequest();
        request.getList(this);
    }

    private void onItemRequest(){
        request.getItemById(list.get(currentPage).getId(), this);
    }

    @Override
    public void list(List<Score> list) {
        this.list = list;
        ViewsUtils.setViewsGone(tvTips);
        ViewsUtils.setViewsVisible(viewPager);
        adapter = new ScoreAdapter(this, list);
        viewPager.setAdapter(adapter);
        onItemRequest();
    }

    @Override
    public void onListEmpty() {
        ViewsUtils.setViewsGone(viewPager);
        ViewsUtils.setViewsVisible(tvTips);
    }

    @Override
    public void item(Score score) {

    }

    @Override
    public void onItemEmpty() {

    }
}