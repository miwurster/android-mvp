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

package inf.msc.yawapp.test.search;

import android.support.v7.widget.SearchView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

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
        assertNotNull(searchView);
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchView.setIconified(true);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(1, mockSearchPresenter.closeCount);
    }

    /**
     * Tests if given a suggestion list is displayed correctly in the list view of the search
     * activity. Also tests, whether clearing of the list works as expected.
     *
     * @throws Exception
     */
    public void testShowSuggestions() throws Exception {
        ListView listView = (ListView) searchActivity.findViewById(R.id.suggestions_container);
        assertNotNull(listView);

        // Fill
        assertEquals(0, listView.getCount());
        final List<String> suggestions = Arrays.asList("Foo", "Bar");
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchActivity.showSuggestions(suggestions);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(2, listView.getCount());
        assertEquals("Foo", listView.getItemAtPosition(0));
        assertEquals("Bar", listView.getItemAtPosition(1));

        // Clear
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchActivity.clearSuggestions();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(0, listView.getCount());
    }

    /**
     * This test shows some suggestions and simulates a click on one of those items.
     * This should result in a submitted search on the presenter object.
     *
     * @throws Exception
     */
    public void testClickSuggestion() throws Exception {
        final ListView listView = (ListView) searchActivity.findViewById(R.id.suggestions_container);
        assertNotNull(listView);

        // Fill in some suggestions
        assertEquals(0, listView.getCount());
        final List<String> suggestions = Arrays.asList("Foo", "Bar");
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchActivity.showSuggestions(suggestions);
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals(2, listView.getCount());

        // Click on one of the suggestion items
        searchActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getChildAt(0), 0, listView.getItemIdAtPosition(0));
            }
        });
        getInstrumentation().waitForIdleSync();

        assertEquals(1, mockSearchPresenter.submittedSearches.size());
        assertEquals("Foo", mockSearchPresenter.submittedSearches.get(0));
    }
}
