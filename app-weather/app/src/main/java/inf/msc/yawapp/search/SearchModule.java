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
    public SearchActivity provideSearchActivity() {
        return view;
    }
}
