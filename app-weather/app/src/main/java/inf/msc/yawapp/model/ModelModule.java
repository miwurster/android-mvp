package inf.msc.yawapp.model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.common.GenericCache;
import inf.msc.yawapp.common.GenericObservable;

@Module(
        complete = false,
        library = true
)
public class ModelModule {

    @Provides
    @Singleton
    public WeatherSearchInteractor provideWeatherSearchInteractor(OpenWeatherMapInteractor interactor) {
        return interactor;
    }

    @Provides
    @Singleton
    public GenericCache<WeatherData> provideWeatherDataCache() {
        return new GenericCache<WeatherData>();
    }

    @Provides
    @Singleton
    public SubmitSearchInteractor provideSubmitSearchInteractor(SubmitSearchInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @Singleton
    public FavouritesStore provideFavouritesStore(FavouritesStoreImpl favouritesStore) {
        return favouritesStore;
    }

    @Provides
    @Singleton
    public GenericObservable<FavouritesStoreOperation> provideFavouritesStoreObservable() {
        return new GenericObservable<>();
    }

    @Provides
    @Singleton
    public SearchSuggestions provideSearchSuggestions(CitySearchSuggestions searchSuggestions) {
        return searchSuggestions;
    }
}
