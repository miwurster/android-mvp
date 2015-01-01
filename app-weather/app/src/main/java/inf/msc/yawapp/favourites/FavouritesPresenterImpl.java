package inf.msc.yawapp.favourites;

import javax.inject.Inject;

import inf.msc.yawapp.common.GenericObservable;
import inf.msc.yawapp.common.GenericObserver;
import inf.msc.yawapp.model.FavouritesStore;
import inf.msc.yawapp.model.Location;

public class FavouritesPresenterImpl implements FavouritesPresenter, GenericObserver<Location> {

    @Inject
    FavouritesView view;

    @Inject
    FavouritesStore model;

    @Inject
    GenericObservable<Location> locationObservable;

    @Override
    public void init() {
        locationObservable.registerObserver(this);
    }

    @Override
    public void deinit() {
        locationObservable.unregisterObserver(this);
    }

    @Override
    public void update() {
        view.clearView();
        view.addFavourites(model.getAll());
    }

    @Override
    public void update(GenericObservable<Location> observable, Location argument) {
        update();
    }
}
