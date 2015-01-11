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

package inf.msc.yawapp.favourites;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                FavouritesActivity.class
        },
        complete = false
)
public class FavouritesModule {
    private final FavouritesView view;

    public FavouritesModule(FavouritesView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public FavouritesPresenter provideFavouritesPresenter(FavouritesPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    public FavouritesView provideFavouritesView() {
        return view;
    }


}
