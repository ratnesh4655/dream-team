package com.example.dreamteam.common;

/**
 * Created by Jaydeep on 17-05-2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class SharedPreferenceUtil {
    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;

    public SharedPreferenceUtil() {
    }

    public static void init(Context mcontext) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mcontext);
            editor = sharedPreferences.edit();
            editor.apply();
        }

    }

    public static void putValue(String key, String value) {
        editor.putString(key, value);
    }

    public static void putValue(String key, int value) {
        editor.putInt(key, value);
    }

    public static void putValue(String key, long value) {
        editor.putLong(key, value);
    }

    public static void putValue(String key, boolean value) {
        editor.putBoolean(key, value);
    }

    public static void save() {
        editor.commit();
    }

    public static String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }


    public static void remove(String key) {
        editor.remove(key);
        editor.commit();
    }


    public static void clear() {
        editor.clear();
    }
}
