package inf.msc.yawapp.details;

public interface WeatherDetailsView {
    public void showSearchError(final String message);

    public void showCityName(final String city);
    public void showCurrentTemperature(float temperature);
}
