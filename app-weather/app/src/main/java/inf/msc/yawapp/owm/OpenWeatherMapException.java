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
