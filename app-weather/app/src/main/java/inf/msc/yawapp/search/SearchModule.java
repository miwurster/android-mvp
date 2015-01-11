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
    public SearchPresenter provideSearchPresenter(SearchPresenterImpl presenter) {
        return presenter;
    }
}
