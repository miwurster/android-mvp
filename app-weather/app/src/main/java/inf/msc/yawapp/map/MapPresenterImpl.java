package inf.msc.yawapp.map;

import java.io.Serializable;

import javax.inject.Inject;

import inf.msc.yawapp.model.Location;
import inf.msc.yawapp.model.SubmitSearchInteractor;

public class MapPresenterImpl implements MapPresenter {

    @Inject
    SubmitSearchInteractor submitSearchInteractor;

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
    public void submitSearch(final float latitude, final float longitude) {
        submitSearchInteractor.submitSearch(new LngLatLocation(latitude, longitude));
    }
}
