package inf.msc.yawapp.model;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inf.msc.yawapp.MainApplication;

@Module(
        injects ={
                WeatherSearchInteractor.class,
                FavouritesData.class
        }
)
public class ModelModule {

    @Inject
    MainApplication main;

    @Provides
    @Singleton
    public WeatherSearchInteractor provideWeatherSearchInteractor() {
        return new OpenWeatherMapInteractor();
    }


    @Provides
    @Singleton
    public FavouritesData provideFavouritesData(){
        return new FavouritesDataImpl(main);
    }

}
