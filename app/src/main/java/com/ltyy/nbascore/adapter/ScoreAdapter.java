package com.ltyy.nbascore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ltyy.nbascore.ScoreFragment;
import com.ltyy.nbascore.bean.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends FragmentStateAdapter {

    private List<Score> scores;

    public ScoreAdapter(@NonNull FragmentActivity fragmentActivity, List<Score> scores) {
        super(fragmentActivity);
        this.scores = scores;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ScoreFragment.newInstance(scores.get(position));
    }

    @Override
    public int getItemCount() {
        return scores == null ? 0 : scores.size();
    }

    public void updateData(int position, MyFragment fragment) {
        fragments.set(position, fragment);
        notifyItemChanged(position);
    }
}
