package inf.msc.yawapp.test.search;

import android.support.v7.widget.SearchView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import dagger.ObjectGraph;
import inf.msc.yawapp.R;
import inf.msc.yawapp.search.SearchActivity;

public class SearchActivityTest extends ActivityInstrumentationTestCase2<SearchActivity> {

    ObjectGraph objectGraph;
    SearchActivity searchActivity;
    MockSearchPresenter mockSearchPresenter;

    public SearchActivityTest() {
        super(SearchActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        searchActivity = getActivity();
        mockSearchPresenter = new MockSearchPresenter();
        objectGraph = ObjectGraph.create(new MockSearchModule(searchActivity, mockSearchPresenter));
        searchActivity.setModuleGraph(objectGraph);
    }

    /**
     * This test fires several keystrokes in a row and checks if SearchActivity behaves correctly
     * and is calling it's presenter object.
     * The test expects that the SearchActivity puts focus to the search field.
     * The presenter object should be called whenever the search string changes and on submit
     * of the search.
     *
     * @throws Exception
     */
    public void testSubmitSearch() throws Exception {
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_A);
        assertEquals(1, mockSearchPresenter.updatedSearches.size());
        assertEquals("a", mockSearchPresenter.updatedSearches.get(0));

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_B);
        assertEquals(2, mockSearchPresenter.updatedSearches.size());
        assertEquals("ab", mockSearchPresenter.updatedSearches.get(1));

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_C);
        assertEquals(3, mockSearchPresenter.updatedSearches.size());
        assertEquals("abc", mockSearchPresenter.updatedSearches.get(2));

        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
        assertEquals(1, mockSearchPresenter.submittedSearches.size());
        assertEquals("abc", mockSearchPresenter.submittedSearches.get(0));

        assertEquals(0, mockSearchPresenter.closeCount);
    }

    /**
     * Simulates a close of the search view and tests if the presenter is called accordingly.
     *
     * @throws Exception
     */
    public void testCloseSearch() throws Exception {
        assertEquals(0, mockSearchPresenter.closeCount);

        // simulate close
        final SearchView searchView = (SearchView) searchActivity.findViewById(R.id.action_search);
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchView.setIconified(true);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(1, mockSearchPresenter.closeCount);
    }
}
