package inf.msc.yawapp.details;

import android.os.Bundle;

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericCache;
import inf.msc.yawapp.model.FavouritesStore;
import inf.msc.yawapp.model.WeatherData;
import inf.msc.yawapp.model.WeatherDataListener;
import inf.msc.yawapp.model.WeatherSearchInteractor;

public class WeatherDetailsPresenterImpl implements WeatherDetailsPresenter, WeatherDataListener {

    public static final String BUNDLE_VIEW_STATE = "viewState";

    @Inject
    WeatherDetailsView view;

    @Inject
    WeatherSearchInteractor weatherSearchInteractor;

    @Inject
    GenericCache<WeatherData> weatherDataCache;

    @Inject
    FavouritesStore favouritesStore;

    private enum ViewState {
        UNINITIALIZED, LOADING, WEATHER, ERROR
    }

    private ViewState viewState = ViewState.UNINITIALIZED;

    private void presentData(final WeatherData data) {
        if (data.getLocation().getCity().isEmpty()) {
            view.showCityName(data.getLocation().getCountry());
        } else {
            view.showCityName(data.getLocation().getCity());
        }
        view.showWeatherCondition(data.getCondition(), data.isDay());
        view.showFavouriteIcon(favouritesStore.getById(data.getLocation().getId()) != null);
    }

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

    @Override
    public void restore(Bundle savedInstancestate) {
        if (savedInstancestate != null) {
            viewState = (ViewState) savedInstancestate.getSerializable(BUNDLE_VIEW_STATE);
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
        weatherSearchInteractor.search(query, this);
    }

    @Override
    public void refresh() {
        if (weatherDataCache.getData() != null) {
            viewState = ViewState.LOADING;
            view.showLoadingAnimation();
            weatherSearchInteractor.search(weatherDataCache.getData().getLocation(), this);
        }
    }

    @Override
    public void presentExistingData() {
        if (weatherDataCache.getData() != null) {
            final WeatherData data = weatherDataCache.getData();
            presentData(data);
        }
    }

    @Override
    public void toggleFavourite() {
        if (weatherDataCache.getData() != null) {
            final WeatherData data = weatherDataCache.getData();
            if (favouritesStore.getById(data.getLocation().getId()) != null) {
                favouritesStore.delete(data.getLocation());
                view.showFavouriteIcon(false);
            } else {
                favouritesStore.add(data.getLocation());
                view.showFavouriteIcon(true);
            }
        }
    }
}
