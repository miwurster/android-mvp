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
