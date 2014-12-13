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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses current weather data (from the JSON data) and provides methods to
 * get/access the information about current weather. This class provides
 * <code>has</code> and <code>get</code> methods to access the information.
 * <p>
 * <p>
 * <code>has</code> methods can be used to check if the data exists, i.e., if
 * the data was available (successfully downloaded) and was parsed correctly.
 * <p>
 * <p>
 * <code>get</code> methods can be used to access the data, if the data exists,
 * otherwise <code>get</code> methods will give value as per following
 * basis:<br>
 * Boolean: <code>false</code><br>
 * Integral: Minimum value (MIN_VALUE)<br>
 * Floating point: Not a number (NaN)<br>
 * Others: <code>null</code><br>
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2013/08/07
 * @since 2.5.0.1
 */
public class CurrentWeatherData extends AbstractWeatherData {

    /**
     * Key for JSON object - Rain
     */
    private final String JSON_RAIN = "rain";
    /**
     * Key for JSON object - Sys
     */
    private final String JSON_SYS = "sys";

    /*
     ************************
     * Declaring sub-classes
     ************************
     */
    /**
     * Parses data about clouds (from the JSON data) and provides methods to
     * get/access the information. This class provides <code>has</code> and
     * <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Clouds extends AbstractWeatherData.Clouds {

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Clouds() {
            super();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about clouds
         */
        public Clouds(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    /**
     * Parses data about geographic coordinates (from the JSON data) and
     * provides methods to get/access the information. This class provides
     * <code>has</code> and <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Coord extends AbstractWeatherData.Coord {

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Coord() {
            super();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about coordinates
         */
        public Coord(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    /**
     * Parses data about main weather elements (from the JSON data) and provides
     * methods to get/access the information. This class provides
     * <code>has</code> and <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Main extends AbstractWeatherData.Main {

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Main() {
            super();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about main weather
         *                elements (temperature, pressure, etc.)
         */
        public Main(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    /**
     * Parses data about rain (from the JSON data) and provides methods to
     * get/access the information. This class provides <code>has</code> and
     * <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Rain {

        /**
         * Key for JSON variable <code>Rain -> Rain per 3 hours</code>
         */
        private final String JSON_RAIN_3HOURS = "3h";

        /**
         * Rain per 3 hours
         */
        private final float rain3h;

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Rain() {
            this.rain3h = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about rain
         */
        public Rain(JSONObject jsonObj) {
            this.rain3h = (float) jsonObj.optDouble(this.JSON_RAIN_3HOURS, Double.NaN);
        }

        public boolean hasRain3Hours() {
            return (this.rain3h != Float.NaN);
        }

        public float getRain3Hours() {
            return this.rain3h;
        }
    }

    /**
     * Parses data about country, sunrise, and sunset (from the JSON data) and
     * provides methods to get/access the information. This class provides
     * <code>has</code> and <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Sys {

        /**
         * Key for JSON variable <code>Sys -> Country</code>
         */
        private final String JSON_SYS_COUNTRY_CODE = "country";
        /**
         * Key for JSON variable <code>Sys -> Sunrise</code>
         */
        private final String JSON_SYS_SUNRISE = "sunrise";
        /**
         * Key for JSON variable <code>Sys -> Sunset</code>
         */
        private final String JSON_SYS_SUNSET = "sunset";

        /**
         * Country code for the city
         */
        private final String countryCode;
        /**
         * Sunrise time
         */
        private final Date sunrise;
        /**
         * Sunset time
         */
        private final Date sunset;

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Sys() {
            this.countryCode = null;
            this.sunrise = null;
            this.sunset = null;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about country, sunrise,
         *                and sunset.
         */
        public Sys(JSONObject jsonObj) {
            this.countryCode = jsonObj.optString(this.JSON_SYS_COUNTRY_CODE, null);

            long sr_secs = jsonObj.optLong(this.JSON_SYS_SUNRISE, Long.MIN_VALUE);
            if (sr_secs != Long.MIN_VALUE) {
                /*
                 @bugfix Incorrect date and time
                 Issue: #3 given at http://code.aksingh.net/owm-japis/issue/3/problem-with-datetime
                 Incorrect: this.sunrise = new Date(sr_secs);
                 Correct: this.sunrise = new Date(sr_secs * 1000);
                 Reason: Date requires milliseconds but previously, seconds were provided.
                 */
                this.sunrise = new Date(sr_secs * 1000);
            } else {
                this.sunrise = null;
            }

            long ss_secs = jsonObj.optLong(this.JSON_SYS_SUNSET, Long.MIN_VALUE);
            if (ss_secs != Long.MIN_VALUE) {
                /*
                 @bugfix Incorrect date and time
                 Issue: #3 given at http://code.aksingh.net/owm-japis/issue/3/problem-with-datetime
                 Incorrect: this.sunrise = new Date(ss_secs);
                 Correct: this.sunrise = new Date(ss_secs * 1000);
                 Reason: Date requires milliseconds but previously, seconds were provided.
                 */
                this.sunset = new Date(ss_secs * 1000);
            } else {
                this.sunset = null;
            }
        }

        public boolean hasCountryCode() {
            return (this.countryCode != null);
        }

        public boolean hasSunriseTime() {
            return (this.sunrise != null);
        }

        public boolean hasSunsetTime() {
            return (this.sunset != null);
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public Date getSunriseTime() {
            return this.sunrise;
        }

        public Date getSunsetTime() {
            return this.sunset;
        }
    }

    /**
     * Parses data about weather code, name, etc. (from the JSON data) and
     * provides methods to get/access the information. This class provides
     * <code>has</code> and <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Weather extends AbstractWeatherData.Weather {

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Weather() {
            super();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about weather id, name,
         *                etc.
         */
        public Weather(JSONObject jsonObj) {
            super(jsonObj);
        }
    }

    /**
     * Parses data about winds (from the JSON data) and provides methods to
     * get/access the information. This class provides <code>has</code> and
     * <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Wind extends AbstractWeatherData.Wind {

        /**
         * Key for JSON variable <code>Wind -> Gust</code>
         */
        private final String JSON_WIND_GUST = "gust";

        /**
         * Wind gust
         */
        private final float gust;

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Wind() {
            super();

            this.gust = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about wind
         */
        public Wind(JSONObject jsonObj) {
            super(jsonObj);

            this.gust = (float) jsonObj.optDouble(this.JSON_WIND_GUST, Double.NaN);
        }

        public boolean hasWindGust() {
            return (this.gust != Float.NaN);
        }

        public float getWindGust() {
            return this.gust;
        }
    }

    /*
     ***********************
     * Declaring this class
     ***********************
     */
    /**
     * Key for JSON variable <code>Base</code>
     */
    private final String JSON_BASE = "base";
    /**
     * Key for JSON variable <code>City code (ID)</code>
     */
    private final String JSON_CITY_ID = "id";
    /**
     * Key for JSON variable <code>City name</code>
     */
    private final String JSON_CITY_NAME = "name";
    /**
     * Key for JSON variable <code>Response code</code>
     */
    private final String JSON_RESPONSE_CODE = "cod";

    /**
     * Base
     */
    private final String base;
    /**
     * City code (ID)
     */
    private final long cityID;
    /**
     * City name
     */
    private final String cityName;
    /**
     * Response code
     */
    private final int responseCode;

    private final Clouds clouds;
    private final Coord coord;
    private final Main main;
    private final Rain rain;
    private final Sys sys;
    private final Wind wind;

    /**
     * List of weather information (code, name, etc.)
     */
    private final List<Weather> weatherList;
    /**
     * Count (number) of elements in list of weather information
     */
    private final int weatherListCount;

    /**
     * Parameterized constructor
     * <p>
     * Initializes variables from values from the given JSON object.
     * <p>
     * @param jsonObj JSON object containing current weather data
     */
    public CurrentWeatherData(JSONObject jsonObj) {
        super(jsonObj);

        this.base = (jsonObj != null) ? jsonObj.optString(this.JSON_BASE, null) : null;
        this.cityID = (jsonObj != null) ? jsonObj.optLong(this.JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
        this.cityName = (jsonObj != null) ? jsonObj.optString(this.JSON_CITY_NAME, null) : null;
        this.responseCode = (jsonObj != null) ? jsonObj.optInt(this.JSON_RESPONSE_CODE, Integer.MIN_VALUE) : Integer.MIN_VALUE;

        JSONObject jsonObjClouds = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CLOUDS) : null;
        this.clouds = (jsonObjClouds != null) ? new Clouds(jsonObjClouds) : new Clouds();

        JSONObject jsonObjCoord = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_COORD) : null;
        this.coord = (jsonObjCoord != null) ? new Coord(jsonObjCoord) : new Coord();

        JSONObject jsonObjMain = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_MAIN) : null;
        this.main = (jsonObjMain != null) ? new Main(jsonObjMain) : new Main();

        JSONObject jsonObjRain = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_RAIN) : null;
        this.rain = (jsonObjRain != null) ? new Rain(jsonObjRain) : new Rain();

        JSONObject jsonObjSys = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_SYS) : null;
        this.sys = (jsonObjSys != null) ? new Sys(jsonObjSys) : new Sys();

        JSONArray jsonArrWeather = (jsonObj != null) ? jsonObj.optJSONArray(this.JSON_WEATHER) : null;
        this.weatherList = (jsonArrWeather != null) ? new ArrayList<Weather>(jsonArrWeather.length()) : Collections.EMPTY_LIST;
        if (this.weatherList != Collections.EMPTY_LIST) {
            for (int i = 0; i < jsonArrWeather.length(); i++) {
                JSONObject jsonObjWeather = jsonArrWeather.optJSONObject(i);
                if (jsonObjWeather != null) {
                    this.weatherList.add(new Weather(jsonObjWeather));
                }
            }
        }
        this.weatherListCount = this.weatherList.size();

        JSONObject jsonObjWind = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_WIND) : null;
        this.wind = (jsonObjWind != null) ? new Wind(jsonObjWind) : new Wind();
    }

    public boolean hasBaseStation() {
        return (this.base != null);
    }

    public boolean hasCityCode() {
        return (this.cityID != Long.MIN_VALUE);
    }

    public boolean hasCityName() {
        return (this.cityName != null);
    }

    public boolean hasResponseCode() {
        return (this.responseCode != Integer.MIN_VALUE);
    }

    public String getBaseStation() {
        return this.base;
    }

    public long getCityCode() {
        return this.cityID;
    }

    public String getCityName() {
        return this.cityName;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    // Objects
    public Clouds getClouds_Object() {
        return this.clouds;
    }

    public Coord getCoordinates_Object() {
        return this.coord;
    }

    public Main getMainData_Object() {
        return this.main;
    }

    public Rain getRain_Object() {
        return this.rain;
    }

    public Sys getSysData_Object() {
        return this.sys;
    }

    public Wind getWind_Object() {
        return this.wind;
    }

    // Lists
    public boolean hasWeather_List() {
        return (this.weatherListCount != 0);
    }

    public int getWeather_List_Count() {
        return this.weatherListCount;
    }

    public List<Weather> getWeather_List() {
        return this.weatherList;
    }
}
