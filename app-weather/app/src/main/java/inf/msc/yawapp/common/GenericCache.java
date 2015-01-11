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

package inf.msc.yawapp.common;

public class GenericCache<T> extends GenericObservable<T> {

    private T data;

    @Override
    public void notifyAll(T argument) {
        data = argument;
        super.notifyAll(argument);
    }

    public T getData() {
        return data;
    }
}
