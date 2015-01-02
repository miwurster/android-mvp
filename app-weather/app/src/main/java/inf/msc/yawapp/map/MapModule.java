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
