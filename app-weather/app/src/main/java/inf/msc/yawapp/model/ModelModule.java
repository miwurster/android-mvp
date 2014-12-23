package inf.msc.yawapp.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = WeatherSearchInteractor.class
)
public class ModelModule {

    @Provides
    @Singleton
    public WeatherSearchInteractor provideWeatherSearchInteractor() {
        return new OpenWeatherMapInteractor();
    }
}
