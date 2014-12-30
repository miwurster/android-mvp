package inf.msc.yawapp.details;

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericCache;
import inf.msc.yawapp.model.WeatherData;
import inf.msc.yawapp.model.WeatherDataListener;
import inf.msc.yawapp.model.WeatherSearchInteractor;

public class WeatherDetailsPresenterImpl implements WeatherDetailsPresenter {
    @Inject
    WeatherDetailsView view;

    @Inject
    WeatherSearchInteractor weatherSearchInteractor;

    @Inject
    GenericCache<WeatherData> weatherDataCache;

    @Override
    public void search(String query) {
        weatherSearchInteractor.search(query, new WeatherDataListener() {
            @Override
            public void onWeatherDataAvailable(WeatherData data) {
                weatherDataCache.notifyAll(data);
                view.showCityName(data.getCityName());
                view.showWeatherCondition(data.getCondition(), data.isDay());
            }

            @Override
            public void onWeatherDataError() {
                view.showSearchError("FooBar");
            }
        });
    }

    @Override
    public void presentExistingData() {
        if (weatherDataCache.getData() != null) {
            final WeatherData data = weatherDataCache.getData();
            view.showCityName(data.getCityName());
            view.showWeatherCondition(data.getCondition(), data.isDay());
        }
    }
}
