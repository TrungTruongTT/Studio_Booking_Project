package com.example.demofacebook.Api;

import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
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

    //@GET("/v1/{appId}/conversations/{conversationId}/messages")

    /*serviceId name createDate price description soldCount status updateDate view discount rating updateBy createBy studio
    servicePack_mediaService servicePack_orderDetail servicePack_favorite*/
}
