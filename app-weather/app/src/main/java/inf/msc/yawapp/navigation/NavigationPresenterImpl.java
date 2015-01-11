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

package inf.msc.yawapp.navigation;

import android.content.Context;
import android.content.Intent;

import inf.msc.yawapp.favourites.FavouritesActivity;
import inf.msc.yawapp.map.MapActivity;
import inf.msc.yawapp.search.SearchActivity;

public class NavigationPresenterImpl implements NavigationPresenter {

    @Override
    public boolean navigate(NavDrawerItem item, Context context) {
        Intent intent;
        switch (item) {
            case NAV_FAVOURITES:
                intent = new Intent(context, FavouritesActivity.class);
                context.startActivity(intent);
                break;
            case NAV_MAP:
                intent = new Intent(context, MapActivity.class);
                context.startActivity(intent);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void navigateSearch(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
