/*
 * This source code is part of the research paper
 * "Applying Model-View-Presenter Architecture to the Android Framework with App Prototype"
 *
 * Software Architecture and Management,
 * Herman-Hollerith-Zentrum, Reutlingen University
 *
 * Authors:  Dennis G. Geisse,
 *           Iven John,
 *           Daniel Joos,
 *           Sebastian Kotstein,
 *           Michael Wurster
 *
 * Year:     2015
 */

package inf.msc.yawapp.model;

public interface WeatherData {
    public enum Condition {
        NOT_AVAILABLE, CLEAR, FEW_CLOUDS, SCATTERED_CLOUDS, BROKEN_CLOUDS,
        SHOWER_RAIN, RAIN, THUNDERSTORM, SNOW, MIST
    }

    public Location getLocation();

    public float getCurrentTemperature();

    public float getHumidity();

    public float getAtmosphericPressure();

    public Condition getCondition();

    public boolean isDay();

    public float getWindDirection();

    public float getWindSpeed();
}
