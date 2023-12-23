package com.smartedge.saee.Networking.Network;

import com.smartedge.saee.Networking.Models.Auth.LoginResults;
import com.smartedge.saee.Networking.Models.LoginRequest;
import com.smartedge.saee.Networking.Models.UserInputModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitServices {
    @Headers({"Accept: application/json"})
    @GET
    Call<ResponseBody> get(@Url String str, @QueryMap Map<String, Object> map);

    @POST
    Call<LoginResults> login(@Url String str, @Body LoginRequest loginRequest);

    @POST
    Call<ResponseBody> post(@Url String str, @Body UserInputModel userInputModel);

    @Headers({"Accept: application/json"})
    @POST
    Call<ResponseBody> post(@Url String str, @Body Object obj);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST
    Call<ResponseBody> post(@Url String str, @FieldMap Map<String, Object> map);

    @POST
    @Multipart
    Call<ResponseBody> postImages(@Url String str, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part[] partArr);

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST
    Call<ResponseBody> postLogin(@Url String str, @FieldMap Map<String, String> map);
}
