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

package inf.msc.yawapp.map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MapActivity.class
        },
        complete = false
)
public class MapModule {

    private final MapView view;

    public MapModule(MapView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public MapView provideMapView() {
        return view;
    }

    @Provides
    @Singleton
    public MapPresenter provideMapPresenter(MapPresenterImpl presenter) {
        return presenter;
    }
}
