/*
 * This source code is part of the research paper
 * "Applying Model-View-Presenter Architecture to the Android Framework with App Prototype"
 *
 * Software Architecture and Management,
 * Herman-Hollerith-Zentrum, Reutlingen University
 *
 * Authors:  Dennis G. Geisse,
 *           Iven John,
 *           Daniel Joos,
 *           Sebastian Kotstein,
 *           Michael Wurster
 *
 * Year:     2015
 */

package inf.msc.yawapp.favourites;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.model.Location;

public class FavouritesActivity extends BaseModuleActivity implements FavouritesView {

    @Inject
    FavouritesPresenter presenter;

    private FavouritesListViewAdapter favouritesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getActionBarToolbar();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                getActionBarToolbar().setTitle(R.string.activity_header_favourites);
            }
        });

        ListView favouritesList = (ListView) findViewById(R.id.favourites_container);
        favouritesListAdapter = new FavouritesListViewAdapter(this);
        favouritesList.setAdapter(favouritesListAdapter);
        favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Location item = (Location) parent.getItemAtPosition(position);
                presenter.submitSearch(item);
            }
        });

        presenter.init();
        presenter.update();
    }

    @Override
    protected void onDestroy() {
        presenter.deinit();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.favourites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                presenter.navigateToSearch(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new FavouritesModule(this));
    }

    @Override
    public void clearView() {
        favouritesListAdapter.clear();
    }

    @Override
    public void addFavourites(final List<Location> items) {
        favouritesListAdapter.addAll(items);
    }

}
