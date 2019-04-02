package com.example.livescorebottombar.navigation;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

public class NavigationController {
    public static final String NAVIGATION_ITEM_BACK_STACK_TAG = "NavigationController";
    private @IdRes
    int containerViewId;
    private FragmentManager fragmentManager;
    private int currentId = -1;
    private SparseArray<NavigationRootItem> navigationRootItems = new SparseArray<>();

    public NavigationController(FragmentActivity activity, @IdRes int containerViewId) {
        this.containerViewId = containerViewId;
        fragmentManager = activity.getSupportFragmentManager();
    }

    public void addRootItem(NavigationRootItem item, int id) {
        navigationRootItems.put(id, item);
    }

    public void activateItem(int id) {
        clearStack();

        if (currentId != id)
            activateItemInternal(id);
    }

    public void forceActivateItem(int id) {
        clearStack();
        activateItemInternal(id);
    }

    public int getCurrentItemId() {
        return currentId;
    }

    private void activateItemInternal(int id) {
        NavigationRootItem navigationRootItem = navigationRootItems.get(id);

        if (navigationRootItem != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();

            navigationRootItem.show(ft, containerViewId);

            ft.commit();

            currentId = id;
        }
    }

    private void clearStack() {
        fragmentManager.popBackStack(NAVIGATION_ITEM_BACK_STACK_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.executePendingTransactions();
    }
}
