package inf.msc.yawapp.favourites;

import javax.inject.Inject;

import inf.msc.yawapp.model.FavouritesData;

public class FavouritesPresenterImpl implements FavouritesPresenter {

    @Inject
    FavouritesView view;

    @Inject
    FavouritesData model;

    @Override
    public void update() {
        view.clearView();
        view.addFavourites(model.getAll());
    }
}
