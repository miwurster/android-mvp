package inf.msc.yawapp.details;

import inf.msc.yawapp.model.WeatherData;

public interface WeatherDetailsView {
    public void showLoadingAnimation();

    public void showWeatherContent();

    public void showSearchError();

    public void showCityName(final String city);

    public void showWeatherCondition(final WeatherData.Condition condition, boolean isDay);

    public void showFavouriteIcon(boolean isFavourite);
}
