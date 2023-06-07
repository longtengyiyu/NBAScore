package com.ltyy.nbascore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ltyy.nbascore.app.AppParams;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.utils.DataUtils;

public class ScoreFragment extends Fragment {

    private Score score;
    private TextView tvStatus;
    private TextView tvHomeFieldName;
    private TextView tvAwayFieldName;
    private TextView tvScores;
    private TextView tvStartTime;

    public static ScoreFragment newInstance(Score score){
        ScoreFragment fragment = new ScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppParams.KEY_DATA, score);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        score = getArguments().getParcelable(AppParams.KEY_DATA);
        findViews(view);
        bindData();
    }

    private void findViews(View v){
        tvStatus = v.findViewById(R.id.tv_status);
        tvScores = v.findViewById(R.id.tv_scores);
        tvHomeFieldName = v.findViewById(R.id.tv_home_field_name);
        tvAwayFieldName = v.findViewById(R.id.tv_away_field_name);
        tvStartTime = v.findViewById(R.id.tv_start_time);
    }

    private void bindData(){
        if (score != null){
            int status = score.getStatus();
            String tips = "";
            switch (status){
                case 0:
                    tips = getString(R.string.tips_not_started);
                    break;
                case 1:
                    tips = getString(R.string.tips_in_progress);
                    break;
                case 2:
                    tips = getString(R.string.tips_completed);
                    break;
            }
            tvStatus.setText(tips);
        }
        tvHomeFieldName.setText(score.getHomeField());
        tvAwayFieldName.setText(score.getAwayField());
        tvScores.setText(score.getHomeFieldScores() + ":" + score.getAwayFieldScores());
        String time = DataUtils.getCurrentDateFormat(score.getStartTime(), "HH:mm");
        tvStartTime.setText(time);
    }

}
