package inf.msc.yawapp.test.model;

import inf.msc.yawapp.model.Location;

public class MockLocation implements Location {

    public long id;
    public String city;
    public String country;
    public float latitude;
    public float longitude;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCountry() {
        return country;
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
