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


/**
 * Created by Sebastian on 28.12.2014.
 */
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
