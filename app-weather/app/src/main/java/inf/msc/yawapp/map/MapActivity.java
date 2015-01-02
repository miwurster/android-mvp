package inf.msc.yawapp.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.search.SearchActivity;

public class MapActivity extends BaseModuleActivity implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    @Inject
    MapPresenter presenter;

    private MapFragment mapFragment;
    private Marker mapMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getActionBarToolbar();

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMap().getUiSettings().setZoomControlsEnabled(true);
        mapFragment.getMap().setOnMapLongClickListener(this);
        mapFragment.getMap().setOnMarkerClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.map, menu);
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
        return Arrays.<Object>asList(new MapModule());
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (mapMarker != null) {
            mapMarker.remove();
        }
        mapMarker = mapFragment.getMap().addMarker(new MarkerOptions().position(latLng));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng latLng = marker.getPosition();
        presenter.submitSearch((float) latLng.latitude, (float) latLng.longitude);
        return true;
    }
}
