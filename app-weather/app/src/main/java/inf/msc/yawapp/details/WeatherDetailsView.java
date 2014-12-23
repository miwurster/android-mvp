package inf.msc.yawapp.details;

public interface WeatherDetailsView {
    public void showSearchError(final String message);

    public void showCurrentTemperature(float temperature);
}
