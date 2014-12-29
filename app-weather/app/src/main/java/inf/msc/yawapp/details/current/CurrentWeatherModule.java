package inf.msc.yawapp.details.current;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                CurrentWeatherFragment.class
        },
        complete = false
)
public class CurrentWeatherModule {
    private final CurrentWeatherView view;

    public CurrentWeatherModule(CurrentWeatherView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public CurrentWeatherPresenter provideCurrentWeatherPresenter(CurrentWeatherPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    public CurrentWeatherView provideCurrentWeatherView() {
        return view;
    }
}
