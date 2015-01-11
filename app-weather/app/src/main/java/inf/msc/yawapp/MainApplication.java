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

package inf.msc.yawapp;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import inf.msc.yawapp.model.ModelModule;
import inf.msc.yawapp.navigation.NavigationModule;
import inf.msc.yawapp.owm.OpenWeatherMapModule;

public class MainApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private List<Object> getModules() {
        return Arrays.asList(
                new MainModule(this),
                new ModelModule(),
                new NavigationModule(),
                new OpenWeatherMapModule());
    }

    public ObjectGraph createSubGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
