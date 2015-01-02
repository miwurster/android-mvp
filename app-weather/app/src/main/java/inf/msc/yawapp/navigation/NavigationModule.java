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
    public NavigationPresenter provideNavigationPresenter(NavigationPresenterImpl presenter) {
        return presenter;
    }
}
