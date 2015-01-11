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

package inf.msc.yawapp.owm;

public class OpenWeatherMapException extends Exception {
    public OpenWeatherMapException() {
    }

    public OpenWeatherMapException(String detailMessage) {
        super(detailMessage);
    }

    public OpenWeatherMapException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public OpenWeatherMapException(Throwable throwable) {
        super(throwable);
    }
}
