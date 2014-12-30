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
