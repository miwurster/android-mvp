package inf.msc.yawapp.model;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.MainModule;

@Module(
        injects ={
                WeatherSearchInteractor.class,
                //FavouritesData.class
        },
        complete = false,
        library = true
)
public class ModelModule {


    @Provides
    @Singleton
    public WeatherSearchInteractor provideWeatherSearchInteractor() {
        return new OpenWeatherMapInteractor();
    }


    @Provides
    @Singleton
    public FavouritesData provideFavouritesData(FavouritesDataImpl favouritesData){
        return favouritesData;
    }

}
