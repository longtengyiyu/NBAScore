package com.ltyy.nbascore.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ltyy.nbascore.R;

public class ScoreView extends FrameLayout {
    public ScoreView(@NonNull Context context) {
        this(context, null);
    }

    public ScoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.widget_score,
                this, false);
        findViews(view);
    }

    private void findViews(View v){

    }

    public void bindData(int l, int r){
        if (l < 10){

        }else if (l < 100){

        }else {

        }

        if (r < 10){

        }else if (r < 100){

        }else{

        }
    }


}
