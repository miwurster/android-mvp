package inf.msc.yawapp.details;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.model.WeatherData;

public class WeatherDetailsActivity extends BaseModuleActivity implements WeatherDetailsView {
    @Inject
    WeatherDetailsPresenter presenter;

    private Toolbar toolbar;

    private Map<WeatherData.Condition, String> conditionTexts;

    private void initConditionTexts() {
        Resources res = getResources();
        conditionTexts = new HashMap<>();
        conditionTexts.put(WeatherData.Condition.CLEAR, res.getString(R.string.weather_condition_clear));
        conditionTexts.put(WeatherData.Condition.FEW_CLOUDS, res.getString(R.string.weather_condition_few_clouds));
        conditionTexts.put(WeatherData.Condition.SCATTERED_CLOUDS, res.getString(R.string.weather_condition_scattered_clouds));
        conditionTexts.put(WeatherData.Condition.BROKEN_CLOUDS, res.getString(R.string.weather_condition_broken_clouds));
        conditionTexts.put(WeatherData.Condition.SHOWER_RAIN, res.getString(R.string.weather_condition_shower_rain));
        conditionTexts.put(WeatherData.Condition.RAIN, res.getString(R.string.weather_condition_rain));
        conditionTexts.put(WeatherData.Condition.THUNDERSTORM, res.getString(R.string.weather_condition_thunderstorm));
        conditionTexts.put(WeatherData.Condition.SNOW, res.getString(R.string.weather_condition_snow));
        conditionTexts.put(WeatherData.Condition.MIST, res.getString(R.string.weather_condition_mist));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initConditionTexts();

        final Toolbar toolbar = getActionBarToolbar();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //startActivity(new Intent(this, SearchActivity.class));
                presenter.search("Herrenberg");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSearchError(String message) {
    }

    @Override
    public void showCityName(final String city) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView cityName = (TextView) findViewById(R.id.city_name);
                cityName.setText(city);
            }
        });
    }

    @Override
    public void showWeatherCondition(final WeatherData.Condition condition, final boolean isDay) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView cityConditionWidget = (TextView) findViewById(R.id.city_condition);
                String conditionText = conditionTexts.get(condition);
                String daylightText = (isDay) ? getResources().getString(R.string.day) : getResources().getString(R.string.night);
                cityConditionWidget.setText(String.format("%s, %s", conditionText, daylightText));
            }
        });
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new WeatherDetailsModule(this));
    }
}
