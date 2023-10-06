package com.example.demofacebook.Api;


import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.Feedback;
import com.example.demofacebook.Model.Gallery;
import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.OptSms;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Interceptor interceptor = chain -> {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String token = "Bearer " + DataLocalManager.getTokenResponse().getAccessToken();
        builder.addHeader("Authorization", token);
        return chain.proceed(builder.build());
    };
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(5, TimeUnit.SECONDS) // Timeout kết nối // set 1s
            .readTimeout(30, TimeUnit.SECONDS) // Timeout đọc dữ liệu // đọc API 10s
            .writeTimeout(30, TimeUnit.SECONDS) // Timeout ghi dữ liệu // viết API 10s
            .build();
    OkHttpClient guest = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS) // Timeout kết nối // set 1s
            .readTimeout(30, TimeUnit.SECONDS) // Timeout đọc dữ liệu // đọc API 10s
            .writeTimeout(30, TimeUnit.SECONDS) // Timeout ghi dữ liệu // viết API 10s
            .build();

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService = new Retrofit.Builder()
            .client(client)
            .baseUrl("http://10.0.2.2:8080") // DOMAIN
            //http://10.0.2.2:8080 //http://localhost:8080
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    ApiService apiServiceGuest = new Retrofit.Builder()
            .client(guest)
            .baseUrl("http://10.0.2.2:8080") // DOMAIN
            //http://10.0.2.2:8080 //http://localhost:8080
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    ApiService talkJsServices = new Retrofit.Builder()
            .baseUrl("https://api.talkjs.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    public final static String APPID_TALKJS = "tQ6S3FD4";
    public final static String BEARER_TALKJS = "sk_test_KS0lVFwV4W6f8Vf4COh2fkfFABxyAXBf";

   /* @Headers("Authorization: Bearer sk_test_KS0lVFwV4W6f8Vf4COh2fkfFABxyAXBf")
    @GET("/{appId}/users/{userId}/conversations")
    Call<> getListConersations(@Path("appId") APPID_TALKJS ,@Path("userId") int userID);*/


    //services
    @GET("/api/services")
    //GET list services
    Call<List<Service>> serviceCall();

    @GET("/api/services/{serviceId}")
        //GetservicesByid
    Call<Service> getServiceById(
            @Path("serviceId") int serviceId
    );

    //studio
    @GET("/api/studios/{id}")
    // GetStudioByID
    Call<Studio> getStudioByID(
            @Path("id") int studioID
    );

    @GET("/api/studios?name=")
        // GetStudioList
    Call<List<Studio>> getStudio();

    @GET("/api/studios")
        // GetStudioByname
    Call<List<Studio>> getStudioByName(
            @Query("name") String studioName
    );
    //account
    @POST("/api/auth/login")
    Call<TokenResponse> login(@Body Login_Request login);

    @POST("/api/customers")
    //register
    Call<CustomerAccount> createCustomer(@Body CustomerAccount account);

    @GET("/api/customers")
    Call<List<CustomerAccount>> getCustomerByEmailorPhone(@Query("emailOrPhone") String emailOrPhone);

    @GET("/api/services/studio/all/{studioId}")
    Call<List<Service>> getServiceByStudioId(
            @Path("studioId") int studioId
    );

    @GET("/api/order-details/feedback/service/{serviceId}")
    Call<List<Feedback>> getServiceFeedbackServiceId(
            @Path("serviceId") int serviceId
    );

    @GET("/api/albums/studio/{studioId}")
    Call<List<Gallery>> getGalleryByStudioId(
            @Path("studioId") int studioId
    );

    @GET("/api/order-details/feedback/studio")
    Call<List<Feedback>> getServiceFeedbackStudioId(
            @Query("studioId") int studioId
    );

    @GET("/api/orders/user")
    Call<List<OrderInformation>> geOrderIdByUser(
    );

    @GET("/api/order-details/order/{orderId}")
    Call<List<OrderDetail>> getDetailByOrderId(
            @Path("orderId") int orderId
    );

   @PATCH("/api/orders/{orderId}")
    Call<Void> updateCancelStatus(
            @Path("orderId") int orderId,
            @Query("status") String status
    );

    @POST("api/order-details/feedback/{orderDetailId}")
    Call<OrderDetail> createFeedback(
            @Path("orderDetailId") int orderDetailId,
            @Body OrderDetail orderDetail
    );

    @PATCH("api/customers/{customerId}")
    Call<Void> updateCustomer(
            @Path("customerId") int customerId,
            @Body CustomerAccount customerAccount
    );

    @POST("api/otp")
    Call<OptSms> getOtp(
            @Body OptSms optSms
    );

    @POST("api/otp/verify")
    Call<String> sendOtp(
            @Body OptSms optSms
    );


    //@Headers("Authorization: Bearer sk_test_KS0lVFwV4W6f8Vf4COh2fkfFABxyAXBf")
    //@GET("/v1/{appId}/conversations/{conversationId}/messages")
    /*serviceId name createDate price description soldCount status updateDate view discount rating updateBy createBy studio
    servicePack_mediaService servicePack_orderDetail servicePack_favorite*/
}
