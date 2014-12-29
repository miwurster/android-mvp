package inf.msc.yawapp.model;

/**
 * Created by Sebastian on 28.12.2014.
 */
public class Location {

    private long id;

    private String longitude;
    private String latitude;

    private String country;
    private String zip;
    private String city;
    private String address;


    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongitude() {
        if(longitude == null){
            return "";
        }
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        if(latitude == null){
            return "";
        }
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        if(country == null){
            return "";
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        if(zip == null){
            return "";
        }
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        if(city == null){
            return "";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        if(address == null){
            return "";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
