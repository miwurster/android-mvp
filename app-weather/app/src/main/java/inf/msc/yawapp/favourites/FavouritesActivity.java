package inf.msc.yawapp.favourites;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.model.Location;
import inf.msc.yawapp.model.SubmitSearchInteractor;
import inf.msc.yawapp.search.SearchActivity;

public class FavouritesActivity extends BaseModuleActivity implements FavouritesView {

    @Inject
    FavouritesPresenter presenter;

    @Inject
    SubmitSearchInteractor submitSearchInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getActionBarToolbar();

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
                startActivity(new Intent(this, SearchActivity.class));
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout list = (LinearLayout) findViewById(R.id.favList);
                list.removeAllViews();
            }
        });
    }

    @Override
    public void addFavourites(final List<Location> items) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout list = (LinearLayout) findViewById(R.id.favList);
                for (int i = 0; i < items.size(); i++) {
                    View convertView = getLayoutInflater().inflate(R.layout.item_favourites, list, false);

                    TextView city = (TextView) convertView.findViewById(R.id.city);
                    city.setText(items.get(i).getCity());

                    TextView misc = (TextView) convertView.findViewById(R.id.misc);
                    misc.setText(items.get(i).getCountry());

                    final Location location = items.get(i);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submitSearchInteractor.submitSearch(location);
                        }
                    });

                    list.addView(convertView);
                }
            }
        });
    }

}
