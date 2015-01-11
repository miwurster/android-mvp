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

package inf.msc.yawapp.navigation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true
)
public class NavigationModule {

    @Provides
    @Singleton
    public NavigationPresenter provideNavigationPresenter() {
        return new NavigationPresenterImpl();
    }

    @Provides
    @Singleton
    public SubmitSearchInteractor provideSubmitSearchInteractor(SubmitSearchInteractorImpl interactor) {
        return interactor;
    }
}
