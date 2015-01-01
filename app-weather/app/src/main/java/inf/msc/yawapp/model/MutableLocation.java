package inf.msc.yawapp.model;

public interface MutableLocation extends Location {
    public void setId(long id);

    public void setCity(final String city);

    public void setCountry(final String country);

    public void setLatitude(float latitude);

    public void setLongitude(float longitude);
}
