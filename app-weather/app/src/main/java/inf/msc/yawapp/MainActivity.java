package inf.msc.yawapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import net.aksingh.java.api.owm.CurrentWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getWeather(View view) {
        EditText editText = (EditText) findViewById(R.id.txtCityName);
        new GetWeatherTask().execute(editText.getText().toString());
    }

    private class GetWeatherTask extends AsyncTask<String, Void, String> {

        private OpenWeatherMap owm = new OpenWeatherMap(OpenWeatherMap.OWM_URL.PARAMETER_UNITS_VALUE_METRIC, "");

        @Override
        protected String doInBackground(String... cityNames) {
            try {
                String cityName = cityNames[0];
                CurrentWeatherData cwd = owm.currentWeatherByCityName(cityName);

                System.out.println("City: " + cwd.getCityName());
                System.out.println("Temperature: " + cwd.getMainData_Object().getTemperature());
                System.out.println("Temperature (max/min): "
                                + cwd.getMainData_Object().getMaxTemperature()
                                + "/" + cwd.getMainData_Object().getMinTemperature()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
