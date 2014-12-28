package inf.msc.yawapp.model;

public interface WeatherSearchInteractor {
    public void search(final String query, final WeatherDataListener listener);
}
