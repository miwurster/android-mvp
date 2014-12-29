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

    @Inject
    CurrentWeatherPresenter presenter;

    private TextView weatherIcon;
    private TextView temperatureBlock;

    private final Map<WeatherData.Condition, String> conditionStringMap;

    {
        conditionStringMap = new HashMap<>();
        conditionStringMap.put(WeatherData.Condition.NOT_AVAILABLE, "\uf053");
        conditionStringMap.put(WeatherData.Condition.CLEAR, "\uf00d");
        conditionStringMap.put(WeatherData.Condition.FEW_CLOUDS, "\uf00c");
        conditionStringMap.put(WeatherData.Condition.SCATTERED_CLOUDS, "\uf002");
        conditionStringMap.put(WeatherData.Condition.BROKEN_CLOUDS, "\uf013");
        conditionStringMap.put(WeatherData.Condition.SHOWER_RAIN, "\uf0b5");
        conditionStringMap.put(WeatherData.Condition.RAIN, "\uf037");
        conditionStringMap.put(WeatherData.Condition.THUNDERSTORM, "\uf01d");
        conditionStringMap.put(WeatherData.Condition.SNOW, "\uf01b");
        conditionStringMap.put(WeatherData.Condition.MIST, "\uf014");
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
                temperatureBlock.setText(Float.toString(weatherData.getCurrentTemperature()));
                weatherIcon.setText(conditionStringMap.get(weatherData.getCondition()));
            }
        });
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new CurrentWeatherModule(this));
    }
}
