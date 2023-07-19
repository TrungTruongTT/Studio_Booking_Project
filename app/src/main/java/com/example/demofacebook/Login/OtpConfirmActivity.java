package com.example.demofacebook.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.OptSms;
import com.example.demofacebook.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpConfirmActivity extends AppCompatActivity {
    CustomerAccount customer;
    String pinValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_confirm);
        customer = (CustomerAccount) getIntent().getExtras().get("customer");
        String idOtp = (String) getIntent().getExtras().get("idOtp");
        String phoneValue = (String) getIntent().getExtras().get("phoneNumberFormatted");
        getOptConfirm(idOtp, phoneValue);
    }

    private void getOptConfirm(String id, String phone) {
        PinView pinView = findViewById(R.id.pinView);

        pinView.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

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

        Call<String> call = ApiService.apiService.sendOtp(getOptSms);
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
        Log.w("createAccount: ", "createAccount");

        CustomerAccount customer = (CustomerAccount) getIntent().getExtras().get("customer");

        ApiService.apiService.createCustomer(customer).enqueue(new Callback<CustomerAccount>() {
            @Override
            public void onResponse(Call<CustomerAccount> call, Response<CustomerAccount> response) {
                if (response.isSuccessful()) {
                    CustomerAccount customerAccount = response.body();
                    Log.d("CUSTOMER ACCOUNT", customerAccount.getUser().getPhone());
                    if (customerAccount != null) {
                        Intent intent = new Intent(OtpConfirmActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Create Account UnSuccess", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Create Account UnSuccess Duplicated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerAccount> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
