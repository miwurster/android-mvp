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

public interface MutableLocation extends Location {
    public void setId(long id);

    public void setCity(final String city);

    public void setCountry(final String country);

    public void setLatitude(float latitude);

    public void setLongitude(float longitude);
}
