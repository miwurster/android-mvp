/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 Ashutosh Kumar Singh [me@aksingh.net]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
*/

package net.aksingh.java.api.owm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides methods to get weather, forecast, and other data from
 * OpenWeatherMap.org
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2013/08/05
 * @since 2.5.0.1
 */
public class OpenWeatherMap {

    public static enum OWM_URL {
        // Base URL for OpenWeatherMap.org API 2.5

        BASE_URL("http://api.openweathermap.org/data/2.5/"),
        // Parameters

        PARAMETER_CURRENT_WEATHER("weather?"),
        PARAMETER_FORECAST_WEATHER("forecast?"),
        PARAMETER_DAILY_FORECAST("forecast/daily?"),
        PARAMETER_SEARCH_CITY("find?"),
        PARAMETER_COUNT("cnt="),
        PARAMETER_CITY_NAME("q="),
        PARAMETER_CITY_ID("id="),
        PARAMETER_LATITUDE("lat="),
        PARAMETER_LONGITUDE("lon="),
        PARAMETER_MODE("mode="),
        PARAMETER_UNITS("units="),
        PARAMETER_APPID("APPID="),
        // Possible values of parameters

        PARAMETER_MODE_VALUE_JSON("json"),
        PARAMETER_UNITS_VALUE_IMPERIAL("imperial"),
        PARAMETER_UNITS_VALUE_METRIC("metric");

        private final String value;

        private OWM_URL(String val) {
            this.value = val;
        }

        /**
         * Returns the parameter.
         * <p>
         * @return Parameter
         */
        public String getParameter() {
            return this.value;
        }
    }

    public static class OWM_Response {

        private final String OWM_MODE_VALUE;
        private final String OWM_UNITS_VALUE;
        private final String OWM_APPID_VALUE;

        public OWM_Response(String apiKey) {
            this(OWM_URL.PARAMETER_UNITS_VALUE_IMPERIAL, apiKey);
        }

        public OWM_Response(OWM_URL unit, String apiKey) {
            this.OWM_MODE_VALUE = OWM_URL.PARAMETER_MODE_VALUE_JSON.getParameter();
            this.OWM_UNITS_VALUE = unit.getParameter();
            this.OWM_APPID_VALUE = apiKey;
        }

        public String currentWeatherByCityName(String cityName)
                throws MalformedURLException, IOException {
            String response;

            String address = OWM_URL.BASE_URL.getParameter()
                             + OWM_URL.PARAMETER_CURRENT_WEATHER.getParameter()
                             + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                             + URLEncoder.encode(cityName, "US-ASCII") + "&"
                             + OWM_URL.PARAMETER_MODE.getParameter()
                             + this.OWM_MODE_VALUE + "&"
                             + OWM_URL.PARAMETER_UNITS.getParameter()
                             + this.OWM_UNITS_VALUE + "&"
                             + OWM_URL.PARAMETER_APPID.getParameter()
                             + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String currentWeatherByCityName(String cityName, String countryCode)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_CURRENT_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                      + URLEncoder.encode(cityName, "US-ASCII")
                      + "," + countryCode + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String currentWeatherByCityCode(long cityCode)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_CURRENT_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_CITY_ID.getParameter()
                      + Long.toString(cityCode) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String currentWeatherByCoordinates(float latitude, float longitude)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_CURRENT_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_LATITUDE.getParameter()
                      + Float.toString(latitude) + "&"
                      + OWM_URL.PARAMETER_LONGITUDE.getParameter()
                      + Float.toString(longitude) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String forecastWeatherByCityName(String cityName)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_FORECAST_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                      + URLEncoder.encode(cityName, "US-ASCII") + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String forecastWeatherByCityName(String cityName, String countryCode)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_FORECAST_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                      + URLEncoder.encode(cityName, "US-ASCII")
                      + "," + countryCode + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String forecastWeatherByCityCode(long cityCode)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_FORECAST_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_CITY_ID.getParameter()
                      + Long.toString(cityCode) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String forecastWeatherByCoordinates(float latitude, float longitude)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_FORECAST_WEATHER.getParameter()
                      + OWM_URL.PARAMETER_LATITUDE.getParameter()
                      + Float.toString(latitude) + "&"
                      + OWM_URL.PARAMETER_LONGITUDE.getParameter()
                      + Float.toString(longitude) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String dailyForecastByCityName(String cityName, byte count)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_DAILY_FORECAST.getParameter()
                      + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                      + URLEncoder.encode(cityName, "US-ASCII") + "&"
                      + OWM_URL.PARAMETER_COUNT.getParameter()
                      + Byte.toString(count) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String dailyForecastByCityName(String cityName, String countryCode, byte count)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_DAILY_FORECAST.getParameter()
                      + OWM_URL.PARAMETER_CITY_NAME.getParameter()
                      + URLEncoder.encode(cityName, "US-ASCII")
                      + "," + countryCode + "&"
                      + OWM_URL.PARAMETER_COUNT.getParameter()
                      + Byte.toString(count) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String dailyForecastByCityCode(long cityCode, byte count)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_DAILY_FORECAST.getParameter()
                      + OWM_URL.PARAMETER_CITY_ID.getParameter()
                      + Long.toString(cityCode) + "&"
                      + OWM_URL.PARAMETER_COUNT.getParameter()
                      + Byte.toString(count) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }

        public String dailyForecastByCoordinates(float latitude, float longitude, byte count)
                throws MalformedURLException, IOException {
            String address;
            String response;

            address = OWM_URL.BASE_URL.getParameter()
                      + OWM_URL.PARAMETER_DAILY_FORECAST.getParameter()
                      + OWM_URL.PARAMETER_LATITUDE.getParameter()
                      + Float.toString(latitude) + "&"
                      + OWM_URL.PARAMETER_COUNT.getParameter()
                      + Byte.toString(count) + "&"
                      + OWM_URL.PARAMETER_LONGITUDE.getParameter()
                      + Float.toString(longitude) + "&"
                      + OWM_URL.PARAMETER_MODE.getParameter()
                      + this.OWM_MODE_VALUE + "&"
                      + OWM_URL.PARAMETER_UNITS.getParameter()
                      + this.OWM_UNITS_VALUE + "&"
                      + OWM_URL.PARAMETER_APPID.getParameter()
                      + this.OWM_APPID_VALUE;

            response = Tools.Downloader.downloadPage(address);

            return response;
        }
    }

    /**
     * *********************
     * Declaring this class *********************
     */
    private final OWM_Response owmResponse;

    public OpenWeatherMap() {
        this(OWM_URL.PARAMETER_UNITS_VALUE_METRIC, "");
    }

    public OpenWeatherMap(OWM_URL unit, String apiKey) {
        this.owmResponse = new OWM_Response(unit, apiKey);
    }

    public CurrentWeatherData currentWeatherByCityName(String cityName)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        CurrentWeatherData cwd;

        jsonResponse = owmResponse.currentWeatherByCityName(cityName);
        cwd = this.currentWeatherFromResponse(jsonResponse);

        return cwd;
    }

    public CurrentWeatherData currentWeatherByCityName(String cityName, String countryCode)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        CurrentWeatherData cwd;

        jsonResponse = owmResponse.currentWeatherByCityName(cityName, countryCode);
        cwd = this.currentWeatherFromResponse(jsonResponse);

        return cwd;
    }

    public CurrentWeatherData currentWeatherByCityCode(long cityCode)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        CurrentWeatherData cwd;

        jsonResponse = owmResponse.currentWeatherByCityCode(cityCode);
        cwd = this.currentWeatherFromResponse(jsonResponse);

        return cwd;
    }

    public CurrentWeatherData currentWeatherByCoordinates(float latitude, float longitude)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        CurrentWeatherData cwd;

        jsonResponse = owmResponse.currentWeatherByCoordinates(latitude, longitude);
        cwd = this.currentWeatherFromResponse(jsonResponse);

        return cwd;
    }

    public CurrentWeatherData currentWeatherFromResponse(String jsonResponse)
            throws MalformedURLException, IOException, JSONException {
        JSONObject jsonObj;
        CurrentWeatherData cwd;

        jsonObj = (jsonResponse != null) ? new JSONObject(jsonResponse) : null;
        cwd = new CurrentWeatherData(jsonObj);

        return cwd;
    }

    public ForecastWeatherData forecastWeatherByCityName(String cityName)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        ForecastWeatherData fwd;

        jsonResponse = owmResponse.forecastWeatherByCityName(cityName);
        fwd = this.forecastWeatherFromResponse(jsonResponse);

        return fwd;
    }

    public ForecastWeatherData forecastWeatherByCityName(String cityName, String countryCode)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        ForecastWeatherData fwd;

        jsonResponse = owmResponse.forecastWeatherByCityName(cityName, countryCode);
        fwd = this.forecastWeatherFromResponse(jsonResponse);

        return fwd;
    }

    public ForecastWeatherData forecastWeatherByCityCode(long cityCode)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        ForecastWeatherData fwd;

        jsonResponse = owmResponse.forecastWeatherByCityCode(cityCode);
        fwd = this.forecastWeatherFromResponse(jsonResponse);

        return fwd;
    }

    public ForecastWeatherData forecastWeatherByCoordinates(float latitude, float longitude)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        ForecastWeatherData fwd;

        jsonResponse = owmResponse.forecastWeatherByCoordinates(latitude, longitude);
        fwd = this.forecastWeatherFromResponse(jsonResponse);

        return fwd;
    }

    public ForecastWeatherData forecastWeatherFromResponse(String jsonResponse)
            throws MalformedURLException, IOException, JSONException {
        JSONObject jsonObj;
        ForecastWeatherData fwd;

        jsonObj = (jsonResponse != null) ? new JSONObject(jsonResponse) : null;
        fwd = new ForecastWeatherData(jsonObj);

        return fwd;
    }

    public DailyForecastData dailyForecastByCityName(String cityName, byte count)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        DailyForecastData dfd;

        jsonResponse = owmResponse.dailyForecastByCityName(cityName, count);
        dfd = this.dailyForecastFromResponse(jsonResponse);

        return dfd;
    }

    public DailyForecastData dailyForecastByCityName(String cityName, String countryCode, byte count)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        DailyForecastData dfd;

        jsonResponse = owmResponse.dailyForecastByCityName(cityName, countryCode, count);
        dfd = this.dailyForecastFromResponse(jsonResponse);

        return dfd;
    }

    public DailyForecastData dailyForecastByCityCode(long cityCode, byte count)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        DailyForecastData dfd;

        jsonResponse = owmResponse.dailyForecastByCityCode(cityCode, count);
        dfd = this.dailyForecastFromResponse(jsonResponse);

        return dfd;
    }

    public DailyForecastData dailyForecastByCoordinates(float latitude, float longitude, byte count)
            throws MalformedURLException, IOException, JSONException {
        String jsonResponse;
        DailyForecastData dfd;

        jsonResponse = owmResponse.dailyForecastByCoordinates(latitude, longitude, count);
        dfd = this.dailyForecastFromResponse(jsonResponse);

        return dfd;
    }

    public DailyForecastData dailyForecastFromResponse(String jsonResponse)
            throws MalformedURLException, IOException, JSONException {
        JSONObject jsonObj;
        DailyForecastData dfd;

        jsonObj = (jsonResponse != null) ? new JSONObject(jsonResponse) : null;
        dfd = new DailyForecastData(jsonObj);

        return dfd;
    }
}
