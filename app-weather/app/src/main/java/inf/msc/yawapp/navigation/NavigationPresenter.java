package inf.msc.yawapp.navigation;

import android.content.Context;

public interface NavigationPresenter {
    public boolean navigate(NavDrawerItem item, Context context);
}
