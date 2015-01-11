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
        return new GenericCache<>();
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
