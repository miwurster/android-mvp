package inf.msc.yawapp.map;

import android.content.Context;

import java.io.Serializable;

import javax.inject.Inject;

import inf.msc.yawapp.model.Location;
import inf.msc.yawapp.navigation.NavigationPresenter;
import inf.msc.yawapp.navigation.SubmitSearchInteractor;

public class MapPresenterImpl implements MapPresenter {

    @Inject
    MapView view;

    @Inject
    SubmitSearchInteractor submitSearchInteractor;

    @Inject
    NavigationPresenter navigationPresenter;

    static private class LngLatLocation implements Location, Serializable {

        private float latitude;
        private float longitude;

        private LngLatLocation(float latitude, float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public long getId() {
            return -1;
        }

        @Override
        public String getCity() {
            return null;
        }

        @Override
        public String getCountry() {
            return null;
        }

        @Override
        public float getLatitude() {
            return latitude;
        }

        @Override
        public float getLongitude() {
            return longitude;
        }
    }

    @Override
    public void mark(float latitude, float longitude) {
        view.removeMarkers();
        view.showMarkerAt(latitude, longitude);
    }

    @Override
    public void submitSearch(final float latitude, final float longitude) {
        submitSearchInteractor.submitSearch(new LngLatLocation(latitude, longitude));
    }

    @Override
    public void navigateToSearch(Context context) {
        navigationPresenter.navigateSearch(context);
    }
}
