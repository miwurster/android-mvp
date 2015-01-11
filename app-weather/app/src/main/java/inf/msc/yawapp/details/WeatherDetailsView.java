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

package inf.msc.yawapp.details;

import inf.msc.yawapp.model.WeatherData;

public interface WeatherDetailsView {
    public void showLoadingAnimation();

    public void showWeatherContent();

    public void showSearchError();

    public void showCityName(final String city);

    public void showWeatherCondition(final WeatherData.Condition condition, boolean isDay);

    public void showFavouriteIcon(boolean isFavourite);
}
