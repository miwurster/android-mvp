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

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericCache;
import inf.msc.yawapp.common.GenericObservable;
import inf.msc.yawapp.common.GenericObserver;
import inf.msc.yawapp.model.WeatherData;

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter, GenericObserver<WeatherData> {
    @Inject
    CurrentWeatherView view;

    @Inject
    GenericCache<WeatherData> weatherDataCache;

    @Override
    public void register() {
        weatherDataCache.registerObserver(this);
    }

    @Override
    public void unregister() {
        weatherDataCache.unregisterObserver(this);
    }

    @Override
    public void presentExistingData() {
        if (weatherDataCache.getData() != null) {
            view.showWeatherData(weatherDataCache.getData());
        }
    }

    @Override
    public void update(GenericObservable<WeatherData> observable, WeatherData argument) {
        if (argument != null) {
            view.showWeatherData(argument);
        }
    }
}
