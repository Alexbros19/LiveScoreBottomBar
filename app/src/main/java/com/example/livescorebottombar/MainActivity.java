package com.example.livescorebottombar;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.livescorebottombar.menu.BottomMenuHelper;
import com.example.livescorebottombar.navigation.NavigationItems;

public class MainActivity extends AppCompatActivity implements BottomMenuHelper.OnNavigationItemSelectedListener {
    public static int currentNavigationViewId = NavigationItems.SCORES;
    private BottomMenuHelper bottomMenuHelper = null;
    private BottomNavigationView bottomMenuView;
    private Button showBadgeButton;
    private Button showHockeyBottomBarButton;
    private boolean isBadgeVisible = false;

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
                    BottomMenuHelper.removeBadge(bottomMenuView, R.id.nav_favorites);
                    BottomMenuHelper.refreshBadgeView(isBadgeVisible, showBadgeButton);
                    isBadgeVisible = false;
                } else {
                    BottomMenuHelper.showBadge(getApplicationContext(), bottomMenuView, R.id.nav_favorites, "1");
                    BottomMenuHelper.refreshBadgeView(isBadgeVisible, showBadgeButton);
                    isBadgeVisible = true;
                }
            }
        });

        showHockeyBottomBarButton = findViewById(R.id.showHockeyBottomBarButton);
        showHockeyBottomBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomMenuHelper.setCheckedItem(currentNavigationViewId);
    }

    @Override
    public void onNavigationItemSelected(int navigationId) {
    }
}
