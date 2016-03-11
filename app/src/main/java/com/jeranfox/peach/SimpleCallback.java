package com.jeranfox.peach;

/**
 * Simple callback to wrap network responses with local data type returns.
 *
 * @param <T> The type of response to expect.
 */
public interface SimpleCallback<T> {
    void onSuccess(T response);

    void onFailure(Throwable throwable);
}
