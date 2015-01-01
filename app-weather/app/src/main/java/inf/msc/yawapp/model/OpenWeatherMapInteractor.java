package inf.msc.yawapp.model;

import android.os.AsyncTask;
import android.util.Log;

import net.aksingh.java.api.owm.CurrentWeatherData;

import javax.inject.Inject;

import inf.msc.yawapp.owm.OpenWeatherMapAdapter;

public class OpenWeatherMapInteractor implements WeatherSearchInteractor {

    @Inject
    OpenWeatherMapAdapter openWeatherMapAdapter;

    private class GetWeatherTask extends AsyncTask<String, Void, String> {
        private WeatherDataListener listener;

        @Override
        protected String doInBackground(String... params) {
            try {
                CurrentWeatherData cwd = openWeatherMapAdapter.currentWeatherByCityName(params[0]);
                try {
                    if (listener != null) {
                        listener.onWeatherDataAvailable(new OpenWeatherMapWeatherData(cwd));
                    }
                } catch (Exception e) {
                    Log.d("yawapp", "Failed to notify listener! Error was: " + e.getMessage());
                }
            } catch (Exception e) {
                Log.e("yawapp", "Failed to fetch weather data! Error was: " + e.getMessage());
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
}
