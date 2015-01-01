package inf.msc.yawapp.owm;

import net.aksingh.java.api.owm.CurrentWeatherData;
import net.aksingh.java.api.owm.OpenWeatherMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class OpenWeatherMapAdapterImpl implements OpenWeatherMapAdapter {

    public static final String JSON_COD = "cod";
    public static final int ERRC_SUCCESS = 200;
    public static final String JSON_MESSAGE = "message";
    private OpenWeatherMap.OWM_Response response;

    public OpenWeatherMapAdapterImpl(OpenWeatherMap.OWM_URL unit, final String apiKey) {
        response = new OpenWeatherMap.OWM_Response(unit, apiKey);
    }

    @Override
    public CurrentWeatherData currentWeatherByCityName(final String cityName) throws OpenWeatherMapException {
        CurrentWeatherData cwd;
        String jsonResponseString;
        JSONObject jsonResponseObject;

        try {
            jsonResponseString = response.currentWeatherByCityName(cityName);
            if (jsonResponseString == null) {
                throw new OpenWeatherMapException();
            }
            jsonResponseObject = new JSONObject(jsonResponseString);
            if (!jsonResponseObject.has(JSON_COD) || jsonResponseObject.getInt(JSON_COD) != ERRC_SUCCESS) {
                if (jsonResponseObject.has(JSON_MESSAGE)) {
                    throw new OpenWeatherMapException(jsonResponseObject.getString(JSON_MESSAGE));
                } else {
                    throw new OpenWeatherMapException();
                }
            }
            cwd = new CurrentWeatherData(jsonResponseObject);
        } catch (JSONException | IOException e) {
            throw new OpenWeatherMapException(e);
        }

        return cwd;
    }
}

