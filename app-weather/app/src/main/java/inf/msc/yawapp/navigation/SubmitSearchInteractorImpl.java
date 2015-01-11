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

package inf.msc.yawapp.navigation;

import android.content.Intent;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.common.Intents;
import inf.msc.yawapp.model.Location;

public class SubmitSearchInteractorImpl implements SubmitSearchInteractor {

    @Inject
    MainApplication application;

    @Override
    public void submitSearch(String query) {
        Intent intent = new Intent(Intents.SEARCH_WEATHER);
        intent.putExtra("query", query);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        application.startActivity(intent);
    }

    @Override
    public void submitSearch(Location location) {
        Intent intent = new Intent(Intents.SEARCH_WEATHER);
        intent.putExtra("location", location);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        application.startActivity(intent);
    }
}
