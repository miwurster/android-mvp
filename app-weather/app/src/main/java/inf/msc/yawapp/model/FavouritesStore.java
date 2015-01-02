package inf.msc.yawapp.model;

import java.util.List;

public interface FavouritesStore {

    public void add(Location location);

    public void update(Location location);

    public void delete(Location location);

    public Location getById(long id);

    public List<Location> getAll();

    public boolean hasLocation(Location location);

}
