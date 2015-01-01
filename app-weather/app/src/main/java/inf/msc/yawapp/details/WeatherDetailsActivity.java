package inf.msc.yawapp.details;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.common.Intents;
import inf.msc.yawapp.model.WeatherData;
import inf.msc.yawapp.search.SearchActivity;

public class WeatherDetailsActivity extends BaseModuleActivity implements WeatherDetailsView {

    public static final String CONDITION_FORMAT = "%s, %s";

    @Inject
    WeatherDetailsPresenter presenter;

    private Map<WeatherData.Condition, String> conditionTexts;

    private TextView widgetCityName;
    private TextView widgetCityCondition;
    private MenuItem widgetFavouriteIcon;
    private FrameLayout contentArea;
    private View contentPageLoading;
    private View contentPageCurrent;
    private View contentPageError;

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

        widgetCityName = (TextView) findViewById(R.id.city_name);
        widgetCityCondition = (TextView) findViewById(R.id.city_condition);
        contentArea = (FrameLayout) findViewById(R.id.content_area);
        contentPageLoading = getLayoutInflater().inflate(R.layout.page_loading, contentArea, false);
        contentPageCurrent = getLayoutInflater().inflate(R.layout.page_current, contentArea, false);
        contentPageError = getLayoutInflater().inflate(R.layout.page_error, contentArea, false);

        final Toolbar toolbar = getActionBarToolbar();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("");
            }
        });

        presenter.restore(savedInstanceState);
        if (!presenter.isInitialized()) {
            onNewIntent(getIntent());
        } else {
            presenter.presentExistingData();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.backup(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.getAction().equals(Intents.SEARCH_WEATHER)) {
            presenter.search(intent.getExtras().getString("query"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.details, menu);
        widgetFavouriteIcon = menu.findItem(R.id.action_favourite);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            case R.id.action_refresh:
                presenter.refresh();
                return true;
            case R.id.action_favourite:
                presenter.toggleFavourite();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingAnimation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                widgetCityName.setText("");
                widgetCityCondition.setText("");
                contentArea.removeAllViews();
                contentArea.addView(contentPageLoading);
            }
        });
    }

    @Override
    public void showWeatherContent() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contentArea.removeAllViews();
                contentArea.addView(contentPageCurrent);
            }
        });
    }

    @Override
    public void showSearchError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                widgetCityName.setText("Error");
                widgetCityCondition.setText("");
                contentArea.removeAllViews();
                contentArea.addView(contentPageError);
            }
        });
    }

    @Override
    public void showCityName(final String city) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                widgetCityName.setText(city);
            }
        });
    }

    @Override
    public void showWeatherCondition(final WeatherData.Condition condition, final boolean isDay) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String conditionText = conditionTexts.get(condition);
                String daylightText = (isDay) ? getResources().getString(R.string.day) : getResources().getString(R.string.night);
                widgetCityCondition.setText(String.format(CONDITION_FORMAT, conditionText, daylightText));
            }
        });
    }

    @Override
    public void showFavouriteIcon(boolean isFavourite) {
        if (isFavourite) {
            widgetFavouriteIcon.setIcon(R.drawable.ic_favorite_white_24dp);
        } else {
            widgetFavouriteIcon.setIcon(R.drawable.ic_favorite_outline_white_24dp);
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new WeatherDetailsModule(this));
    }
}
