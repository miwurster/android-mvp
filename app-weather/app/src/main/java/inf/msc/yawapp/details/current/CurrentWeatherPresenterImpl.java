package inf.msc.yawapp.details.current;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.common.GenericObservable;
import inf.msc.yawapp.common.GenericObserver;
import inf.msc.yawapp.model.WeatherData;

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter, GenericObserver<WeatherData> {
    @Inject
    CurrentWeatherView view;

    @Inject
    MainApplication application;

    @Inject
    GenericObservable<WeatherData> weatherDataObservable;

    @Override
    public void register() {
        weatherDataObservable.registerObserver(this);
    }

    @Override
    public void unregister() {
        weatherDataObservable.unregisterObserver(this);
    }

    @Override
    public void update(GenericObservable<WeatherData> observable, WeatherData argument) {
        view.showWeatherData(argument);
    }
}
