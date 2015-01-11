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

import net.aksingh.java.api.owm.CurrentWeatherData;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenWeatherMapWeatherData implements WeatherData {

    private final CurrentWeatherData data;
    private Location location;
    private Condition condition;
    private boolean daylight;

    private static final Map<String, Condition> CONDITION_MAP;

    static {
        CONDITION_MAP = new HashMap<>();
        CONDITION_MAP.put("01", Condition.CLEAR);
        CONDITION_MAP.put("02", Condition.FEW_CLOUDS);
        CONDITION_MAP.put("03", Condition.SCATTERED_CLOUDS);
        CONDITION_MAP.put("04", Condition.BROKEN_CLOUDS);
        CONDITION_MAP.put("09", Condition.SHOWER_RAIN);
        CONDITION_MAP.put("10", Condition.RAIN);
        CONDITION_MAP.put("11", Condition.THUNDERSTORM);
        CONDITION_MAP.put("13", Condition.SNOW);
        CONDITION_MAP.put("50", Condition.MIST);
    }

    public OpenWeatherMapWeatherData(final CurrentWeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.data = data;
        this.location = new Location() {
            @Override
            public long getId() {
                return data.getCityCode();
            }

            @Override
            public String getCity() {
                return data.getCityName();
            }

            @Override
            public String getCountry() {
                return data.getSysDataObject().getCountryCode();
            }

            @Override
            public float getLatitude() {
                return data.getCoordObject().getLatitude();
            }

            @Override
            public float getLongitude() {
                return data.getCoordObject().getLongitude();
            }
        };
        determineCondition();
    }

    private void determineCondition() {
        condition = Condition.NOT_AVAILABLE;
        if (data.getWeatherListCount() > 0) {
            String icon = data.getWeatherList().get(0).getWeatherIconName();
            Pattern p = Pattern.compile("([0-9]{2})([dn])");
            Matcher m = p.matcher(icon);
            if (m.matches() && m.groupCount() > 0) {
                if (CONDITION_MAP.containsKey(m.group(1))) {
                    condition = CONDITION_MAP.get(m.group(1));
                }
                daylight = m.group(2).equals("d");
            }
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public float getCurrentTemperature() {
        return data.getMainObject().getTemperature();
    }

    @Override
    public float getHumidity() {
        return data.getMainObject().getHumidity();
    }

    @Override
    public float getAtmosphericPressure() {
        return data.getMainObject().getPressure();
    }

    @Override
    public Condition getCondition() {
        return condition;
    }

    @Override
    public boolean isDay() {
        return daylight;
    }

    @Override
    public float getWindDirection() {
        return data.getWindObject().getWindDegree();
    }

    @Override
    public float getWindSpeed() {
        return data.getWindObject().getWindSpeed();
    }
}
