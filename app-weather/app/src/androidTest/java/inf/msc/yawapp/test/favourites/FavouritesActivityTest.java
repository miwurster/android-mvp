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

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;
import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;
import inf.msc.yawapp.favourites.FavouritesActivity;
import inf.msc.yawapp.model.Location;
import inf.msc.yawapp.test.ModuleActivityUnitTestCase;
import inf.msc.yawapp.test.model.MockLocation;

public class FavouritesActivityTest extends ModuleActivityUnitTestCase<FavouritesActivity> {

    FavouritesActivity activity;
    MockFavouritesPresenter presenter;

    public FavouritesActivityTest() {
        super(FavouritesActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        presenter = new MockFavouritesPresenter();
        setGraphCreationStrategy(new BaseModuleActivity.GraphCreationStrategy() {
            @Override
            public ObjectGraph getModuleGraph(BaseModuleActivity activity) {
                return ObjectGraph.create(new MockFavouritesModule((FavouritesActivity) activity, presenter));
            }
        });
        activity = startActivity(null);

        // init & update should be called during onCreate of the activity
        assertEquals(1, presenter.initCount);
        assertEquals(1, presenter.updateCount);
    }

    /**
     * This test fills the favourites list view with two test location objects and expects those
     * entries to be visible in the list.
     * Afterwards the test attempts to clear the list through the FavouritesView interface.
     *
     * @throws Exception
     */
    public void testFavouritesList() throws Exception {
        ListView list = (ListView) activity.findViewById(R.id.favourites_container);
        assertNotNull(list);
        assertEquals(0, list.getCount());

        // Prepare some "favourite" locations
        final List<Location> testLocations = new ArrayList<>();
        MockLocation testLocation;
        testLocation = new MockLocation();
        testLocation.id = 1;
        testLocation.city = "Paradise City";
        testLocation.country = "Heaven";
        testLocation.latitude = .1f;
        testLocation.longitude = .2f;
        testLocations.add(testLocation);
        testLocation = new MockLocation();
        testLocation.id = 2;
        testLocation.city = "Somewhere";
        testLocation.country = "Over the rainbow";
        testLocation.latitude = 3.f;
        testLocation.longitude = 4.f;
        testLocations.add(testLocation);

        activity.addFavourites(testLocations);
        assertEquals(2, list.getCount());
        assertEquals("Paradise City", ((Location) list.getItemAtPosition(0)).getCity());

        // Clear list
        activity.clearView();
        assertEquals(0, list.getCount());
    }

    /**
     * Gives a test location object to the activity and simulates a click on that item in the
     * favourites list. The test expects the presenter to be called once with exactly the same
     * location object given to the view.
     *
     * @throws Exception
     */
    public void testFavouritesListClick() throws Exception {
        final ListView list = (ListView) activity.findViewById(R.id.favourites_container);
        assertNotNull(list);
        assertEquals(0, list.getCount());

        // Prepare "favourite" locations
        final List<Location> testLocations = new ArrayList<>();
        MockLocation testLocation;
        testLocation = new MockLocation();
        testLocation.id = 1;
        testLocation.city = "Paradise City";
        testLocations.add(testLocation);

        // Give the location to the activity through the FavouritesView interface
        activity.addFavourites(testLocations);
        assertEquals(1, list.getCount());

        // Click on that item in the list
        list.performItemClick(list.getChildAt(0), 0, list.getItemIdAtPosition(0));
        assertEquals(1, presenter.submitSearchList.size());
        assertEquals(testLocation, presenter.submitSearchList.get(0));
    }

    /**
     * Tests if the presenter object is notified correctly on destruction of the activity.
     *
     * @throws Exception
     */
    public void testDestroyActivity() throws Exception {
        assertEquals(0, presenter.deinitCount);
        getInstrumentation().callActivityOnDestroy(activity);
        assertEquals(1, presenter.deinitCount);
    }
}
