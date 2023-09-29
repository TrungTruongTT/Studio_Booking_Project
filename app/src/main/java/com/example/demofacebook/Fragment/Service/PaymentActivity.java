package com.example.demofacebook.Fragment.Service;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Api.CreateOrder;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.OrderDetailActivity;
import com.example.demofacebook.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PaymentActivity extends AppCompatActivity {
    TextView lblZpTransToken;
    TextView txtToken;
    Button btnCreateOrder;
    Button btnPay;
    EditText txtAmount;

    //private Service service;
    private OrderDetail orderDetail;
    Toolbar toolbar;
    private String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //service = loadService();
        orderDetail = loadOrderDetail();
        //Load toolbar
        initToolBar();
        //khởi tạo
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // ZaloPay SDK Init
        ZaloPaySDK.tearDown();
        ZaloPaySDK.init(554, Environment.SANDBOX);
        // bind components with ids
        BindView();

        // handle CreateOrder for Zalo API
        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestZalo();
            }
        });


    }
    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.Payment_Toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestZalo() {
        Bundle bundle = new Bundle();

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //CreateOrder Model in Api Package
                CreateOrder orderApi = new CreateOrder();
                try {
                    //txtAmount.getText() lấy số dư
                    JSONObject data = orderApi.createOrder(txtAmount.getText().toString());
                    //bắt log check
                    Log.d("Amount", txtAmount.getText().toString());
                    lblZpTransToken.setVisibility(View.VISIBLE);
                    String code = data.getString("returncode");

                    if (code.equals("1")) {
                        lblZpTransToken.setText("zptranstoken");
                        txtToken.setText(data.getString("zptranstoken"));
                        IsDone();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //hiện nút pay gửi qua zaloPay
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = txtToken.getText().toString();
                ZaloPaySDK.getInstance().payOrder(PaymentActivity.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(PaymentActivity.this)
                                        .setTitle("Payment Success")
                                        .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Log.w("PAYMENT", "PAYMENT SUCCESS");
                                                updateDeposited();
                                               /* status = "success";
                                                Intent intent = new Intent(PaymentActivity.this, OrderDetailActivity.class);
                                                bundle.putSerializable("statusPay",status);
                                                bundle.putSerializable("orderId",orderDetail.getOrder().getOrderId());
                                                bundle.putSerializable("orderStatus",orderDetail.getOrder().getStatus());
                                                intent.putExtras(bundle);
                                                startActivity(intent);*/
                                            }
                                        })
                                        .setNegativeButton("Cancel", null).show();
                            }

                        });
                        IsLoading();
                    }

                    @Override
                    public void onPaymentCanceled(String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(PaymentActivity.this)
                                .setTitle("User Cancel Payment")
                                .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                        new AlertDialog.Builder(PaymentActivity.this)
                                .setTitle("Payment Fail")
                                .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.w("PAYMENT", "PAYMENT FAIL");
                                        /*Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                                        startActivity(intent);*/
                                        Intent intent = new Intent(PaymentActivity.this, OrderDetailActivity.class);
                                        bundle.putSerializable("statusPay",status);
                                        bundle.putSerializable("orderId",orderDetail.getOrder().getOrderId());
                                        bundle.putSerializable("orderStatus",orderDetail.getOrder().getStatus());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }
                });
            }
        });
    }

    private void BindView() {
        txtToken = findViewById(R.id.txtToken);
        lblZpTransToken = findViewById(R.id.lblZpTransToken);
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        txtAmount = findViewById(R.id.txtAmount);
        txtAmount.setText(String.valueOf(orderDetail.getPrice()));// găn giá trị vào cho trang payment
        btnPay = findViewById(R.id.btnPay);
        IsLoading();
    }

    private void IsLoading() {
        lblZpTransToken.setVisibility(View.INVISIBLE);
        txtToken.setVisibility(View.INVISIBLE);
        btnPay.setVisibility(View.INVISIBLE);
    }

    private Service loadService(){
        if(getIntent().getExtras() != null) {
            Service service= (Service) getIntent().getExtras().get("service");
            if (service != null) {
                return service;
            }
        }
        return null;
    }

    private OrderDetail loadOrderDetail(){
        if(getIntent().getExtras() != null) {
            OrderDetail orderDetail= (OrderDetail) getIntent().getExtras().get("orderDetail");
            if (orderDetail != null) {
                return orderDetail;
            }
        }
        return null;
    }

    private void IsDone() {
        lblZpTransToken.setVisibility(View.VISIBLE);
        txtToken.setVisibility(View.VISIBLE);
        btnPay.setVisibility(View.VISIBLE);
    }

    private void updateDeposited(){
        ApiService.apiService.updateCancelStatus(orderDetail.getOrder().getOrderId(), "deposited").enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Deposit Successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Deposit Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}