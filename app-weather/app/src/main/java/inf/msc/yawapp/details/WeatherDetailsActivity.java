package inf.msc.yawapp.details;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.BaseModuleActivity;
import inf.msc.yawapp.R;

public class WeatherDetailsActivity extends BaseModuleActivity implements WeatherDetailsView {
    @Inject
    WeatherDetailsPresenter presenter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = getActionBarToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView view = (SearchView) searchItem.getActionView();
            if (view != null) {
                view.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                view.setIconified(false);
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        view.clearFocus();
                        presenter.search(s);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return true;
                    }
                });
                view.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        return false;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public void showSearchError(String message) {
    }

    @Override
    public void showCityName(final String city) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(city);
            }
        });
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
