package com.jeranfox.peach.api;

import com.jeranfox.peach.api.request.SignInRequest;
import com.jeranfox.peach.api.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PeachService {
    public static final String BASE_URL = "https://v1.peachapi.com";

    @Headers("Accept: application/json")
    @POST("login")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);
}
