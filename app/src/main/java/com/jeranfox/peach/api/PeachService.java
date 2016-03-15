package com.jeranfox.peach.api;

import com.jeranfox.peach.api.request.SignInRequest;
import com.jeranfox.peach.api.response.ExploreResponse;
import com.jeranfox.peach.api.response.FeedResponse;
import com.jeranfox.peach.api.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PeachService {
    public static final String BASE_URL = "https://v1.peachapi.com";

    @Headers("Accept: application/json")
    @POST("login")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);

    @Headers("Accept: application/json")
    @GET("connections/explore")
    Call<ExploreResponse> getExploreFeed(@Header("Authorization") String authorization);

    @Headers("Accept: application/json")
    @GET("connections")
    Call<FeedResponse> getFeed(@Header("Authorization") String authorization);
}
