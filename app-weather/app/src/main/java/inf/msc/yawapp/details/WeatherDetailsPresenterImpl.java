package inf.msc.yawapp.details;

import android.os.Bundle;

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericCache;
import inf.msc.yawapp.model.WeatherData;
import inf.msc.yawapp.model.WeatherDataListener;
import inf.msc.yawapp.model.WeatherSearchInteractor;

public class WeatherDetailsPresenterImpl implements WeatherDetailsPresenter {

    public static final String BUNDLE_VIEW_STATE = "viewState";

    @Inject
    WeatherDetailsView view;

    @Inject
    WeatherSearchInteractor weatherSearchInteractor;

    @Inject
    GenericCache<WeatherData> weatherDataCache;

    private enum ViewState {
        UNINITIALIZED, LOADING, WEATHER, ERROR
    }

    private ViewState viewState = ViewState.UNINITIALIZED;

    private void presentData(final WeatherData data) {
        if (data.getCityName().isEmpty()) {
            view.showCityName(data.getCountry());
        } else {
            view.showCityName(data.getCityName());
        }
        view.showWeatherCondition(data.getCondition(), data.isDay());
    }

    @Override
    public void restore(Bundle savedInstancestate) {
        if (savedInstancestate != null) {
            viewState = (ViewState) savedInstancestate.getSerializable("viewState");
        }
        switch (viewState) {
            case LOADING:
                view.showLoadingAnimation();
                break;
            case WEATHER:
                view.showWeatherContent();
                break;
            case ERROR:
                view.showSearchError();
                break;
        }
    }

    @Override
    public void backup(Bundle outState) {
        if (outState != null) {
            outState.putSerializable(BUNDLE_VIEW_STATE, viewState);
        }
    }

    @Override
    public boolean isInitialized() {
        return viewState != ViewState.UNINITIALIZED;
    }

    @Override
    public void search(String query) {
        viewState = ViewState.LOADING;
        view.showLoadingAnimation();
        weatherSearchInteractor.search(query, new WeatherDataListener() {
            @Override
            public void onWeatherDataAvailable(WeatherData data) {
                viewState = ViewState.WEATHER;
                view.showWeatherContent();
                weatherDataCache.notifyAll(data);
                presentData(data);
            }

            @Override
            public void onWeatherDataError() {
                viewState = ViewState.ERROR;
                view.showSearchError();
                weatherDataCache.notifyAll(null);
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
