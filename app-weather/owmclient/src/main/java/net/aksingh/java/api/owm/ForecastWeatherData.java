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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses forecast weather data (from the JSON data) and provides methods to
 * get/access the information about forecasted weather. This class provides
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
 * Objects: Data initialized with default/non-parameterized constructor<br>
 * Others: <code>null</code><br>
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2013/08/07
 * @since 2.5.0.1
 */
public class ForecastWeatherData {

    /**
     * Key for JSON object - City
     */
    private final String JSON_CITY = "city";
    /**
     * Key for JSON object - List of forecasts
     */
    private final String JSON_FORECAST_LIST = "list";

    /*
     ************************
     * Declaring sub-classes
     ************************
     */
    /**
     * Parses data about city (from the JSON data) and provides methods to
     * get/access the information. For example, city name, coordinates, country
     * name, population, etc. This class provides <code>has</code> and
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
    public static class City {

        /**
         * Key for JSON object - Coordinates
         */
        private final String JSON_CITY_COORD = "coord";

        /**
         * Parses data about geographic coordinates (from the JSON data) and
         * provides methods to get/access the information. This class provides
         * <code>has</code> and <code>get</code> methods to access the
         * information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
             * @param jsonObj JSON object containing data about clouds
             */
            public Coord(JSONObject jsonObj) {
                super(jsonObj);
            }
        }

        /**
         * Key for JSON variable <code>City code (ID)</code>
         */
        private final String JSON_CITY_ID = "id";
        /**
         * Key for JSON variable <code>City name</code>
         */
        private final String JSON_CITY_NAME = "name";
        /**
         * Key for JSON variable <code>Country code of city</code>
         */
        private final String JSON_CITY_COUNTRY_CODE = "country";
        /**
         * Key for JSON variable <code>Population of city</code>
         */
        private final String JSON_CITY_POPULATION = "population";

        /**
         * City code (ID)
         */
        private final long cityID;
        /**
         * City name
         */
        private final String cityName;
        /**
         * Country code of city
         */
        private final String countryCode;
        /**
         * Population of city
         */
        private final long population;

        private final Coord coord;

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
        public City() {
            this.cityID = Long.MIN_VALUE;
            this.cityName = null;
            this.countryCode = null;
            this.population = Long.MIN_VALUE;

            this.coord = new Coord();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about city
         */
        public City(JSONObject jsonObj) {
            this.cityID = (jsonObj != null) ? jsonObj.optLong(this.JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
            this.cityName = (jsonObj != null) ? jsonObj.optString(this.JSON_CITY_NAME, null) : null;
            this.countryCode = (jsonObj != null) ? jsonObj.optString(this.JSON_CITY_COUNTRY_CODE, null) : null;
            this.population = (jsonObj != null) ? jsonObj.optLong(this.JSON_CITY_POPULATION, Long.MIN_VALUE) : Long.MIN_VALUE;

            JSONObject jsonObjCoord = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CITY_COORD) : null;
            this.coord = (jsonObjCoord != null) ? new Coord(jsonObjCoord) : new Coord();
        }

        public boolean hasCityCode() {
            return (this.cityID != Long.MIN_VALUE);
        }

        public boolean hasCityName() {
            return (this.cityName != null);
        }

        public boolean hasCountryCode() {
            return (this.countryCode != null);
        }

        public boolean hasCityPopulation() {
            return (this.population != Long.MIN_VALUE);
        }

        public long getCityCode() {
            return this.cityID;
        }

        public String getCityName() {
            return this.cityName;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public long getCityPopulation() {
            return this.population;
        }

        // Objects
        public Coord getCoordinates_Object() {
            return this.coord;
        }
    }

    /**
     * Parses data about forecasts (from the JSON data) and provides methods to
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
    public static class Forecast extends AbstractWeatherData {

        /**
         * Key for JSON object - Sys (pod, etc.)
         */
        private final String JSON_SYS = "sys";

        /*
         ***************************
         * Declaring sub-sub-classes
         ***************************
         */
        /**
         * Parses data about clouds (from the JSON data) and provides methods to
         * get/access the information. This class provides <code>has</code> and
         * <code>get</code> methods to access the information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
         * Parses data about main weather elements (from the JSON data) and
         * provides methods to get/access the information. For example,
         * temperature, pressure, sea level, etc. This class provides
         * <code>has</code> and <code>get</code> methods to access the
         * information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
             * Key for JSON variable <code>Main -> Sea level</code>
             */
            private final String JSON_MAIN_SEA_LEVEL = "sea_level";
            /**
             * Key for JSON variable <code>Main -> Ground level</code>
             */
            private final String JSON_MAIN_GRND_LEVEL = "grnd_level";
            /**
             * Key for JSON variable <code>Main -> Temperature KF</code>
             */
            private final String JSON_MAIN_TMP_KF = "temp_kf";

            /**
             * Sea level
             */
            private final float seaLevel;
            /**
             * Ground level
             */
            private final float groundLevel;
            /**
             * Temperature KF
             */
            private final float tempKF;

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

                this.seaLevel = Float.NaN;
                this.groundLevel = Float.NaN;
                this.tempKF = Float.NaN;
            }

            /**
             * Parameterized constructor
             * <p>
             * Initializes variables from values from the given JSON object.
             * <p>
             * @param jsonObj JSON object containing data about main weather
             *                elements
             */
            public Main(JSONObject jsonObj) {
                super(jsonObj);

                this.seaLevel = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_MAIN_SEA_LEVEL, Float.NaN) : Float.NaN;
                this.groundLevel = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_MAIN_GRND_LEVEL, Float.NaN) : Float.NaN;
                this.tempKF = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_MAIN_TMP_KF, Float.NaN) : Float.NaN;
            }

            public boolean hasSeaLevel() {
                return (this.seaLevel != Float.NaN);
            }

            public boolean hasGroundLevel() {
                return (this.groundLevel != Float.NaN);
            }

            public boolean hasTempKF() {
                return (this.tempKF != Float.NaN);
            }

            public float getSeaLevel() {
                return this.seaLevel;
            }

            public float getGroundLevel() {
                return this.groundLevel;
            }

            public float getTempKF() {
                return this.tempKF;
            }
        }

        /**
         * Parses data about main weather elements (from the JSON data) and
         * provides methods to get/access the information. For example,
         * temperature, pressure, sea level, etc. This class provides
         * <code>has</code> and <code>get</code> methods to access the
         * information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
             * Key for JSON variable <code>Sys -> Pod</code>
             */
            private final String JSON_SYS_POD = "pod";

            /**
             * Pod
             */
            private final String pod;

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
                this.pod = null;
            }

            /**
             * Parameterized constructor
             * <p>
             * Initializes variables from values from the given JSON object.
             * <p>
             * @param jsonObj JSON object containing data about sys. For
             *                example, pod, etc.
             */
            public Sys(JSONObject jsonObj) {
                this.pod = (jsonObj != null) ? jsonObj.optString(this.JSON_SYS_POD, null) : null;
            }

            public boolean hasPod() {
                return (this.pod != null);
            }

            public String getPod() {
                return this.pod;
            }
        }

        /**
         * Parses data about weather (from the JSON data) and provides methods
         * to get/access the information. For example, weather id, name, etc.
         * This class provides <code>has</code> and <code>get</code> methods to
         * access the information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
             * @param jsonObj JSON object containing data about weather code,
             *                name, etc.
             */
            public Weather(JSONObject jsonObj) {
                super(jsonObj);
            }
        }

        /**
         * Parses data about wind (from the JSON data) and provides methods to
         * get/access the information. This class provides <code>has</code> and
         * <code>get</code> methods to access the information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
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
            }
        }

        /*
         ************************
         * Declaring this sub-class
         ************************
         */
        /**
         * Key for JSON variable <code>Date time text</code>
         */
        private final String JSON_DATE_TIME_TEXT = "dt_txt";

        /**
         * Date time text
         */
        private final String dateTimeText;

        private final Clouds clouds;
        private final Main main;
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
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Forecast() {
            super();

            this.dateTimeText = null;

            this.clouds = new Clouds();
            this.main = new Main();
            this.sys = new Sys();
            this.weatherList = Collections.EMPTY_LIST;
            this.weatherListCount = this.weatherList.size();
            this.wind = new Wind();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about forecasts
         */
        public Forecast(JSONObject jsonObj) {
            super(jsonObj);

            this.dateTimeText = (jsonObj != null) ? jsonObj.optString(this.JSON_DATE_TIME_TEXT, null) : null;

            JSONObject jsonObjClouds = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CLOUDS) : null;
            this.clouds = (jsonObjClouds != null) ? new Clouds(jsonObjClouds) : new Clouds();

            JSONObject jsonObjMain = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_MAIN) : null;
            this.main = (jsonObjMain != null) ? new Main(jsonObjMain) : new Main();

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

        public boolean hasDateTimeText() {
            return (this.dateTimeText != null);
        }

        public String getDateTimeText() {
            return this.dateTimeText;
        }

        // Objects
        public Clouds getClouds_Object() {
            return this.clouds;
        }

        public Main getMainData_Object() {
            return this.main;
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

    /*
     ***********************
     * Declaring this class
     ***********************
     */
    /**
     * Key for JSON variable <code>Response code</code>
     */
    private final String JSON_RESPONSE_CODE = "cod";
    /**
     * Key for JSON variable <code>Response time</code>
     */
    private final String JSON_RESPONSE_TIME = "message";
    /**
     * Key for JSON variable <code>Forecast count</code>
     */
    private final String JSON_RESPONSE_FORECAST_COUNT = "cnt";

    /**
     * Response code
     */
    private final String responseCode;
    /**
     * Response time
     */
    private final float responseTime;
    /**
     * Forecast count
     */
    private final int responseForecastCount;

    private final City city;

    /**
     * List of forecast information
     */
    private final List<Forecast> forecastList;
    /**
     * Count (number) of elements in list of forecast information
     */
    private final int forecastListCount;

    /**
     * Parameterized constructor
     * <p>
     * Initializes variables from values from the given JSON object.
     * <p>
     * @param jsonObj JSON object containing data about clouds
     */
    public ForecastWeatherData(JSONObject jsonObj) {
        this.responseCode = (jsonObj != null) ? jsonObj.optString(this.JSON_RESPONSE_CODE, null) : null;
        this.responseTime = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_RESPONSE_TIME, Double.NaN) : Float.NaN;
        this.responseForecastCount = (jsonObj != null) ? jsonObj.optInt(this.JSON_RESPONSE_FORECAST_COUNT, Integer.MIN_VALUE) : Integer.MIN_VALUE;

        JSONObject jsonObjCity = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CITY) : null;
        this.city = (jsonObjCity != null) ? new City(jsonObjCity) : new City();

        JSONArray jsonArrForecast = (jsonObj != null) ? jsonObj.optJSONArray(this.JSON_FORECAST_LIST) : null;
        this.forecastList = (jsonArrForecast != null) ? new ArrayList<Forecast>(jsonArrForecast.length()) : Collections.EMPTY_LIST;
        if (this.forecastList != Collections.EMPTY_LIST) {
            for (int i = 0; i < jsonArrForecast.length(); i++) {
                JSONObject jsonObjWeather = jsonArrForecast.optJSONObject(i);
                if (jsonObjWeather != null) {
                    this.forecastList.add(new Forecast(jsonObjWeather));
                }
            }
        }
        this.forecastListCount = this.forecastList.size();
    }

    public boolean hasResponseCode() {
        return (this.responseCode != null);
    }

    public boolean hasResponseTime() {
        return (this.responseTime != Float.NaN);
    }

    public boolean hasResponseForecastCount() {
        return (this.responseForecastCount != Integer.MIN_VALUE);
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public float getResponseTime() {
        return this.responseTime;
    }

    public int getResponseForecastCount() {
        return this.responseForecastCount;
    }

    // Objects
    public City getCity_Object() {
        return this.city;
    }

    // Lists
    public boolean hasForecast_List() {
        return (this.forecastListCount != 0);
    }

    public int getForecast_List_Count() {
        return this.forecastListCount;
    }

    public List<Forecast> getForecast_List() {
        return this.forecastList;
    }
}
