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
