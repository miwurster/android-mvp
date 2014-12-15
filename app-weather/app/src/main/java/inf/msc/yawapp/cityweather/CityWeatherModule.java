package inf.msc.yawapp.cityweather;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                CityWeatherActivity.class
        }
)
public class CityWeatherModule {
    private final CityWeatherView view;

    public CityWeatherModule(CityWeatherView view) {
        this.view = view;
    }

    @Provides @Singleton
    public CityWeatherPresenter provideCityWeatherPresenter(CityWeatherPresenterImpl presenter) {
        return presenter;
    }

    @Provides @Singleton
    public CityWeatherView provideCityWeatherView() {
        return view;
    }
}
