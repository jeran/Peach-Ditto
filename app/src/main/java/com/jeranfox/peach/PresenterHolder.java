package com.jeranfox.peach;

import com.jeranfox.peach.presenters.Presenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Copy of com.israelferrer.effectiveandroid.PresenterHolder.
 */
public class PresenterHolder {
    static volatile PresenterHolder singleton = null;

    private Map<Class, Presenter> presenterMap;


    public static PresenterHolder getInstance() {
        if (singleton == null) {
            synchronized (PresenterHolder.class) {
                if (singleton == null) {
                    singleton = new PresenterHolder();
                }
            }
        }
        return singleton;
    }

    private PresenterHolder() {
        this.presenterMap = new HashMap<>();
    }

    public void putPresenter(Class c, Presenter p) {
        presenterMap.put(c, p);
    }

    public <T extends Presenter> T getPresenter(Class<T> c) {
        return (T) presenterMap.get(c);
    }

    public void remove(Class c) {
        presenterMap.remove(c);
    }
}
