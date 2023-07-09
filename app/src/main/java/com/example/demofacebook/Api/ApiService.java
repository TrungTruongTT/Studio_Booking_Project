package com.example.demofacebook.Api;

import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS) // Timeout kết nối // set 1s
            .readTimeout(10, TimeUnit.SECONDS) // Timeout đọc dữ liệu // đọc API 10s
            .writeTimeout(10, TimeUnit.SECONDS) // Timeout ghi dữ liệu // viết API 10s
            .build();

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
            .client(client)
            .baseUrl("http://10.0.2.2:8080") // DOMAIN
            //http://10.0.2.2:8080 //http://localhost:8080
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    ApiService talkJsServices = new Retrofit.Builder()
            .baseUrl("https://api.talkjs.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("/api/services") //GET list services
    Call<List<Service>> serviceCall();

    @GET("/api/services/{serviceId}") //GetservicesByid
    Call<Service> getServiceById(
            @Path("serviceId") int serviceId
    );

    @GET("/api/studios/{id}") // GetStudioByID
    Call<Studio> getStudioByID(
            @Path("id") int studioID
    );

    @GET("/api/studios?name=") // GetStudioList
    Call<List<Studio>> getStudio();

    @GET("/api/studios") // GetStudioByname
    Call<List<Studio>> getStudioByName(
            @Query("name") String studioName
    );

    @POST("/api/auth/login")
    Call<TokenResponse> login(@Body Login_Request login);

    //@Headers("Authorization: Bearer sk_test_KS0lVFwV4W6f8Vf4COh2fkfFABxyAXBf")
    //@GET("/v1/{appId}/conversations/{conversationId}/messages")

    /*serviceId name createDate price description soldCount status updateDate view discount rating updateBy createBy studio
    servicePack_mediaService servicePack_orderDetail servicePack_favorite*/
}
