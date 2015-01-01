package inf.msc.yawapp.model;

public class FavouritesStoreEntry implements MutableLocation {

    private long id;
    private String city;
    private String country;
    private float latitude;
    private float longitude;

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getCity() {
        return (city != null) ? city : "";
    }

    @Override
    public String getCountry() {
        return (country != null) ? country : "";
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
