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
import com.ltyy.nbascore.adapter.ScoreAdapter;
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

public class ScoreFragment extends Fragment implements MainRequest.OnScheduleResponse{
    private static final String TAG = ScoreFragment.class.getSimpleName();
    private TextView tvStatus;
    private TextView tvHomeFieldName;
    private TextView tvAwayFieldName;
    private ScoreView viewScores;
    private TextView tvStartTime;
    private ProgressBar progressBar;

    private int position;

    public static ScoreFragment newInstance(int position){
        ScoreFragment fragment = new ScoreFragment();
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
        MainRequest.getInstance().loadData(this);
    }

    private void bindData(Game game){
        if (game != null){
            int status = Integer.parseInt(game.getBoxscore().getStatus());
            String tips = "";
            switch (status){
                case 1:
                    tips = getString(R.string.tips_not_started);
                    break;
                case 2:
                    tips = String.format(getString(R.string.tips_period), game.getBoxscore().getPeriod());
                    break;
                case 3:
                    tips = getString(R.string.tips_completed);
                    break;
            }
            tvStatus.setText(tips);
        }
        String homeTeamName = game.getHomeTeam().getProfile().getAbbr();
        String awayTeamName = game.getAwayTeam().getProfile().getAbbr();

        if (LanguageUtils.isChineseLanguage()){
            homeTeamName = game.getHomeTeam().getProfile().getName();
            awayTeamName = game.getAwayTeam().getProfile().getName();
        }
        tvHomeFieldName.setText(homeTeamName);
        tvAwayFieldName.setText(awayTeamName);

        viewScores.bindData(game.getBoxscore().getHomeScore(), game.getBoxscore().getAwayScore());
//        ViewScores.bindData(score.getHomeFieldScores(), score.getAwayFieldScores());
//        String time = DataUtils.getCurrentDateFormat(score.getStartTime(), "HH:mm");
//        tvStartTime.setText(time);
    }

    @Override
    public void schedule(Schedule schedule) {
        ViewsUtils.setViewsGone(progressBar);
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
                                Game game = games.get(position);
                                bindData(game);
                                //如果是进行中，延迟获取数据
                                if (Integer.parseInt(game.getBoxscore().getStatus()) == 2){
                                    MainRequest.getInstance().onRequest(6, this);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onError() {
        ViewsUtils.setViewsGone(progressBar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainRequest.getInstance().stopDispose();
    }
}
