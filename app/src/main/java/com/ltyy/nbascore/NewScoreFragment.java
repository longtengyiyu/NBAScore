package com.ltyy.nbascore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.ltyy.nbascore.app.AppParams;
import com.ltyy.nbascore.bean.Date;
import com.ltyy.nbascore.bean.Game;
import com.ltyy.nbascore.bean.Payload;
import com.ltyy.nbascore.bean.Schedule;
import com.ltyy.nbascore.bean.Score;
import com.ltyy.nbascore.request.MainRequest;
import com.ltyy.nbascore.utils.DataUtils;
import com.ltyy.nbascore.utils.LanguageUtils;
import com.ltyy.nbascore.utils.ViewsUtils;
import com.ltyy.nbascore.widget.ScoreView;

import java.util.List;

public class NewScoreFragment extends Fragment implements MainRequest.OnListResponse{
    private static final String TAG = NewScoreFragment.class.getSimpleName();
    private TextView tvStatus;
    private TextView tvHomeFieldName;
    private TextView tvAwayFieldName;
    private ScoreView viewScores;
    private TextView tvStartTime;
    private ProgressBar progressBar;

    private int position;

    public static NewScoreFragment newInstance(int position){
        NewScoreFragment fragment = new NewScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AppParams.KEY_POSITION, position);
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
        position = getArguments().getInt(AppParams.KEY_POSITION);
        findViews(view);
    }

    private void findViews(View v){
        tvStatus = v.findViewById(R.id.tv_status);
        viewScores = v.findViewById(R.id.view_score);
        tvHomeFieldName = v.findViewById(R.id.tv_home_field_name);
        tvAwayFieldName = v.findViewById(R.id.tv_away_field_name);
        tvStartTime = v.findViewById(R.id.tv_start_time);
        progressBar = v.findViewById(R.id.progress);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewsUtils.setViewsVisible(progressBar);
        MainRequest.getInstance().getScheduleList(this);
    }

    private void bindData(Score score){
        if (score != null){
            int status = score.getStatus();
            String tips = "";
            switch (status){
                case 1:
                    tips = String.format(getString(R.string.tips_not_started), score.getStartTime());
                    break;
                case 2:
                    tips = String.format(getString(R.string.tips_period), score.getPeriod());
                    break;
                case 3:
                    tips = getString(R.string.tips_completed);
                    break;
            }
            tvStatus.setText(tips);
            String homeTeamName = score.getHomeTeamNameEn();
            String awayTeamName = score.getAwayTeamNameEn();

            if (LanguageUtils.isChineseLanguage()){
                homeTeamName = score.getHomeTeam();
                awayTeamName =score.getAwayTeam();
            }
            tvHomeFieldName.setText(homeTeamName);
            tvAwayFieldName.setText(awayTeamName);

            viewScores.bindData(score.getHomeTeamScore(), score.getAwayTeamScore());
        }

//        ViewScores.bindData(score.getHomeFieldScores(), score.getAwayFieldScores());
//        String time = DataUtils.getCurrentDateFormat(score.getStartTime(), "HH:mm");
//        tvStartTime.setText(time);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainRequest.getInstance().stopDispose();
    }

    @Override
    public void list(List<Score> list) {
        ViewsUtils.setViewsGone(progressBar);
        if (list != null && !list.isEmpty()){
            Score score = list.get(position);
            Log.d(TAG, "score: " + new Gson().toJson(score));
            bindData(score);
            if (score.getStatus() == 2){
                MainRequest.getInstance().onGetList(6, this);
            }
        }
    }

    @Override
    public void onListEmpty() {
        MainRequest.getInstance().onGetList(6, this);
        ViewsUtils.setViewsGone(progressBar);
    }
}
