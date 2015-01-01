package inf.msc.yawapp.owm;

import net.aksingh.java.api.owm.OpenWeatherMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        library = true
)
public class OpenWeatherMapModule {

    @Provides
    @Singleton
    public OpenWeatherMapAdapter provideOpenWeatherMapAdapter() {
        return new OpenWeatherMapAdapterImpl(OpenWeatherMap.OWM_URL.PARAMETER_UNITS_VALUE_METRIC, "");
    }
}
