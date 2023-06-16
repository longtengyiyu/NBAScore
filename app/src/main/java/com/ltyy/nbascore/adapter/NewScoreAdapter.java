package com.ltyy.nbascore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ltyy.nbascore.NewScoreFragment;
import com.ltyy.nbascore.ScoreFragment;
import com.ltyy.nbascore.bean.Game;
import com.ltyy.nbascore.bean.Score;

import java.util.List;

public class NewScoreAdapter extends FragmentStateAdapter {

    List<Score> scores;

    public NewScoreAdapter(@NonNull FragmentActivity fragmentActivity, List<Score> scores) {
        super(fragmentActivity);
        this.scores = scores;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return NewScoreFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return scores == null ? 0 : scores.size();
    }

}
