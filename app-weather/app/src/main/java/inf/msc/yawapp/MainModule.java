package inf.msc.yawapp;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MainApplication.class
        }
)
public class MainModule {
    private final MainApplication application;

    public MainModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    public MainApplication provideMainApplication() {
        return this.application;
    }
}
