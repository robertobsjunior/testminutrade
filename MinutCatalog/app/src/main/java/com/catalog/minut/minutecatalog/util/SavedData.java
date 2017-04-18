package com.catalog.minut.minutecatalog.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by robertosampaio on 16/04/17.
 */

public class SavedData{

    private static String USER_APP = "UserAPP";

    public static String getUserAPP(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_APP, null);
    }

    public static void setUserApp(Context context, String userAPP){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(USER_APP, userAPP).apply();
    }
}
