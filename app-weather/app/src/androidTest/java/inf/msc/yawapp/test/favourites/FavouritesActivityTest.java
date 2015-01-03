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

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.addFavourites(testLocations);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(2, list.getCount());
        assertEquals("Paradise City", ((Location) list.getItemAtPosition(0)).getCity());

        // Clear list
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.clearView();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(0, list.getCount());
    }

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

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.addFavourites(testLocations);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(1, list.getCount());

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.performItemClick(list.getChildAt(0), 0, list.getItemIdAtPosition(0));
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(1, presenter.submitSearchList.size());
        assertEquals(testLocation, presenter.submitSearchList.get(0));
    }
}
