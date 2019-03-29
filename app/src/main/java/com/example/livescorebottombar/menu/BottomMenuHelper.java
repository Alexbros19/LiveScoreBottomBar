package com.example.livescorebottombar.menu;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.livescorebottombar.R;
import com.example.livescorebottombar.navigation.NavigationItems;

public class BottomMenuHelper implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomBar;
    private OnNavigationItemSelectedListener onNavigationItemSelectedListener = null;

    public void setup(BottomNavigationView bottomBar) {
        this.bottomBar = bottomBar;
        bottomBar.setVisibility(View.VISIBLE);
        bottomBar.setOnNavigationItemSelectedListener(this);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        onNavigationItemSelectedListener = listener;
    }

    public void setCheckedItem(int navigationId) {
        Menu menu = bottomBar.getMenu();

        int tabId = 0;
        if (navigationId == NavigationItems.SCORES)
            tabId = R.id.nav_scores;
        else if (navigationId == NavigationItems.LIVE)
            tabId = R.id.nav_live;
        else if (navigationId == NavigationItems.FAVORITES)
            tabId = R.id.nav_favorites;
        else if (navigationId == NavigationItems.MENU)
            tabId = R.id.nav_menu;
        else if (navigationId == NavigationItems.NEWS)
            tabId = R.id.nav_news;
//        else if (navigationId == NavigationItems.REFRESH)
//            tabId = R.id.nav_refresh;

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);

            if (menuItem.getItemId() == tabId)
                menuItem.setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int tabId = menuItem.getItemId();

        int navigationId = 0;
        if (tabId == R.id.nav_scores)
            navigationId = NavigationItems.SCORES;
        else if (tabId == R.id.nav_live)
            navigationId = NavigationItems.LIVE;
        else if (tabId == R.id.nav_favorites)
            navigationId = NavigationItems.FAVORITES;
        else if (tabId == R.id.nav_menu)
            navigationId = NavigationItems.MENU;
        else if (tabId == R.id.nav_news)
            navigationId = NavigationItems.NEWS;
//        else if (tabId == R.id.nav_refresh)
//            navigationId = NavigationItems.REFRESH;

        if (onNavigationItemSelectedListener != null && navigationId != 0)
            onNavigationItemSelectedListener.onNavigationItemSelected(navigationId);

        return true;
    }

    public static void showBadge(Context context, BottomNavigationView
            bottomNavigationView, @IdRes int itemId, String value) {
//        removeBadge(bottomNavigationView, itemId);
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        View badge = LayoutInflater.from(context).inflate(R.layout.bottom_bar_badge_layout, bottomNavigationView, false);

        TextView text = badge.findViewById(R.id.notificationsBadgeTextView);
        text.setText(value);
        itemView.addView(badge);
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(int navigationId);
    }
}
