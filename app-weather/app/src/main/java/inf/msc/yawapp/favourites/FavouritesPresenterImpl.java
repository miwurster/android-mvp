package inf.msc.yawapp.favourites;

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericObservable;
import inf.msc.yawapp.common.GenericObserver;
import inf.msc.yawapp.model.FavouritesStore;
import inf.msc.yawapp.model.FavouritesStoreOperation;
import inf.msc.yawapp.model.Location;
import inf.msc.yawapp.navigation.SubmitSearchInteractor;

public class FavouritesPresenterImpl implements FavouritesPresenter, GenericObserver<FavouritesStoreOperation> {

    @Inject
    FavouritesView view;

    @Inject
    FavouritesStore model;

    @Inject
    GenericObservable<FavouritesStoreOperation> favouritesStoreObservable;

    @Inject
    SubmitSearchInteractor submitSearchInteractor;

    @Override
    public void init() {
        favouritesStoreObservable.registerObserver(this);
    }

    @Override
    public void deinit() {
        favouritesStoreObservable.unregisterObserver(this);
    }

    @Override
    public void update() {
        view.clearView();
        view.addFavourites(model.getAll());
    }

    @Override
    public void submitSearch(Location location) {
        submitSearchInteractor.submitSearch(location);
    }

    @Override
    public void update(GenericObservable<FavouritesStoreOperation> observable, FavouritesStoreOperation argument) {
        update();
    }
}
