package com.example.livescorebottombar.navigation;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

public interface NavigationRootItem {
    void show(FragmentTransaction ft, @IdRes int containerViewId);
}
