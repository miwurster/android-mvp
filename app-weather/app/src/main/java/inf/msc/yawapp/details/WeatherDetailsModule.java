package inf.msc.yawapp.details;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.model.OpenWeatherMapInteractor;
import inf.msc.yawapp.model.WeatherSearchInteractor;

@Module(
        injects = {
                WeatherDetailsActivity.class
        },
        complete = false
)
public class WeatherDetailsModule {
    private final WeatherDetailsView view;

    public WeatherDetailsModule(WeatherDetailsView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public WeatherDetailsPresenter provideWeatherDetailsPresenter(WeatherDetailsPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    public WeatherDetailsView provideWeatherDetailsView() {
        return view;
    }
}
