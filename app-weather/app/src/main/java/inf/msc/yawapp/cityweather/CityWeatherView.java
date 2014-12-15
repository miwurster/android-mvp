package inf.msc.yawapp.cityweather;

import java.util.List;

public interface CityWeatherView {
    public void setHistory(final List<String> history);
    public void setSearchError(final String message);
}
