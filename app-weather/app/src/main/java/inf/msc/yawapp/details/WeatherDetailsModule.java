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

package inf.msc.yawapp.details;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
