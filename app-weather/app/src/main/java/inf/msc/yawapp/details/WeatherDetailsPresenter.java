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

package inf.msc.yawapp.details;

import android.content.Context;
import android.os.Bundle;

import inf.msc.yawapp.model.Location;

public interface WeatherDetailsPresenter {

    public void restore(final Bundle savedInstancestate);

    public void backup(Bundle outState);

    public boolean isInitialized();

    public void search(final String query);

    public void search(final Location location);

    public void navigateToSearch(final Context context);

    public void refresh();

    public void presentExistingData();

    public void toggleFavourite();
}
