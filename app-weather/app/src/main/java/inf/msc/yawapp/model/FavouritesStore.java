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
