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

    @Inject
    CurrentWeatherPresenter presenter;

    private TextView weatherIcon;
    private TextView temperatureBlock;
    private TextView minTemperature;
    private TextView maxTemperature;

    private static final Map<WeatherData.Condition, String> CONDITIONS_DAY;
    private static final Map<WeatherData.Condition, String> CONDITIONS_NIGHT;

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
        minTemperature = (TextView) view.findViewById(R.id.temperature_min);
        maxTemperature = (TextView) view.findViewById(R.id.temperature_max);

        weatherIcon.setTypeface(Typeface.createFromAsset(getActivity().getApplication().getAssets(), "fonts/weathericons-regular-webfont.ttf"));

        presenter.register();
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
                temperatureBlock.setText(String.format(TEMPERATURE_CELCIUS, weatherData.getCurrentTemperature()));
                minTemperature.setText(String.format(TEMPERATURE_CELCIUS, weatherData.getMinTemperature()));
                maxTemperature.setText(String.format(TEMPERATURE_CELCIUS, weatherData.getMaxTemperature()));
                if (!weatherData.isDay()) {
                    weatherIcon.setText(CONDITIONS_NIGHT.get(weatherData.getCondition()));
                } else {
                    weatherIcon.setText(CONDITIONS_DAY.get(weatherData.getCondition()));
                }
            }
        });
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new CurrentWeatherModule(this));
    }
}
