package inf.msc.yawapp.favourites;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.BaseModuleActivity;
import inf.msc.yawapp.R;
import inf.msc.yawapp.details.WeatherDetailsModule;
import inf.msc.yawapp.model.Location;

/**
 * Created by Sebastian on 28.12.2014.
 */
public class FavouritesActivity extends BaseModuleActivity implements FavouritesView {

    private Toolbar toolbar;
    private MenuItem searchItem;

    @Inject
    FavouritesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        toolbar = getActionBarToolbar();



        presenter.update();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        this.searchItem = searchItem;
        if (searchItem != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView view = (SearchView) searchItem.getActionView();
            if (view != null) {
                view.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                view.setIconified(true);
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {

                        presenter.search(s);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {

                        return true;
                    }
                });
            }
        }
        return true;
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new FavouritesModule(this));
    }

    @Override
    public void clearView() {
        LinearLayout list = (LinearLayout) findViewById(R.id.favList);
        list.removeAllViews();


    }

    @Override
    public void addFavourites(List<Location> items) {
        LinearLayout list = (LinearLayout) findViewById(R.id.favList);
        for(int i = 0; i < items.size(); i++){

            View convertView = getLayoutInflater().inflate(R.layout.item_favourites,list,false);


            //RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.favItem);

            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(items.get(i).getCity());

            TextView misc = (TextView) convertView.findViewById(R.id.misc);
            misc.setText(items.get(i).getCountry()+ " " +items.get(i).getZip()+"  "+items.get(i).getAddress());

            list.addView(convertView);
        }

    }


}
