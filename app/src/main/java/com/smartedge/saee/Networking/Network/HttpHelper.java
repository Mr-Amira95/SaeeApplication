package com.smartedge.saee.Networking.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.LoginRequest;
import com.smartedge.saee.Networking.Models.UserInputModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes5.dex */
public class HttpHelper {
    private CallBack callback;
    private ProgressDialog dialog;

    public void postLogin(Context context, String url, final int tag, final Class clazz, Map<String, String> params) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().postLogin(url, params);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.1
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void postLogin(Context context, String url, final int tag, final Class clazz, LoginRequest loginRequest) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().post(url, loginRequest);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.2
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void post(Context context, String url, final int tag, final Class clazz, Map<String, Object> params) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().post(url, params);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.3
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void post(Context context, String url, final int tag, final Class clazz, UserInputModel params) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().post(url, params);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.4
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void post(Context context, String url, final int tag, final Class clazz, Object params) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().post(url, params);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.5
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void postReset(Context context, String url, final int tag, final Class clazz, Object params) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethodsReset().post(url, params);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.6
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void get(Context context, String url, final int tag, final Class clazz, HashMap<String, Object> map) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().get(url, map);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.7
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void postImages(Context context, String url, final int tag, final Class clazz, Map<String, RequestBody> params, MultipartBody.Part[] images) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.dialog = progressDialog;
        progressDialog.setMessage("Loading ...");
        this.dialog.setCancelable(false);
        this.dialog.show();
        Call<ResponseBody> call = MyApplication.getInstance().getHttpMethods().postImages(url, params, images);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.smartedge.saee.Networking.Network.HttpHelper.8
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                HttpHelper.this.dialog.dismiss();
                Log.d("Result: ", "Url:" + call2.request().url());
                try {
                    if (response.isSuccessful()) {
                        HttpHelper.this.result(clazz, response.body().string(), tag, response.isSuccessful(), response.isSuccessful());
                    } else if (HttpHelper.this.callback != null) {
                        HttpHelper.this.result(clazz, response.errorBody().source().readUtf8().toString(), tag, response.isSuccessful(), response.isSuccessful());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call2, Throwable t) {
                HttpHelper.this.dialog.dismiss();
                if (HttpHelper.this.callback != null) {
                    HttpHelper.this.callback.onFailure(tag, call2);
                }
                t.printStackTrace();
            }
        });
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void result(Class clazz, String str, int tag, boolean isSuccess, boolean flag) {
        if (flag) {
            if (this.callback != null) {
                Log.d("Result API", str);
                this.callback.onSuccess(tag, isSuccess, MyApplication.getInstance().getGson().fromJson(str, (Class<Object>) clazz));
            }
        } else if (this.callback != null) {
            Log.d("Result API", str);
            this.callback.onFailure(tag, MyApplication.getInstance().getGson().fromJson(str, (Class<Object>) clazz));
        }
    }
}
