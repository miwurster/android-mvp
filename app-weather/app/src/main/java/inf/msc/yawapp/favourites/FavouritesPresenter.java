package inf.msc.yawapp.favourites;

import android.content.Context;

import inf.msc.yawapp.model.Location;

public interface FavouritesPresenter {
    public void init();

    public void deinit();

    public void update();

    public void submitSearch(final Location location);

    public void navigateToSearch(final Context context);
}
