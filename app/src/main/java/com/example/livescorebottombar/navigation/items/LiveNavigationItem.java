package com.example.livescorebottombar.navigation.items;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.livescorebottombar.fragments.SoccerLiveListFragment;
import com.example.livescorebottombar.navigation.NavigationRootItem;

public class LiveNavigationItem implements NavigationRootItem {
    @Override
    public void show(FragmentTransaction ft, int containerViewId) {
        Fragment fragment = new SoccerLiveListFragment();
        ft.replace(containerViewId, fragment);
    }
}
