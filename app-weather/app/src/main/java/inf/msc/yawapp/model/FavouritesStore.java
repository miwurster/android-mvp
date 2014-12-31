package inf.msc.yawapp.model;

import java.util.List;

public interface FavouritesStore {

    public void add(Location loc);

    public void update(Location loc);

    public void delete(long id);

    public Location get(long id);

    public List<Location> getAll();

    public List<Location> search(String query);

}
