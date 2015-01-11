/*
 * This source code is part of the research paper
 * "Applying Model-View-Presenter Architecture to the Android Framework with App Prototype"
 *
 * Software Architecture and Management,
 * Herman-Hollerith-Zentrum, Reutlingen University
 *
 * Authors:  Dennis G. Geisse,
 *           Iven John,
 *           Daniel Joos,
 *           Sebastian Kotstein,
 *           Michael Wurster
 *
 * Year:     2015
 */

package inf.msc.yawapp.navigation;

import inf.msc.yawapp.R;

public enum NavDrawerItem {
    NAV_MAP(0, R.string.nav_maps, R.drawable.ic_map_black_24dp),
    NAV_FAVOURITES(1, R.string.nav_favourites, R.drawable.ic_favorite_black_24dp);

    private final int id;
    private final int titleResource;
    private final int iconResource;

    private NavDrawerItem(int id, int titleResource, int iconResource) {
        this.id = id;
        this.titleResource = titleResource;
        this.iconResource = iconResource;
    }

    public int getId() {
        return id;
    }

    public int getTitleResource() {
        return titleResource;
    }

    public int getIconResource() {
        return iconResource;
    }
}
