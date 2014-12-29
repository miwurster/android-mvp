package inf.msc.yawapp.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.MainModule;
import inf.msc.yawapp.common.GenericObservable;

@Module(
        injects = {
                WeatherSearchInteractor.class
        },
        library = true
)
public class ModelModule {

    public interface MyFactory {
        public Object create();
    }

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
