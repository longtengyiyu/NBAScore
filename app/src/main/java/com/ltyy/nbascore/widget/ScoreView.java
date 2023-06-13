package com.ltyy.nbascore.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ltyy.nbascore.R;

public class ScoreView extends FrameLayout {

    private ImageView lHundred,lTen,lUnit,rHundred,rTen,rUnit;

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
                this, true);
        findViews(view);
    }

    private void findViews(View v){
        lHundred = v.findViewById(R.id.iv_l_hundred);
        lTen= v.findViewById(R.id.iv_l_ten);
        lUnit= v.findViewById(R.id.iv_l_unit);
        rHundred= v.findViewById(R.id.iv_r_hundred);
        rTen= v.findViewById(R.id.iv_r_ten);
        rUnit= v.findViewById(R.id.iv_r_unit);
    }

    public void bindData(int l, int r){
        lHundred.setImageResource(getImageResourceByNum(getDigitHundreds(l)));
        lTen.setImageResource(getImageResourceByNum(getDigitTens(l)));
        lUnit.setImageResource(getImageResourceByNum(getDigitOnes(l)));

        rHundred.setImageResource(getImageResourceByNum(getDigitHundreds(r)));
        rTen.setImageResource(getImageResourceByNum(getDigitTens(r)));
        rUnit.setImageResource(getImageResourceByNum(getDigitOnes(r)));
    }

    private int getImageResourceByNum(int n){
        switch (n){
            case 0:
                return R.drawable.ic_0;
            case 1:
                return R.drawable.ic_1;
            case 2:
                return R.drawable.ic_2;
            case 3:
                return R.drawable.ic_3;
            case 4:
                return R.drawable.ic_4;
            case 5:
                return R.drawable.ic_5;
            case 6:
                return R.drawable.ic_6;
            case 7:
                return R.drawable.ic_7;
            case 8:
                return R.drawable.ic_8;
            case 9:
                return R.drawable.ic_9;
        }
        return R.drawable.ic_0;
    }

    private int getDigitOnes(int n){
        return n % 10;
    }

    private int getDigitTens(int n){
        return (n / 10) % 10;
    }

    private int getDigitHundreds(int n){
        return (n / 100) % 10;
    }
}
