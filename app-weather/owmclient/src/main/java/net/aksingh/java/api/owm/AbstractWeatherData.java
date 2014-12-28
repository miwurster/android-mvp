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

import java.util.Date;
import org.json.JSONObject;

/**
 * This class provides default implementations for
 * {@link CurrentWeatherData} and
 * {@link net.aksingh.java.api.owm.ForecastWeatherData} classes. Standard
 * behaviors like the <code>has</code> and the <code>get</code> methods for
 * information about weather or forecast (for example, temperature, pressure,
 * weather, clouds, wind, etc.) are defined here.
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2013/07/26
 * @since 2.5.0.1
 */
abstract public class AbstractWeatherData {

    /**
     * Key for JSON object - Clouds
     */
    protected final String JSON_CLOUDS = "clouds";
    /**
     * Key for JSON object - Coordinates (Geographic coordinates)
     */
    protected final String JSON_COORD = "coord";
    /**
     * Key for JSON object - Main (Temperature, pressure, etc.)
     */
    protected final String JSON_MAIN = "main";
    /**
     * Key for JSON array - Weather (Weather name, description, etc.)
     */
    protected final String JSON_WEATHER = "weather";
    /**
     * Key for JSON object - Wind
     */
    protected final String JSON_WIND = "wind";

    /*
     ************************
     * Defining sub-classes
     ************************
     */
    /**
     * This class provides default implementations for <code>Clouds</code>.
     * Standard behaviors like the <code>has</code> and the <code>get</code>
     * methods for information about clouds (for example, percentage of clouds,
     * etc.) are defined here.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/07/27
     * @since 2.5.0.1
     */
    abstract public static class Clouds {

        /**
         * Key for JSON variable <code>Clouds -> All</code> (percentage of all
         * clouds)
         */
        private final String JSON_CLOUDS_ALL = "all";

        /**
         * Percentage of all clouds
         */
        private final float percentOfClouds;

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
            this.percentOfClouds = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about clouds
         */
        public Clouds(JSONObject jsonObj) {
            this.percentOfClouds = (float) jsonObj.optDouble(this.JSON_CLOUDS_ALL, Double.NaN);
        }

        /**
         * Tells if the data for percentage of all clouds is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasPercentageOfClouds() {
            return (this.percentOfClouds != Float.NaN);
        }

        /**
         * Returns data for percentage of all clouds.
         * <p>
         * @return Percentage of all clouds if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getPercentageOfClouds() {
            return this.percentOfClouds;
        }
    }

    /**
     * This class provides default implementations for <code>Coord</code>.
     * Standard behaviors like the <code>has</code> and the <code>get</code>
     * methods for information about geographic coordinates (latitude and
     * longitude) are defined here.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/07/28
     */
    abstract public static class Coord {

        /**
         * Key for JSON variable <code>Coord -> Latitude</code>
         */
        private final String JSON_COORD_LATITUDE = "lat";
        /**
         * Key for JSON variable <code>Coord -> Longitude</code>
         */
        private final String JSON_COORD_LONGITUDE = "lon";

        /**
         * Latitude
         */
        private final float lat;
        /**
         * Longitude
         */
        private final float lon;

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
            this.lat = Float.NaN;
            this.lon = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about coordinates
         */
        public Coord(JSONObject jsonObj) {
            this.lat = (float) jsonObj.optDouble(this.JSON_COORD_LATITUDE, Double.NaN);
            this.lon = (float) jsonObj.optDouble(this.JSON_COORD_LONGITUDE, Double.NaN);
        }

        /**
         * Tells if the data for latitude of the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasLatitude() {
            return (this.lat != Float.NaN);
        }

        /**
         * Tells if the data for longitude of the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasLongitude() {
            return (this.lon != Float.NaN);
        }

        /**
         * Returns data for latitude of the city.
         * <p>
         * @return Latitude of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getLatitude() {
            return this.lat;
        }

        /**
         * Returns data for longitude of the city.
         * <p>
         * @return Longitude of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getLongitude() {
            return this.lon;
        }
    }

    /**
     * This class provides default implementations for <code>Main</code>.
     * Standard behaviors like the <code>has</code> and the <code>get</code>
     * methods for information about main weather elements (for example,
     * temperature, pressure, humidity, etc.) are defined here.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/07/28
     * @since 2.5.0.1
     */
    abstract public static class Main {

        /**
         * Key for JSON variable <code>Main -> Temperature</code>
         */
        private final String JSON_MAIN_TEMP = "temp";
        /**
         * Key for JSON variable <code>Main -> Minimum temperature</code>
         */
        private final String JSON_MAIN_TEMP_MIN = "temp_min";
        /**
         * Key for JSON variable <code>Main -> Maximum temperature</code>
         */
        private final String JSON_MAIN_TEMP_MAX = "temp_max";
        /**
         * Key for JSON variable <code>Main -> Pressure</code>
         */
        private final String JSON_MAIN_PRESSURE = "pressure";
        /**
         * Key for JSON variable <code>Main -> Humidity</code>
         */
        private final String JSON_MAIN_HUMIDITY = "humidity";

        /**
         * Temperature
         */
        private final float temp;
        /**
         * Minimum temperature
         */
        private final float minTemp;
        /**
         * Maximum temperature
         */
        private final float maxTemp;
        /**
         * Pressure
         */
        private final float pressure;
        /**
         * Humidity
         */
        private final float humidity;

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
            this.temp = Float.NaN;
            this.minTemp = Float.NaN;
            this.maxTemp = Float.NaN;
            this.pressure = Float.NaN;
            this.humidity = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about main weather
         *                elements. For example, temperature, pressure, etc.
         */
        public Main(JSONObject jsonObj) {
            this.temp = (float) jsonObj.optDouble(this.JSON_MAIN_TEMP, Double.NaN);
            this.minTemp = (float) jsonObj.optDouble(this.JSON_MAIN_TEMP_MIN, Double.NaN);
            this.maxTemp = (float) jsonObj.optDouble(this.JSON_MAIN_TEMP_MAX, Double.NaN);
            this.pressure = (float) jsonObj.optDouble(this.JSON_MAIN_PRESSURE, Double.NaN);
            this.humidity = (float) jsonObj.optDouble(this.JSON_MAIN_HUMIDITY, Double.NaN);
        }

        /**
         * Tells if the data for temperature of the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasTemperature() {
            return (this.temp != Float.NaN);
        }

        /**
         * Tells if the data for minimum temperature of the city is available or
         * not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasMinTemperature() {
            return (this.minTemp != Float.NaN);
        }

        /**
         * Tells if the data for maximum temperature of the city is available or
         * not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasMaxTemperature() {
            return (this.maxTemp != Float.NaN);
        }

        /**
         * Tells if the data for pressure of the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasPressure() {
            return (this.pressure != Float.NaN);
        }

        /**
         * Tells if the data for humidity of the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasHumidity() {
            return (this.humidity != Float.NaN);
        }

        /**
         * Returns data for temperature of the city.
         * <p>
         * @return Temperature of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getTemperature() {
            return this.temp;
        }

        /**
         * Returns data for minimum temperature of the city.
         * <p>
         * @return Minimum temperature of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getMinTemperature() {
            return this.minTemp;
        }

        /**
         * Returns data for maximum temperature of the city.
         * <p>
         * @return Maximum temperature of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getMaxTemperature() {
            return this.maxTemp;
        }

        /**
         * Returns data for pressure of the city.
         * <p>
         * @return Pressure of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getPressure() {
            return this.pressure;
        }

        /**
         * Returns data for humidity of the city.
         * <p>
         * @return Humidity of the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getHumidity() {
            return this.humidity;
        }
    }

    /**
     * This class provides default implementations for <code>Weather</code>.
     * Standard behaviors like the <code>has</code> and the <code>get</code>
     * methods for information about weather (for example, id, name,
     * description, icon, etc.) are defined here.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/07/28
     * @since 2.5.0.1
     */
    abstract public static class Weather {

        /**
         * Key for JSON variable in array <code>Weather -> ID</code>
         */
        private final String JSON_WEATHER_ID = "id";
        /**
         * Key for JSON variable in array
         * <code>Weather -> Main (name of weather)</code>
         */
        private final String JSON_WEATHER_MAIN = "main";
        /**
         * Key for JSON variable <code>Weather -> Description</code>
         */
        private final String JSON_WEATHER_DESCRIPTION = "description";
        /**
         * Key for JSON variable in array <code>Weather -> Icon</code>
         */
        private final String JSON_WEATHER_ICON = "icon";

        /**
         * Weather ID
         */
        private final int id;
        /**
         * Weather name
         */
        private final String name;
        /**
         * Weather description
         */
        private final String description;
        /**
         * Weather icon
         */
        private final String icon;

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
            this.id = Integer.MIN_VALUE;
            this.name = null;
            this.description = null;
            this.icon = null;
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
            this.id = jsonObj.optInt(this.JSON_WEATHER_ID, Integer.MIN_VALUE);
            this.name = jsonObj.optString(this.JSON_WEATHER_MAIN, null);
            this.description = jsonObj.optString(this.JSON_WEATHER_DESCRIPTION, null);
            this.icon = jsonObj.optString(this.JSON_WEATHER_ICON, null);
        }

        /**
         * Tells if the data for weather's code is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWeatherCode() {
            return (this.id != Integer.MIN_VALUE);
        }

        /**
         * Tells if the data for weather's name is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWeatherName() {
            return (this.name != null);
        }

        /**
         * Tells if the data for weather's description is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWeatherDescription() {
            return (this.description != null);
        }

        /**
         * Tells if the data for name of weather's icon is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWeatherIconName() {
            return (this.icon != null);
        }

        /**
         * Returns data for code for weather of the city.
         * <p>
         * @return Code for weather of the city if available, otherwise
         *         <code>Integer.MIN_VALUE</code>
         */
        public int getWeatherCode() {
            return this.id;
        }

        /**
         * Returns data for name for weather of the city.
         * <p>
         * @return Name for weather of the city if available, otherwise
         *         <code>null</code>
         */
        public String getWeatherName() {
            return this.name;
        }

        /**
         * Returns data for description for weather of the city.
         * <p>
         * @return Description for weather of the city if available, otherwise
         *         <code>null</code>
         */
        public String getWeatherDescription() {
            return this.description;
        }

        /**
         * Returns data for name of icon for weather of the city.
         * <p>
         * @return Name of icon for weather of the city if available, otherwise
         *         <code>null</code>
         */
        public String getWeatherIconName() {
            return this.icon;
        }
    }

    /**
     * This class provides default implementations for <code>Wind</code>.
     * Standard behaviors like the <code>has</code> and the <code>get</code>
     * methods for information about wind (for example, speed, degree, etc.) are
     * defined here.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/07/28
     * @since 2.5.0.1
     */
    abstract public static class Wind {

        /**
         * Key for JSON variable <code>Wind -> Speed</code>
         */
        private final String JSON_WIND_SPEED = "speed";
        /**
         * Key for JSON variable <code>Wind -> Degree</code>
         */
        private final String JSON_WIND_DEGREE = "deg";

        /**
         * Wind speed
         */
        private final float speed;
        /**
         * Wind degree (direction of wind)
         */
        private final float degree;

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
            this.speed = Float.NaN;
            this.degree = Float.NaN;
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about wind
         */
        public Wind(JSONObject jsonObj) {
            this.speed = (float) jsonObj.optDouble(this.JSON_WIND_SPEED, Double.NaN);
            this.degree = (float) jsonObj.optDouble(this.JSON_WIND_DEGREE, Double.NaN);
        }

        /**
         * Tells if the data for speed of wind in the city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWindSpeed() {
            return (this.speed != Float.NaN);
        }

        /**
         * Tells if the data for degree (degree gives direction) of wind in the
         * city is available or not.
         * <p>
         * @return <code>true</code> if data available, otherwise
         *         <code>false</code>
         */
        public boolean hasWindDegree() {
            return (this.hasWindSpeed() && (this.degree != Float.NaN));
        }

        /**
         * Returns data for speed of wind in the city.
         * <p>
         * @return Speed of wind in the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getWindSpeed() {
            return this.speed;
        }

        /**
         * Returns data for degree of wind in the city.
         * <p>
         * @return Degree of wind in the city if available, otherwise
         *         <code>Float.NaN</code>, i.e., Not a Number.
         */
        public float getWindDegree() {
            return this.degree;
        }
    }

    /*
     ***********************
     * Declaring this class
     ***********************
     */
    /**
     * Key for JSON variable Date-Time (date & time of the weather)
     */
    private final String JSON_DATE_TIME = "dt";

    /**
     * Date and time of the weather. This is answer for the question that when
     * is/will be this weather.
     */
    private final Date dateTime;

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
    public AbstractWeatherData() {
        this.dateTime = null;
    }

    /**
     * Parameterized constructor
     * <p>
     * Initializes variables from values from the given JSON object.
     * <p>
     * @param jsonObj JSON object containing weather data
     */
    public AbstractWeatherData(JSONObject jsonObj) {
        // getting seconds from the data
        long sec = (jsonObj != null) ? jsonObj.optLong(this.JSON_DATE_TIME, Long.MIN_VALUE) : Long.MIN_VALUE;

        // converting seconds to Date object
        if (sec != Long.MIN_VALUE) {
            /*
             @bugfix It always return "Sat Jan 17 04:10:42 CET 1970"
             Issue: #3 given at http://code.aksingh.net/owm-japis/issue/3/problem-with-datetime
             Incorrect: this.dateTime = new Date(sec);
             Correct: this.dateTime = new Date(sec * 1000);
             Reason: Date requires milliseconds but previously, seconds were provided.
             */
            this.dateTime = new Date(sec * 1000);
        } else {
            this.dateTime = null;
        }
    }

    /**
     * Tells if the data for date and time of this weather is available or not.
     * <p>
     * @return <code>true</code> if data available, otherwise <code>false</code>
     */
    public boolean hasDateTime() {
        return (this.dateTime != null);
    }

    /**
     * Returns data for date and time of this weather.
     * <p>
     * @return Date and time (in object of {@link java.util.Date}) if available,
     *         otherwise <code>null</code>
     */
    public Date getDateTime() {
        return this.dateTime;
    }
}
