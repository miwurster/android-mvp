package inf.msc.yawapp.cityweather;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.BaseModuleActivity;
import inf.msc.yawapp.R;

public class CityWeatherActivity extends BaseModuleActivity implements CityWeatherView {
    @Inject
    CityWeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cityWeatherToolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onSearchButtonClick(View view) {
        EditText searchWidget = (EditText) findViewById(R.id.txtCityName);
        presenter.searchCity(searchWidget.getText().toString());
    }

    @Override
    public void setHistory(List<String> history) {
    }

    @Override
    public void setSearchError(String message) {
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new CityWeatherModule(this));
    }
}
