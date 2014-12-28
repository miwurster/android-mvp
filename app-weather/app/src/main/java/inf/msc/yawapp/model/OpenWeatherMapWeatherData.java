package inf.msc.yawapp.model;

import net.aksingh.java.api.owm.CurrentWeatherData;

public class OpenWeatherMapWeatherData implements WeatherData {

    private final CurrentWeatherData data;

    public OpenWeatherMapWeatherData(final CurrentWeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.data = data;
    }

    @Override
    public String getCityName() {
        return data.getCityName();
    }

    @Override
    public float getCurrentTemperature() {
        return data.getMainObject().getTemperature();
    }
}
