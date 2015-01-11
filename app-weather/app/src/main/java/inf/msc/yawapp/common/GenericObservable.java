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

import android.database.Observable;

public class GenericObservable<T> extends Observable<GenericObserver<T>> {

    public void notifyAll(T argument) {
        for (GenericObserver<T> observer : mObservers) {
            observer.update(this, argument);
        }
    }
}
