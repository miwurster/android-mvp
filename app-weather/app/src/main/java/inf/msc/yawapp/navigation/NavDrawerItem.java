package inf.msc.yawapp.navigation;

import inf.msc.yawapp.R;

public enum NavDrawerItem {
    NAV_MAP(0, R.string.nav_maps),
    NAV_FAVOURITES(1, R.string.nav_favourites);

    private final int id;
    private final int titleResource;

    private NavDrawerItem(int id, int titleResource) {
        this.id = id;
        this.titleResource = titleResource;
    }

    public int getId() {
        return id;
    }

    public int getTitleResource() {
        return titleResource;
    }
}
