package inf.msc.yawapp.map;

import android.content.Context;

public interface MapPresenter {
    public void mark(float latitude, float longitude);

    public void submitSearch(float latitude, float longitude);

    public void navigateToSearch(final Context context);
}
