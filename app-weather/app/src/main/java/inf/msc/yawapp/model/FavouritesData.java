package inf.msc.yawapp.model;

import java.util.List;

/**
 * Created by Sebastian on 28.12.2014.
 */
public interface FavouritesData {


    public void add(Location loc);

    public void update(Location loc);

    public void delete(long id);

    public Location get(long id);

    public List<Location> getAll();

    public List<Location> search(String query);




}
