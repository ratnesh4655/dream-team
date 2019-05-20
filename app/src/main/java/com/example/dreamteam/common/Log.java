package com.example.dreamteam.common;

public class Log {
    private static final String TAG= "ratensh";
    public static  void E(String mes){
        android.util.Log.e(TAG,mes);
    }public static  void I(String mes){
        android.util.Log.i(TAG,mes);
    }
}
