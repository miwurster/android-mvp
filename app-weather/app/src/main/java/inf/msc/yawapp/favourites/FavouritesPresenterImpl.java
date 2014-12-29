package inf.msc.yawapp.favourites;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import inf.msc.yawapp.model.FavouritesData;
import inf.msc.yawapp.model.Location;

/**
 * Created by Sebastian on 28.12.2014.
 */
public class FavouritesPresenterImpl implements FavouritesPresenter {

    @Inject
    FavouritesView view;

    @Inject
    FavouritesData model;


    @Override
    public void update() {
        view.clearView();
        view.addFavourites(model.getAll());


        /*
        List<Location> list = new ArrayList<Location>();
        for(int i = 0; i < 20; i++ ) {

            Location l0 = new Location();
            l0.setCity("Böblingen");
            l0.setZip("71034");
            l0.setCountry("DE");
            l0.setAddress("Danziger Straße 6");
            list.add(l0);

            Location l1 = new Location();
            l1.setCity("Berlin");
            l1.setZip("10001");
            l1.setCountry("DE");
            l1.setAddress("Unter den Linden");
            list.add(l1);
            view.addFavourites(list);

        }

       */

    }

    @Override
    public void search(String query) {
        view.clearView();
        view.addFavourites(model.search(query));
    }
}
