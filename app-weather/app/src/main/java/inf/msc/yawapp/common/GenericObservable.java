package inf.msc.yawapp.common;

import android.database.Observable;

public class GenericObservable<T> extends Observable<GenericObserver<T>> {

    public void notifyAll(T argument) {
        for (GenericObserver<T> observer : mObservers) {
            observer.update(this, argument);
        }
    }
}
