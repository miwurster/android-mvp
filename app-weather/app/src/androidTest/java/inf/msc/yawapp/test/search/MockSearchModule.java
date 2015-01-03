package inf.msc.yawapp.test.search;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.navigation.NavigationPresenter;
import inf.msc.yawapp.search.SearchActivity;
import inf.msc.yawapp.search.SearchPresenter;
import inf.msc.yawapp.search.SearchView;
import inf.msc.yawapp.test.MockNavigationPresenter;

@Module(
        injects = {
                SearchActivity.class
        },
        complete = false,
        library = true
)
public class MockSearchModule {

    private SearchActivity searchActivity;
    private SearchPresenter searchPresenter;

    public MockSearchModule(SearchActivity searchActivity, SearchPresenter searchPresenter) {
        this.searchActivity = searchActivity;
        this.searchPresenter = searchPresenter;
    }

    @Provides
    @Singleton
    public SearchView provideSearchView() {
        return searchActivity;
    }

    @Provides
    @Singleton
    public SearchPresenter provideSearchPresenter() {
        return searchPresenter;
    }

    @Provides
    @Singleton
    public NavigationPresenter provideNavigationPresenter() {
        return new MockNavigationPresenter();
    }
}
