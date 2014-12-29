package inf.msc.yawapp.model;

import net.aksingh.java.api.owm.CurrentWeatherData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenWeatherMapWeatherData implements WeatherData {

    private final CurrentWeatherData data;
    private Condition condition;

    public OpenWeatherMapWeatherData(final CurrentWeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.data = data;
        determineCondition();
    }

    private void determineCondition() {
        String conditionString = "";
        if (data.getWeatherListCount() > 0) {
            String icon = data.getWeatherList().get(0).getWeatherIconName();
            Pattern p = Pattern.compile("([0-9]{2})([dn])");
            Matcher m = p.matcher(icon);
            if (m.matches() && m.groupCount() > 0) {
                conditionString = m.group(0);
            }
        }
        switch(conditionString) {
            case "01":
                condition = Condition.CLEAR;
                break;
            case "02":
                condition = Condition.FEW_CLOUDS;
                break;
            case "03":
                condition = Condition.SCATTERED_CLOUDS;
                break;
            case "04":
                condition = Condition.BROKEN_CLOUDS;
                break;
            case "09":
                condition = Condition.SHOWER_RAIN;
                break;
            case "10":
                condition = Condition.RAIN;
                break;
            case "11":
                condition = Condition.THUNDERSTORM;
                break;
            case "13":
                condition = Condition.SNOW;
                break;
            case "50":
                condition = Condition.MIST;
                break;
            default:
                condition = Condition.NOT_AVAILABLE;
                break;
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
    public Condition getCondition() {
        return condition;
    }
}
