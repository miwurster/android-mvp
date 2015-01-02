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

    @Provides
    @Singleton
    public MapPresenter provideMapPresenter(MapPresenterImpl presenter) {
        return presenter;
    }
}
