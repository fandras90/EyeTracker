package com.eyetracker.mobile.interactor;

/**
 * Created by fabia on 4/22/2016.
 */
public class Event<P> {
    private int code;
    private P result;
    private Throwable throwable;

    public Event() {
    }

    public Event(int code, P param, Throwable throwable) {
        this.code = code;
        this.result = param;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public P getArtists() {
        return result;
    }

    public void setArtists(P param) {
        this.result = param;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
