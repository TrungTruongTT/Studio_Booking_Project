package com.example.demofacebook.Login;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    String idOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_confirm);
        customer = (CustomerAccount) getIntent().getExtras().get("customer");
        idOtp = (String) getIntent().getExtras().get("idOtp");
        String phoneValue = (String) getIntent().getExtras().get("phoneNumberFormatted");
        TextView phoneSignIn = findViewById(R.id.phoneSignIn);
        String result = phoneValue.replaceAll("\\d(?=\\d{4})", "*");
        phoneSignIn.setText(result);
        getOptConfirm(idOtp, phoneValue);


//        Button btn_resendCode = findViewById(R.id.btn_resendCode);
//        btn_resendCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendRequestToServer(phoneValue);
//            }
//        });
    }

    private void getOptConfirm(String id, String phone) {
        PinView pinView = findViewById(R.id.pinView);

//        pinView.requestFocus();
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

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

    private void sendRequestToServer(String phone) {
        OptSms phoneNumber = new OptSms(phone);
        Call<OptSms> call = ApiService.apiServiceGuest.getOtp(phoneNumber);
        call.enqueue(new Callback<OptSms>() {
            @Override
            public void onResponse(Call<OptSms> call, Response<OptSms> response) {
                if (response.isSuccessful()) {
                    OptSms responseValue = response.body();
                    idOtp = responseValue.getOtpId();
                    Toast.makeText(getApplicationContext(), "Send SMS Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Send SMS Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OptSms> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccount() {
        Log.w("createAccount: ", "createAccount");

        CustomerAccount customer = (CustomerAccount) getIntent().getExtras().get("customer");

        ApiService.apiServiceGuest.createCustomer(customer).enqueue(new Callback<CustomerAccount>() {
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
                }
            }

            @Override
            public void onFailure(Call<CustomerAccount> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
