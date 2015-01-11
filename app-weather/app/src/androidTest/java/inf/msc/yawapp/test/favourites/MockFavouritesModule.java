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

package inf.msc.yawapp.test.favourites;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.favourites.FavouritesActivity;
import inf.msc.yawapp.favourites.FavouritesPresenter;
import inf.msc.yawapp.favourites.FavouritesView;
import inf.msc.yawapp.navigation.NavigationPresenter;
import inf.msc.yawapp.test.MockNavigationPresenter;

@Module(
        injects = {
                FavouritesActivity.class
        },
        complete = false,
        library = true
)
public class MockFavouritesModule {

    private FavouritesActivity favouritesActivity;
    private FavouritesPresenter favouritesPresenter;

    public MockFavouritesModule(FavouritesActivity favouritesActivity, FavouritesPresenter favouritesPresenter) {
        this.favouritesActivity = favouritesActivity;
        this.favouritesPresenter = favouritesPresenter;
    }

    @Provides
    @Singleton
    public FavouritesView provideFavouritesView() {
        return favouritesActivity;
    }

    @Provides
    @Singleton
    public FavouritesPresenter provideFavouritesPresenter() {
        return favouritesPresenter;
    }

    @Provides
    @Singleton
    public NavigationPresenter provideNavigationPresenter() {
        return new MockNavigationPresenter();
    }
}
