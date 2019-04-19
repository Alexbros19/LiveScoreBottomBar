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
//        addScoresItems();
    }

//    private void addScoresItems() {
//        Menu menu = bottomBar.getMenu();
//        menu.add(Menu.NONE, NavigationItems.SCORES, Menu.NONE, R.string.string_scores).setIcon(R.drawable.icon_android);
//        menu.add(Menu.NONE, NavigationItems.LIVE, Menu.NONE, R.string.string_live).setIcon(R.drawable.icon_android);
//        menu.add(Menu.NONE, NavigationItems.FAVORITES, Menu.NONE, R.string.string_favorites).setIcon(R.drawable.icon_android);
//        menu.add(Menu.NONE, NavigationItems.MENU, Menu.NONE, R.string.string_menu).setIcon(R.drawable.icon_android);
//        menu.add(Menu.NONE, NavigationItems.NEWS, Menu.NONE, R.string.string_news).setIcon(R.drawable.icon_android);
//    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        onNavigationItemSelectedListener = listener;
    }

    public void setCheckedItem(int navigationId) {
        Menu menu = bottomBar.getMenu();

        int tabId = 0;

        switch (navigationId) {
            case NavigationItems.SCORES: tabId = R.id.nav_scores_soccer; break;
            case NavigationItems.LIVE: tabId = R.id.nav_live_soccer; break;
            case NavigationItems.FAVORITES: tabId = R.id.nav_favorites_soccer; break;
            case NavigationItems.MENU: tabId = R.id.nav_menu_soccer; break;
            case NavigationItems.NEWS: tabId = R.id.nav_news_soccer; break;
        }

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);

            if (menuItem.getItemId() == tabId) {
                menuItem.setChecked(true);
            }

            isFavoriteItemChecked = menuItem.getItemId() == R.id.nav_favorites_soccer;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int tabId = menuItem.getItemId();

        int navigationId = 0;

        switch (tabId) {
            case R.id.nav_scores_soccer: navigationId = NavigationItems.SCORES; break;
            case R.id.nav_live_soccer: navigationId = NavigationItems.LIVE; break;
            case R.id.nav_favorites_soccer: navigationId = NavigationItems.FAVORITES; break;
            case R.id.nav_menu_soccer: navigationId = NavigationItems.MENU; break;
            case R.id.nav_news_soccer: navigationId = NavigationItems.NEWS; break;
        }

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
