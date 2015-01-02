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
