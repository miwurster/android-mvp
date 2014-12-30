package inf.msc.yawapp.search;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                SearchActivity.class
        },
        complete = false
)
public class SearchModule {

    private final SearchActivity view;

    public SearchModule(SearchActivity view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public SearchView provideSearchView() {
        return view;
    }

    @Provides
    @Singleton
    public SubmitSearchInteractor provideSubmitSearchInteractor() {
        return view;
    }

    @Provides
    @Singleton
    public SearchPresenter provideSearchPresenter(SearchPresenterImpl presenter) {
        return presenter;
    }
}
