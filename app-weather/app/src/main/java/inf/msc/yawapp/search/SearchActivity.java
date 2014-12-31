package inf.msc.yawapp.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;

public class SearchActivity extends BaseModuleActivity implements inf.msc.yawapp.search.SearchView {

    @Inject
    SearchPresenter presenter;

    private ArrayAdapter<String> suggestionsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView suggestionsList = (ListView) findViewById(R.id.suggestions_container);
        suggestionsListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        suggestionsList.setAdapter(suggestionsListAdapter);

        suggestionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        presenter.submitSearch(item);
                    }
                });
            }
        });

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

        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setIconified(false);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        searchView.clearFocus();
                        presenter.submitSearch(s);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        presenter.updateSearch(s);
                        return true;
                    }
                });
                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        presenter.close();
                        return false;
                    }
                });
            }
        }
        return true;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new SearchModule(this));
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void clearSuggestions() {
        suggestionsListAdapter.clear();
    }

    @Override
    public void showSuggestions(List<String> suggestions) {
        suggestionsListAdapter.clear();
        suggestionsListAdapter.addAll(suggestions);
    }
}
