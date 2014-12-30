package inf.msc.yawapp.model;

import net.aksingh.java.api.owm.CurrentWeatherData;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenWeatherMapWeatherData implements WeatherData {

    private final CurrentWeatherData data;
    private Condition condition;
    private boolean daylight;

    private final Map<String, Condition> conditionMap;

    {
        conditionMap = new HashMap<>();
        conditionMap.put("01", Condition.CLEAR);
        conditionMap.put("02", Condition.FEW_CLOUDS);
        conditionMap.put("03", Condition.SCATTERED_CLOUDS);
        conditionMap.put("04", Condition.BROKEN_CLOUDS);
        conditionMap.put("09", Condition.SHOWER_RAIN);
        conditionMap.put("10", Condition.RAIN);
        conditionMap.put("11", Condition.THUNDERSTORM);
        conditionMap.put("13", Condition.SNOW);
        conditionMap.put("50", Condition.MIST);
    }

    public OpenWeatherMapWeatherData(final CurrentWeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.data = data;
        determineCondition();
    }

    private void determineCondition() {
        condition = Condition.NOT_AVAILABLE;
        if (data.getWeatherListCount() > 0) {
            String icon = data.getWeatherList().get(0).getWeatherIconName();
            Pattern p = Pattern.compile("([0-9]{2})([dn])");
            Matcher m = p.matcher(icon);
            if (m.matches() && m.groupCount() > 0) {
                if (conditionMap.containsKey(m.group(1))) {
                    condition = conditionMap.get(m.group(1));
                }
                daylight = m.group(2).equals("d");
            }
        }
    }

    @Override
    public String getCityName() {
        return data.getCityName();
    }

    @Override
    public float getCurrentTemperature() {
        return data.getMainObject().getTemperature();
    }

    @Override
    public float getMinTemperature() {
        return data.getMainObject().getMinTemperature();
    }

    @Override
    public float getMaxTemperature() {
        return data.getMainObject().getMaxTemperature();
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
