package com.example.demofacebook.Login;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.OptSms;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpConfirmActivity extends AppCompatActivity {
    User user;
    String pinValue;
    String idOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_confirm);
        user = (User) getIntent().getExtras().get("user");
        idOtp = (String) getIntent().getExtras().get("idOtp");
        String phoneValue = (String) getIntent().getExtras().get("phoneNumberFormatted");
        TextView phoneSignIn = findViewById(R.id.phoneSignIn);
        String result = phoneValue.replaceAll("\\d(?=\\d{4})", "*");
        phoneSignIn.setText(result);
        getOptConfirm(idOtp, phoneValue);
    }

    private void getOptConfirm(String id, String phone) {
        PinView pinView = findViewById(R.id.pinView);
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 6) {
                    pinValue = pinView.getText().toString();
                    Log.w("TAG", pinValue);
                    checkOtp(pinValue, id, phone);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void checkOtp(String pinValue, String id, String phone) {
        OptSms getOptSms = new OptSms(id, phone, pinValue);

        Call<String> call = ApiService.apiServiceGuest.sendOtp(getOptSms);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    createAccount();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccount() {
        ApiService.apiServiceGuest.createCustomer(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(OtpConfirmActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Register Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
