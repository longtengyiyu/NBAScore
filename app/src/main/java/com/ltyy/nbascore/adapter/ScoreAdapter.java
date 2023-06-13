package com.ltyy.nbascore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ltyy.nbascore.ScoreFragment;
import com.ltyy.nbascore.bean.Game;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends FragmentStateAdapter {

    List<Game> games;

    public ScoreAdapter(@NonNull FragmentActivity fragmentActivity, List<Game> games) {
        super(fragmentActivity);
        this.games = games;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ScoreFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return games == null ? 0 : games.size();
    }

}
