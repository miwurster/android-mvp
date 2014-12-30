package inf.msc.yawapp.model;

public interface WeatherData {
    public enum Condition {
        NOT_AVAILABLE, CLEAR, FEW_CLOUDS, SCATTERED_CLOUDS, BROKEN_CLOUDS,
        SHOWER_RAIN, RAIN, THUNDERSTORM, SNOW, MIST
    }

    public String getCityName();

    public float getCurrentTemperature();

    public float getMinTemperature();

    public float getMaxTemperature();

    public Condition getCondition();

    public boolean isDay();
}
