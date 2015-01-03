package inf.msc.yawapp.test;

import android.content.Context;

import inf.msc.yawapp.navigation.NavDrawerItem;
import inf.msc.yawapp.navigation.NavigationPresenter;

public class MockNavigationPresenter implements NavigationPresenter {
    @Override
    public boolean navigate(NavDrawerItem item, Context context) {
        return false;
    }

    @Override
    public void navigateSearch(Context context) {

    }
}
