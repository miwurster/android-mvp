package inf.msc.yawapp.favourites;

import java.util.List;

import inf.msc.yawapp.model.Location;

/**
 * Created by Sebastian on 28.12.2014.
 */
public interface FavouritesView {

    public void clearView();

    public void addFavourites(List<Location> items);
}
