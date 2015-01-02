package inf.msc.yawapp.owm;

import net.aksingh.java.api.owm.CurrentWeatherData;

public interface OpenWeatherMapAdapter {
    public CurrentWeatherData currentWeatherByCityName(final String cityName) throws OpenWeatherMapException;

    public CurrentWeatherData currentWeatherByCityCode(long cityCode) throws OpenWeatherMapException;

    public CurrentWeatherData currentWeatherByCoord(float latitude, float longitude) throws OpenWeatherMapException;
}
