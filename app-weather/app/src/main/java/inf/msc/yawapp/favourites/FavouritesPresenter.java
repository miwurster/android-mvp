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
