package inf.msc.yawapp.common;

public interface GenericObserver<T> {
    public void update(GenericObservable<T> observable, T argument);
}
