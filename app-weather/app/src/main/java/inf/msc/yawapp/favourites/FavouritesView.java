package inf.msc.yawapp.favourites;

import java.util.List;

import inf.msc.yawapp.model.Location;

public interface FavouritesView {
    public void clearView();

    public void addFavourites(final List<Location> items);
}
