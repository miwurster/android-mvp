package inf.msc.yawapp.model;

import android.os.AsyncTask;
import android.util.Log;

import net.aksingh.java.api.owm.CurrentWeatherData;

import javax.inject.Inject;

import inf.msc.yawapp.owm.OpenWeatherMapAdapter;

public class OpenWeatherMapInteractor implements WeatherSearchInteractor {

    @Inject
    OpenWeatherMapAdapter openWeatherMapAdapter;

    private static final String TAG = OpenWeatherMapInteractor.class.getSimpleName();

    private class GetWeatherTask extends AsyncTask<String, Void, Void> {
        private WeatherDataListener listener;

        @Override
        protected Void doInBackground(String... params) {
            try {
                CurrentWeatherData cwd = openWeatherMapAdapter.currentWeatherByCityName(params[0]);
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
    }

    private class GetLocationWeatherTask extends AsyncTask<Location, Void, Void> {
        private WeatherDataListener listener;

        @Override
        protected Void doInBackground(Location... params) {
            try {
                CurrentWeatherData cwd = openWeatherMapAdapter.currentWeatherByCityCode(params[0].getId());
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
    }

    @Override
    public void search(String query, WeatherDataListener listener) {
        GetWeatherTask task = new GetWeatherTask();
        task.setListener(listener);
        task.execute(query);
    }

    @Override
    public void search(Location location, WeatherDataListener listener) {
        if (location.getId() > 0) {
            GetLocationWeatherTask task = new GetLocationWeatherTask();
            task.setListener(listener);
            task.execute(location);
        } else {
            search(location.getCity(), listener);
        }
    }
}
