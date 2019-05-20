package com.example.dreamteam;

import com.example.dreamteam.model.LoginModel;
import com.example.dreamteam.model.RegistrationModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterFace {


    @FormUrlEncoded
    @POST("user_register")
    Call<RegistrationModel> getRegistration(@Field("devicetoken") String devicetoken,
                                            @Field("email_id") String email_id,
                                            @Field("password") String password,
                                            @Field("status") String status,
                                            @Field("reffercode") String reffercode,
                                            @Field("devicetype") String devicetype,
                                            @Field("mobile") String mobile);


    @Headers("Content-Type:application/json")
    @POST("user_register")
    Call<RegistrationModel> getRegistration(@Body String body);

    @Headers("Content-Type:application/json")
    @POST("user_login")
    Call<LoginModel> getLogin(@Body String body);

}
