package inf.msc.yawapp.cityweather;

import javax.inject.Inject;

public class CityWeatherPresenterImpl implements CityWeatherPresenter {
    @Inject
    CityWeatherView view;

    @Override
    public void searchCity(String city) {
        view.setSearchError("An error occurred!");
    }
}
