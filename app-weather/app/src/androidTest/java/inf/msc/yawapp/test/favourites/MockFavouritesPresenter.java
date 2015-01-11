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

package inf.msc.yawapp.test.favourites;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import inf.msc.yawapp.favourites.FavouritesPresenter;
import inf.msc.yawapp.model.Location;

public class MockFavouritesPresenter implements FavouritesPresenter {

    public int initCount = 0;
    public int deinitCount = 0;
    public int updateCount = 0;
    public List<Location> submitSearchList = new ArrayList<>();
    public List<Context> navigateToSearchList = new ArrayList<>();

    @Override
    public void init() {
        initCount++;
    }

    @Override
    public void deinit() {
        deinitCount++;
    }

    @Override
    public void update() {
        updateCount++;
    }

    @Override
    public void submitSearch(Location location) {
        submitSearchList.add(location);
    }

    @Override
    public void navigateToSearch(Context context) {
        navigateToSearchList.add(context);
    }
}
