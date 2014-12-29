package inf.msc.yawapp.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.common.GenericObservable;

@Module(
        injects = {
                WeatherSearchInteractor.class
        },
        library = true
)
public class ModelModule {

    @Provides
    @Singleton
    public WeatherSearchInteractor provideWeatherSearchInteractor() {
        return new OpenWeatherMapInteractor();
    }

    @Provides
    @Singleton
    public GenericObservable<WeatherData> provideWeatherDataObservable() {
        return new GenericObservable<WeatherData>();
    }
}
