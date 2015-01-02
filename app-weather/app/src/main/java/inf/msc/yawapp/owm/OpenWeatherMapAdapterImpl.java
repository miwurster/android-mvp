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

    private CurrentWeatherData handleResponse(final String responseString) throws OpenWeatherMapException {
        try {
            if (responseString == null) {
                throw new OpenWeatherMapException();
            }
            JSONObject jsonResponseObject = new JSONObject(responseString);
            if (!jsonResponseObject.has(JSON_COD) || jsonResponseObject.getInt(JSON_COD) != ERRC_SUCCESS) {
                if (jsonResponseObject.has(JSON_MESSAGE)) {
                    throw new OpenWeatherMapException(jsonResponseObject.getString(JSON_MESSAGE));
                } else {
                    throw new OpenWeatherMapException();
                }
            }
            return new CurrentWeatherData(jsonResponseObject);
        } catch (JSONException e) {
            throw new OpenWeatherMapException(e);
        }
    }

    @Override
    public CurrentWeatherData currentWeatherByCityName(final String cityName) throws OpenWeatherMapException {
        try {
            String jsonResponseString = response.currentWeatherByCityName(cityName);
            return handleResponse(jsonResponseString);
        } catch (IOException e) {
            throw new OpenWeatherMapException(e);
        }
    }

    @Override
    public CurrentWeatherData currentWeatherByCityCode(long cityCode) throws OpenWeatherMapException {
        try {
            String jsonResponseString = response.currentWeatherByCityCode(cityCode);
            return handleResponse(jsonResponseString);
        } catch (IOException e) {
            throw new OpenWeatherMapException(e);
        }
    }

    @Override
    public CurrentWeatherData currentWeatherByCoord(float latitude, float longitude) throws OpenWeatherMapException {
        try {
            String jsonResponseString = response.currentWeatherByCoordinates(latitude, longitude);
            return handleResponse(jsonResponseString);
        } catch (IOException e) {
            throw new OpenWeatherMapException(e);
        }
    }
}

