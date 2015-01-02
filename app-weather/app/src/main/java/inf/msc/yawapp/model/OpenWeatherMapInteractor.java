package inf.msc.yawapp.model;

import android.os.AsyncTask;
import android.util.Log;

import net.aksingh.java.api.owm.CurrentWeatherData;

import javax.inject.Inject;

import inf.msc.yawapp.owm.OpenWeatherMapAdapter;
import inf.msc.yawapp.owm.OpenWeatherMapException;

public class OpenWeatherMapInteractor implements WeatherSearchInteractor {

    @Inject
    OpenWeatherMapAdapter openWeatherMapAdapter;

    private static final String TAG = OpenWeatherMapInteractor.class.getSimpleName();

    private abstract class AbstractGetWeatherTask<T> extends AsyncTask<T, Void, Void> {
        private WeatherDataListener listener;

        @Override
        protected Void doInBackground(T... params) {
            try {
                CurrentWeatherData cwd = getCurrentWeatherData(params[0]);
                OpenWeatherMapWeatherData data = new OpenWeatherMapWeatherData(cwd);
                try {
                    if (listener != null) {
                        listener.onWeatherDataAvailable(data);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "Failed to notify listener! Error was: " + e.getMessage());
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to fetch weather data! Error was: " + e.getMessage());
                if (listener != null) {
                    listener.onWeatherDataError();
                }
            }
            return null;
        }

        public void setListener(WeatherDataListener listener) {
            this.listener = listener;
        }

        public abstract CurrentWeatherData getCurrentWeatherData(T param) throws OpenWeatherMapException;
    }

    private class GetWeatherSearchTask extends AbstractGetWeatherTask<String> {

        @Override
        public CurrentWeatherData getCurrentWeatherData(String param) throws OpenWeatherMapException {
            return openWeatherMapAdapter.currentWeatherByCityName(param);
        }
    }

    private class GetWeatherCityIdTask extends AbstractGetWeatherTask<Location> {

        @Override
        public CurrentWeatherData getCurrentWeatherData(Location param) throws OpenWeatherMapException {
            return openWeatherMapAdapter.currentWeatherByCityCode(param.getId());
        }
    }

    private class GetWeatherCoordTask extends AbstractGetWeatherTask<Location> {

        @Override
        public CurrentWeatherData getCurrentWeatherData(Location param) throws OpenWeatherMapException {
            return openWeatherMapAdapter.currentWeatherByCoord(param.getLatitude(), param.getLongitude());
        }
    }

    @Override
    public void search(String query, WeatherDataListener listener) {
        GetWeatherSearchTask task = new GetWeatherSearchTask();
        task.setListener(listener);
        task.execute(query);
    }

    @Override
    public void search(Location location, WeatherDataListener listener) {
        if (location.getId() > 0) {
            GetWeatherCityIdTask task = new GetWeatherCityIdTask();
            task.setListener(listener);
            task.execute(location);
        } else if (location.getCity() == null || location.getCity().isEmpty()) {
            GetWeatherCoordTask task = new GetWeatherCoordTask();
            task.setListener(listener);
            task.execute(location);
        } else {
            search(location.getCity(), listener);
        }
    }
}
