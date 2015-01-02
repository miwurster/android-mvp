package inf.msc.yawapp.favourites;

import inf.msc.yawapp.model.Location;

public interface FavouritesPresenter {
    public void init();

    public void deinit();

    public void update();

    public void submitSearch(final Location location);
}
