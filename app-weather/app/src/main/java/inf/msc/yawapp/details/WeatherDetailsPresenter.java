package inf.msc.yawapp.details;

import android.os.Bundle;

import inf.msc.yawapp.model.Location;

public interface WeatherDetailsPresenter {

    public void restore(final Bundle savedInstancestate);

    public void backup(Bundle outState);

    public boolean isInitialized();

    public void search(final String query);

    public void search(final Location location);

    public void refresh();

    public void presentExistingData();

    public void toggleFavourite();
}
