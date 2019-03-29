package com.example.livescorebottombar;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.livescorebottombar.menu.BottomMenuHelper;
import com.example.livescorebottombar.navigation.NavigationItems;

public class MainActivity extends AppCompatActivity implements BottomMenuHelper.OnNavigationItemSelectedListener {
    public static int currentNavigationViewId = NavigationItems.SCORES;
    private BottomMenuHelper bottomMenuHelper = null;
    private BottomNavigationView bottomBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBarView = findViewById(R.id.bottom_navigation);
        bottomMenuHelper = new BottomMenuHelper();
        bottomMenuHelper.setup(bottomBarView);
        bottomMenuHelper.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomMenuHelper.setCheckedItem(currentNavigationViewId);
    }

    @Override
    public void onNavigationItemSelected(int navigationId) {
        BottomMenuHelper.showBadge(this, bottomBarView, navigationId, "1");
    }
}
