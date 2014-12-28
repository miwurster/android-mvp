package inf.msc.yawapp.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.BaseActivity;
import inf.msc.yawapp.R;
import inf.msc.yawapp.search.SearchActivity;

public class WeatherDetailsActivity extends BaseActivity implements WeatherDetailsView {

    @Inject
    WeatherDetailsPresenter presenter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = getToolbar();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//
//        getMenuInflater().inflate(R.menu.search, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        this.searchItem = searchItem;
//        if (searchItem != null) {
//            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//            final SearchView view = (SearchView) searchItem.getActionView();
//            if (view != null) {
//                view.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//                view.setIconified(true);
//                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String s) {
//                        view.clearFocus();
//                        presenter.search(s);
//                        return true;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String s) {
//                        // TODO: Lookup in city dictionary here
//                        return true;
//                    }
//                });
//            }
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
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
