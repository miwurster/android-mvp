package inf.msc.yawapp.favourites;

import javax.inject.Inject;

import inf.msc.yawapp.model.FavouritesStore;

public class FavouritesPresenterImpl implements FavouritesPresenter {

    @Inject
    FavouritesView view;

    @Inject
    FavouritesStore model;

    @Override
    public void update() {
        view.clearView();
        view.addFavourites(model.getAll());
    }
}
