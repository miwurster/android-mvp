package inf.msc.yawapp.navigation;

import inf.msc.yawapp.model.Location;

public interface SubmitSearchInteractor {
    public void submitSearch(final String query);

    public void submitSearch(final Location location);
}
