package com.smartedge.saee.Networking.Basic;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.smartedge.saee.Networking.Network.HttpHelper;
import com.smartedge.saee.Networking.Network.RetrofitServices;
import com.smartedge.saee.Views.Activities.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    private static MyApplication instance;
    private Gson gson;
    HttpHelper httpHelper;
    private RetrofitServices httpMethods;
    private SharedPreferences preferences;

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            myApplication = instance;
        }
        return myApplication;
    }

    public synchronized RetrofitServices getHttpMethods() {
        if (this.httpMethods == null) {
            this.gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(AppConstants.BASE_URL).client(createClient()).build();
            this.httpMethods = (RetrofitServices) retrofit.create(RetrofitServices.class);
        }
        return this.httpMethods;
    }

    public synchronized RetrofitServices getHttpMethodsReset() {
        if (this.httpMethods == null) {
            this.gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(AppConstants.BASE_URL).client(createClientReset()).build();
            this.httpMethods = (RetrofitServices) retrofit.create(RetrofitServices.class);
        }
        return this.httpMethods;
    }

    public OkHttpClient createClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() { // from class: com.smartedge.saee.Networking.Basic.MyApplication.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + PreferencesUtils.getUserToken()).header("Accept-Language", PreferencesUtils.getLanguage());
                Request newRequest = builder.build();
                Response resp = chain.proceed(newRequest);
                int code = resp.code();
                Log.d("res Code", "-------------" + code);
                return resp;
            }
        }).readTimeout(1L, TimeUnit.MINUTES).connectTimeout(1L, TimeUnit.MINUTES).build();
        return okHttpClient;
    }

    public OkHttpClient createClientReset() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() { // from class: com.smartedge.saee.Networking.Basic.MyApplication.2
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + PreferencesUtils.getResetToken()).header("Accept-Language", PreferencesUtils.getLanguage());
                Request newRequest = builder.build();
                Response resp = chain.proceed(newRequest);
                int code = resp.code();
                Log.d("res Code", "-------------" + code);
                return resp;
            }
        }).readTimeout(1L, TimeUnit.MINUTES).connectTimeout(1L, TimeUnit.MINUTES).build();
        return okHttpClient;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public synchronized HttpHelper getHttpHelper() {
        if (this.httpHelper == null) {
            this.httpHelper = new HttpHelper();
        }
        return this.httpHelper;
    }

    public synchronized SharedPreferences getPreferences() {
        if (this.preferences == null) {
            this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return this.preferences;
    }

    public synchronized Gson getGson() {
        if (this.gson == null) {
            this.gson = new GsonBuilder().setLenient().create();
        }
        return this.gson;
    }

    private void startActivity() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(268451840));
    }
}
