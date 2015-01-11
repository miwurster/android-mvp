/*
 * This source code is part of the research paper
 * "Applying Model-View-Presenter Architecture to the Android Framework with App Prototype"
 *
 * Software Architecture and Management,
 * Herman-Hollerith-Zentrum, Reutlingen University
 *
 * Authors:  Dennis G. Geisse,
 *           Iven John,
 *           Daniel Joos,
 *           Sebastian Kotstein,
 *           Michael Wurster
 *
 * Year:     2015
 */

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
