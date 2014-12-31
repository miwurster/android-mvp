package inf.msc.yawapp.details;

public interface WeatherDetailsPresenter {
    public void search(final String query);

    public void refresh();

    public void presentExistingData();
}
