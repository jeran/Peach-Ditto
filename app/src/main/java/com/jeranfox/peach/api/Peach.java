package com.jeranfox.peach.api;

import android.content.Context;
import android.preference.PreferenceManager;

import com.jeranfox.peach.api.request.SignInRequest;
import com.jeranfox.peach.api.response.ApiResponse;
import com.jeranfox.peach.api.response.ExploreResponse;
import com.jeranfox.peach.api.response.SignInResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class Peach {
    private static final String AUTH_TOKEN_KEY = "auth_token_key";
    private static volatile Peach singleton;

    public static Peach with(Context context) {
        if (singleton == null) {
            synchronized (Peach.class) {
                if (singleton == null) {
                    singleton = new Peach(context);
                }
            }
        }
        return singleton;
    }

    private Context context;
    private PeachService peachService;
    private Map<Callback, Call> calls = new HashMap<>();

    private Peach(Context context) {
        this.context = context.getApplicationContext();

        // TODO(jeran): implement better solution for logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        peachService = new Retrofit.Builder()
                .baseUrl(PeachService.BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(PeachService.class);
    }

    public void signIn(String username, String password, final Callback<SignInResponse> callback) {
        Call<SignInResponse> call = peachService.signIn(new SignInRequest(username, password));
        calls.put(callback, call);
        Callback<SignInResponse> storeTokenCallback = new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                storeToken(response.body().getToken());
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        };
        call.enqueue(new ApiExceptionCallback<>(storeTokenCallback));
    }

    public void cancelRequest(Callback callback) {
        calls.get(callback).cancel();
    }

    public void cancelRequests(Set<Callback> callbacks) {
        Iterator<Callback> iterator = callbacks.iterator();
        while (iterator.hasNext()) {
            calls.get(iterator.next()).cancel();
        }
    }

    public void getExploreFeed(Callback<ExploreResponse> exploreCallBack) {
        Call<ExploreResponse> call = peachService.getExploreFeed(getAuthHeader());
        calls.put(exploreCallBack, call);
        call.enqueue(new ApiExceptionCallback<>(exploreCallBack));
    }

    private String getAuthHeader() {
        return "Bearer " + getAuthToken();
    }

    public boolean signedIn() {
        return getAuthToken() != null;
    }

    public void signOut() {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(AUTH_TOKEN_KEY).apply();
    }

    private void storeToken(String token) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(AUTH_TOKEN_KEY, token).apply();
    }

    private String getAuthToken() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(AUTH_TOKEN_KEY, null);
    }

    public static class ApiException extends Exception {
        public ApiException(String message) {
            super(message);
        }
    }

    /**
     * Implementation of retrofit2.Callback that forwards Peach api response errors to the
     * onFailure() method with the error message wrapped in an ApiException.
     */
    private static class ApiExceptionCallback<T extends ApiResponse> implements Callback<T> {
        private Callback<T> callback;

        public ApiExceptionCallback(Callback<T> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.body() == null) {
                onFailure(call, new ApiException("Server error. Empty Response."));
            } else if (response.body().wasSuccess()) {
                callback.onResponse(call, response);
            } else {
                onFailure(call, new ApiException(response.body().getErrorMessage()));
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            callback.onFailure(call, t);
        }
    }
}
