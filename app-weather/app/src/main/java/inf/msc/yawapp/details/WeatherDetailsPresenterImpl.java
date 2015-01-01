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

    private void presentData(final WeatherData data) {
        if (data.getCityName().isEmpty()) {
            view.showCityName(data.getCountry());
        } else {
            view.showCityName(data.getCityName());
        }
        view.showWeatherCondition(data.getCondition(), data.isDay());
    }

    @Override
    public void search(String query) {
        view.showLoadingAnimation();
        weatherSearchInteractor.search(query, new WeatherDataListener() {
            @Override
            public void onWeatherDataAvailable(WeatherData data) {
                view.showWeatherContent();
                weatherDataCache.notifyAll(data);
                presentData(data);
            }

            @Override
            public void onWeatherDataError() {
                view.showSearchError("FooBar");  // TODO Show error here
            }
        });
    }

    @Override
    public void refresh() {
        if (weatherDataCache.getData() != null) {
            String query = weatherDataCache.getData().getCityName();
            if (query.isEmpty()) {
                query = weatherDataCache.getData().getCountry();
            }
            search(query);
        }
    }

    @Override
    public void presentExistingData() {
        if (weatherDataCache.getData() != null) {
            final WeatherData data = weatherDataCache.getData();
            presentData(data);
        }
    }
}
