package inf.msc.yawapp.details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.BaseModuleActivity;
import inf.msc.yawapp.R;

public class WeatherDetailsActivity extends BaseModuleActivity implements WeatherDetailsView {
    @Inject
    WeatherDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cityWeatherToolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.drawable.ic_menu_black_18dp);
        }
    }

    public void onSearchButtonClick(View view) {
        EditText searchWidget = (EditText) findViewById(R.id.txtCityName);
        presenter.search(searchWidget.getText().toString());
    }

    @Override
    public void showSearchError(String message) {
    }

    @Override
    public void showCurrentTemperature(float temperature) {
        TextView textWidget = (TextView) findViewById(R.id.textView);
        textWidget.setText(Float.toString(temperature) + " °C");
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new WeatherDetailsModule(this));
    }
}
