package com.example.demofacebook.DAO;

import android.util.Log;
import android.widget.Toast;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDAO {
    private List<Service> list;
    public List<Service> getListService() {
        // Gọi API bằng Retrofit và Callback
        ApiService.apiService.serviceCall().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()) {
                    list.addAll(response.body());
                } else {
                    // Xử lý khi gọi API không thành công
                    Log.e("API Error", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                // Xử lý khi gọi API gặp lỗi
                Log.e("API Error", t.getMessage());
            }
        });
/*
        // Đợi cho đến khi API trả về kết quả
        try {
            Thread.sleep(2000); // Thời gian chờ tùy thuộc vào tốc độ kết nối và xử lý của API
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return list;
    }
}
