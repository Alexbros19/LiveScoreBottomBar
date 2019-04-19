package com.example.livescorebottombar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.livescorebottombar.menu.BottomMenuHelper;
import com.example.livescorebottombar.navigation.NavigationController;
import com.example.livescorebottombar.navigation.NavigationItems;
import com.example.livescorebottombar.navigation.items.LiveNavigationItem;
import com.example.livescorebottombar.navigation.items.ScoresNavigationItem;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements BottomMenuHelper.OnNavigationItemSelectedListener {
    public static int currentNavigationViewId = NavigationItems.SCORES;
    private BottomMenuHelper bottomMenuHelper = null;
    private BottomNavigationView bottomMenuView;
    private Button showBadgeButton;
    private Button showHockeyBottomBarButton;
    private boolean isBadgeVisible = false;
    private boolean isSoccerMenuVisible = true;
    private NavigationController navigationController;
//    private int menuToChoose = R.menu.soccer_navigation_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomMenuView = findViewById(R.id.bottom_navigation);
        bottomMenuHelper = new BottomMenuHelper();
        bottomMenuHelper.setup(bottomMenuView);
        bottomMenuHelper.setNavigationItemSelectedListener(this);

        showBadgeButton = findViewById(R.id.showBadgeButton);
        showBadgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBadgeVisible) {
                    BottomMenuHelper.removeBadge(bottomMenuView, R.id.nav_favorites_soccer);
                    BottomMenuHelper.refreshBadgeView(isBadgeVisible, showBadgeButton);
                    isBadgeVisible = false;
                } else {
                    BottomMenuHelper.showBadge(getApplicationContext(), bottomMenuView, R.id.nav_favorites_soccer, "1");
                    BottomMenuHelper.refreshBadgeView(isBadgeVisible, showBadgeButton);
                    isBadgeVisible = true;
                }
            }
        });

        showHockeyBottomBarButton = findViewById(R.id.showHockeyBottomBarButton);
        showHockeyBottomBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSoccerMenuVisible) {
                    bottomMenuView.getMenu().clear();
                    bottomMenuView.inflateMenu(R.menu.hockey_navigation_menu);
                    showHockeyBottomBarButton.setText("Show soccer");
                    isSoccerMenuVisible = false;
                } else {
                    bottomMenuView.getMenu().clear();
                    bottomMenuView.inflateMenu(R.menu.soccer_navigation_menu);
                    showHockeyBottomBarButton.setText("Show hockey");
                    isSoccerMenuVisible = true;
                }
            }
        });

        navigationController = new NavigationController(this, R.id.content_frame);

        navigationController.addRootItem(new ScoresNavigationItem(), NavigationItems.SCORES);
        navigationController.addRootItem(new LiveNavigationItem(), NavigationItems.LIVE);
//        navigationController.addRootItem(new VideosNavigationItem(), NavigationItems.FAVORITES);
//        navigationController.addRootItem(new TvScheduleNavigationItem(), NavigationItems.MENU);
//        navigationController.addRootItem(new LeaguesNavigationItem(), NavigationItems.NEWS);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomMenuHelper.setCheckedItem(currentNavigationViewId);
    }

    @Override
    public void onNavigationItemSelected(int navigationId) {
        displayView(navigationId);
    }

    private void displayView(int navigationId) {
        try {
            navigationController.activateItem(navigationId);
            currentNavigationViewId = navigationId;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

//    public static void disableShiftMode(BottomNavigationView view) {
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
//        try {
//            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//            shiftingMode.setAccessible(true);
//            shiftingMode.setBoolean(menuView, false);
//            shiftingMode.setAccessible(false);
//            for (int i = 0; i < menuView.getChildCount(); i++) {
//                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
//                //noinspection RestrictedApi
//                item.setShiftingMode(false);
//                // set once again checked value, so view will be updated
//                //noinspection RestrictedApi
//                item.setChecked(item.getItemData().isChecked());
//            }
//        } catch (NoSuchFieldException e) {
//            Log.e("BNVHelper", "Unable to get shift mode field", e);
//        } catch (IllegalAccessException e) {
//            Log.e("BNVHelper", "Unable to change value of shift mode", e);
//        }
//    }
}
