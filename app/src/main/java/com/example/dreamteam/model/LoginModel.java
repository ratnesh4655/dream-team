package com.example.dreamteam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class LoginModel {

    @Expose
    @SerializedName("result")
    private Result result;
    @Expose
    @SerializedName("response")
    private String response;

    public Result getResult() {
        return result;
    }

    public String getResponse() {
        return response;
    }

    public static class Result {
        @Expose
        @SerializedName("user_id")
        private String user_id;
        @Expose
        @SerializedName("message")
        private String message;

        public String getUser_id() {
            return user_id;
        }

        public String getMessage() {
            return message;
        }
    }
}
