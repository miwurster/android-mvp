package inf.msc.yawapp.details.current;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleFragment;
import inf.msc.yawapp.model.WeatherData;

public class CurrentWeatherFragment extends BaseModuleFragment implements CurrentWeatherView {

    public static final String TEMPERATURE_CELCIUS = "%.1f °C";
    public static final String HUMIDITY_PERCENT = "%.0f %%";
    public static final String ATMOSPHERIC_PRESSURE_HPA = "%.0f hPa";
    public static final String WIND_SPEED_MPS = "%.1f mps";
    public static final String WIND_DIRECTION_DEGREES = "%.1f °";
    public static final String FONT_WEATHERICONS = "fonts/weathericons-regular-webfont.ttf";

    @Inject
    CurrentWeatherPresenter presenter;

    private TextView weatherIcon;
    private TextView temperatureBlock;
    private TextView humidity;
    private TextView atmosphericPressure;
    private TextView windIcon;
    private TextView windSpeed;
    private TextView windDirection;

    private static final Map<WeatherData.Condition, String> CONDITIONS_DAY;
    private static final Map<WeatherData.Condition, String> CONDITIONS_NIGHT;
    private static final Map<Integer, String> WIND_DIRECTIONS;

    static {
        CONDITIONS_DAY = new HashMap<>();
        CONDITIONS_DAY.put(WeatherData.Condition.NOT_AVAILABLE, "\uf053");
        CONDITIONS_DAY.put(WeatherData.Condition.CLEAR, "\uf00d");
        CONDITIONS_DAY.put(WeatherData.Condition.FEW_CLOUDS, "\uf00c");
        CONDITIONS_DAY.put(WeatherData.Condition.SCATTERED_CLOUDS, "\uf002");
        CONDITIONS_DAY.put(WeatherData.Condition.BROKEN_CLOUDS, "\uf013");
        CONDITIONS_DAY.put(WeatherData.Condition.SHOWER_RAIN, "\uf0b5");
        CONDITIONS_DAY.put(WeatherData.Condition.RAIN, "\uf037");
        CONDITIONS_DAY.put(WeatherData.Condition.THUNDERSTORM, "\uf01d");
        CONDITIONS_DAY.put(WeatherData.Condition.SNOW, "\uf01b");
        CONDITIONS_DAY.put(WeatherData.Condition.MIST, "\uf014");
    }

    static {
        CONDITIONS_NIGHT = new HashMap<>();
        CONDITIONS_NIGHT.put(WeatherData.Condition.NOT_AVAILABLE, "\uf053");
        CONDITIONS_NIGHT.put(WeatherData.Condition.CLEAR, "\uf02e");
        CONDITIONS_NIGHT.put(WeatherData.Condition.FEW_CLOUDS, "\uf086");
        CONDITIONS_NIGHT.put(WeatherData.Condition.SCATTERED_CLOUDS, "\uf041");
        CONDITIONS_NIGHT.put(WeatherData.Condition.BROKEN_CLOUDS, "\uf013");
        CONDITIONS_NIGHT.put(WeatherData.Condition.SHOWER_RAIN, "\uf0b5");
        CONDITIONS_NIGHT.put(WeatherData.Condition.RAIN, "\uf0b4");
        CONDITIONS_NIGHT.put(WeatherData.Condition.THUNDERSTORM, "\uf01d");
        CONDITIONS_NIGHT.put(WeatherData.Condition.SNOW, "\uf01b");
        CONDITIONS_NIGHT.put(WeatherData.Condition.MIST, "\uf014");
    }

    static {
        WIND_DIRECTIONS = new HashMap<>();
        WIND_DIRECTIONS.put(0, "\uf05c");
        WIND_DIRECTIONS.put(45, "\uf05a");
        WIND_DIRECTIONS.put(90, "\uf059");
        WIND_DIRECTIONS.put(135, "\uf05d");
        WIND_DIRECTIONS.put(180, "\uf060");
        WIND_DIRECTIONS.put(225, "\uf05e");
        WIND_DIRECTIONS.put(270, "\uf061");
        WIND_DIRECTIONS.put(315, "\uf05b");
    }

    private static String getWindDirectionIcon(float degrees) {
        int d = Math.round(degrees) % 360;
        int delta = d % 45;
        d -= delta;
        if (delta > 45 / 2) {
            d += 45;
            d %= 360;
        }
        return WIND_DIRECTIONS.get(d);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherIcon = (TextView) view.findViewById(R.id.weather_icon);
        temperatureBlock = (TextView) view.findViewById(R.id.temperature_block);
        humidity = (TextView) view.findViewById(R.id.humidity);
        atmosphericPressure = (TextView) view.findViewById(R.id.atmospheric_pressure);
        windIcon = (TextView) view.findViewById(R.id.wind_icon);
        windSpeed = (TextView) view.findViewById(R.id.wind_speed);
        windDirection = (TextView) view.findViewById(R.id.wind_direction);

        Typeface weatherIconFont = Typeface.createFromAsset(getActivity().getApplication().getAssets(), FONT_WEATHERICONS);
        weatherIcon.setTypeface(weatherIconFont);
        windIcon.setTypeface(weatherIconFont);

        presenter.register();
        presenter.presentExistingData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        presenter.unregister();
    }

    @Override
    public void showWeatherData(final WeatherData weatherData) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Temperature Block
                temperatureBlock.setText(String.format(TEMPERATURE_CELCIUS, weatherData.getCurrentTemperature()));
                humidity.setText(String.format(HUMIDITY_PERCENT, weatherData.getHumidity()));
                atmosphericPressure.setText(String.format(ATMOSPHERIC_PRESSURE_HPA, weatherData.getAtmosphericPressure()));
                if (!weatherData.isDay()) {
                    weatherIcon.setText(CONDITIONS_NIGHT.get(weatherData.getCondition()));
                } else {
                    weatherIcon.setText(CONDITIONS_DAY.get(weatherData.getCondition()));
                }

                // Wind Block
                windIcon.setText(getWindDirectionIcon(weatherData.getWindDirection()));
                windSpeed.setText(String.format(WIND_SPEED_MPS, weatherData.getWindSpeed()));
                windDirection.setText(String.format(WIND_DIRECTION_DEGREES, weatherData.getWindDirection()));
            }
        });
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new CurrentWeatherModule(this));
    }
}
