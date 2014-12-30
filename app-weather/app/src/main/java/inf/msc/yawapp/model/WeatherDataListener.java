package inf.msc.yawapp.model;

public interface WeatherDataListener {
    public void onWeatherDataAvailable(final WeatherData data);

    public void onWeatherDataError();
}
