package inf.msc.yawapp.model;

import java.io.Serializable;

public interface Location extends Serializable {
    public long getId();

    public String getCity();

    public String getCountry();

    public float getLatitude();

    public float getLongitude();
}
