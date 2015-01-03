package inf.msc.yawapp.test.search;

import java.util.ArrayList;
import java.util.List;

import inf.msc.yawapp.search.SearchPresenter;

public class MockSearchPresenter implements SearchPresenter {

    public List<String> updatedSearches = new ArrayList<>();
    public List<String> submittedSearches = new ArrayList<>();
    public int closeCount = 0;


    @Override
    public void updateSearch(String query) {
        updatedSearches.add(query);
    }

    @Override
    public void submitSearch(String query) {
        submittedSearches.add(query);
    }

    @Override
    public void close() {
        closeCount++;
    }
}
