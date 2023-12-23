package com.smartedge.saee.Networking.Basic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

/* loaded from: classes6.dex */
public class PreferencesUtils {
    private Activity activity;

    public PreferencesUtils(Activity activity) {
        this.activity = activity;
    }

    public static String getUserToken() {
        return getString(PrefKeys.token, "-1");
    }

    public static void setUserToken(String token) {
        setString(PrefKeys.token, token);
    }

    public static String getResetToken() {
        return getString(PrefKeys.resetToken, "-1");
    }

    public static void setResetToken(String token) {
        setString(PrefKeys.resetToken, token);
    }

    public static String getUserID() {
        return getString(PrefKeys.id, "-1");
    }

    public static void setUserID(String id) {
        setString(PrefKeys.id, id);
    }

    public static String getDriverID() {
        return getString(PrefKeys.driver_id, "-1");
    }

    public static void setDriverID(String id) {
        setString(PrefKeys.driver_id, id);
    }

    public static String getLanguage() {
        return getString(PrefKeys.language, PrefKeys.language);
    }

    public static void setLanguage(String lang) {
        setString(PrefKeys.language, lang);
    }

    public static void setLocale(Activity activity) {
        Locale myLocale = new Locale(getLanguage());
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void clearDefaults(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public static void setString(String key, String value) {
        MyApplication.getInstance().getPreferences().edit().putString(key, value).commit();
    }

    public static String getString(String key, String val) {
        AppConstants.Trace("getString " + key, val + " Default");
        return MyApplication.getInstance().getPreferences().getString(key, val);
    }
}
