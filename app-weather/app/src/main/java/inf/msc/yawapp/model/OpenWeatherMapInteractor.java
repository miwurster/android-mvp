package inf.msc.yawapp.model;

import android.os.AsyncTask;

import net.aksingh.java.api.owm.CurrentWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;

public class OpenWeatherMapInteractor implements WeatherSearchInteractor {
    private final OpenWeatherMap owm = new OpenWeatherMap(OpenWeatherMap.OWM_URL.PARAMETER_UNITS_VALUE_METRIC, "");

    private class GetWeatherTask extends AsyncTask<String, Void, String> {
        private WeatherDataListener listener;

        @Override
        protected String doInBackground(String... params) {
            try {
                CurrentWeatherData cwd = owm.currentWeatherByCityName(params[0]);
                try {
                    if (listener != null) {
                        listener.onWeatherDataAvailable(new OpenWeatherMapWeatherData(cwd));
                    }
                } catch (Exception e) {
                    // TODO: Log error
                }
            } catch (Exception e) {
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
