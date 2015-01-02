package inf.msc.yawapp.map;

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

public class MapActivity extends BaseModuleActivity implements MapView, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

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
                presenter.navigateToSearch(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MapModule(this));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        presenter.mark((float) latLng.latitude, (float) latLng.longitude);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng latLng = marker.getPosition();
        presenter.submitSearch((float) latLng.latitude, (float) latLng.longitude);
        return true;
    }

    @Override
    public void removeMarkers() {
        if (mapMarker != null) {
            mapMarker.remove();
        }
    }

    @Override
    public void showMarkerAt(float latitude, float longitude) {
        LatLng position = new LatLng(latitude, longitude);
        mapMarker = mapFragment.getMap().addMarker(new MarkerOptions().position(position));
    }
}
