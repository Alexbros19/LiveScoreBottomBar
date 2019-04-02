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
import android.widget.Button;
import android.widget.TextView;

import com.example.livescorebottombar.R;
import com.example.livescorebottombar.navigation.NavigationItems;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BottomMenuHelper implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomBar;
    private OnNavigationItemSelectedListener onNavigationItemSelectedListener = null;
    private static View badge;
    private static boolean isFavoriteItemChecked = false;

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

            if (menuItem.getItemId() == tabId) {
                menuItem.setChecked(true);
            }

            isFavoriteItemChecked = menuItem.getItemId() == R.id.nav_favorites;
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
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        badge = LayoutInflater.from(context).inflate(R.layout.bottom_bar_badge_layout, bottomNavigationView, false);

        TextView text = badge.findViewById(R.id.notificationsBadgeTextView);
        if (isFavoriteItemChecked)
            text.setBackgroundResource(R.drawable.bottom_bar_item_badget_unchecked);
        else
            text.setBackgroundResource(R.drawable.bottom_bar_item_badget_checked);
        text.setText(value);
        itemView.addView(badge);
        badge.setVisibility(VISIBLE);
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        itemView.removeViewAt(2);
    }

    public static void refreshBadgeView(boolean isBadgeVisible, Button button) {
        badge.setVisibility(isBadgeVisible ? GONE : VISIBLE);
        button.setText(isBadgeVisible ? "Show badge" : " Hide badge");
    }

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(int navigationId);
    }
}
