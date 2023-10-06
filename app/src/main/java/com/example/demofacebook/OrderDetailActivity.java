package com.example.demofacebook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Chat.Booking.OrderDetailAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderDetailListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.PaymentActivity;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetailActivity extends AppCompatActivity {
    private Studio studio;
    private int orderId;
    private String orderStatus;
    private RecyclerView recyclerView;
    private OrderDetailAdapter orderDetailAdapter;
    private List<OrderDetail> orderDetail;

    Button cancelOrderBtn;
    Button depositOrderBtn;
    Button paidTheRestOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //Load Studio, OrderId
        loadData();
        //Init ToolBar
        initToolBar();
        //LoadServiceList
        getOrderData(orderId);
        //Action Button
    }

    private void getOrderData(int orderId) {
        ApiService.apiService.getDetailByOrderId(orderId).enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                if (response.isSuccessful()) {
                    orderDetail = response.body();
                    loadServiceList(orderDetail);
                    loadStudioData(orderDetail);
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
            }
        });
    }

    private void loadServiceList(List<OrderDetail> orderDetail) {
        recyclerView = findViewById(R.id.orderDetailRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        orderDetailAdapter = new OrderDetailAdapter(orderDetail, new IClickItemOrderDetailListener() {
            @Override
            public void onClickItemOrderDetail(OrderDetail orderDetail) {
                //click on service
                onClickGoServiceDetail(orderDetail);
            }
        }, new IClickItemFeedbackOrderDetailListener() {
            @Override
            public void onClickItemFeedbackOrderDetail(OrderDetail orderDetail, Button button) {
                //click on feedback btn
                openFeedbackDialog(Gravity.CENTER, studio, orderDetail, button);
            }
        }, orderStatus);
        recyclerView.setAdapter(orderDetailAdapter);
    }


    private void loadStudioData(List<OrderDetail> orderDetail) {
        studio = orderDetail.get(0).getServicePack().getStudio();
        Order order = orderDetail.get(0).getOrder();
        //Studio Information
        ImageView studioAvatar = findViewById(R.id.StudioAvatarImage_OrderDetail);
        TextView studioName = findViewById(R.id.StudioName_OrderDetail);
        TextView studioRating = findViewById(R.id.StudioRating_OrderDetail);
        if (studio.getImage() != null) {
            Picasso.get().load(studio.getImage())
                    .error(R.drawable.placeholder_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(studioAvatar);
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .error(R.drawable.placeholder_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(studioAvatar);
        }
        studioRating.setText("⭐: " + studio.getRating());
        studioName.setText(studio.getTitle());
        //Payment Detail Information
//        TextView discount = findViewById(R.id.Discount_OrderDetail);
        TextView totalPrice = findViewById(R.id.TotalPrice_OrderDetail);
        TextView deposited = findViewById(R.id.Deposit_OrderDetail);
        TextView startDate = findViewById(R.id.DateCheckInt_OrderDetail);
        TextView orderId = findViewById(R.id.OrderId_OrderDetail);
        TextView bookingDate = findViewById(R.id.BookingDate_OrderDetail);
        TextView orderStatus = findViewById(R.id.OrderStatus_OrderDetail);
        TextView note = findViewById(R.id.Description_OrderDetail);

        int totalPriceValue = 0;
        for (int i = 0; i < orderDetail.size(); i++) {
            totalPriceValue = totalPriceValue + orderDetail.get(i).getServicePack().getPriceService();
        }
        totalPrice.setText(formatMoney(totalPriceValue) + " VND");

        if (order.getDeposit() > 0) {
            deposited.setText(formatMoney(order.getDeposit()) + " VND");
        } else {
            deposited.setText("Not deposited yet");
        }

        if (order.getCheckIn() != null) {
            startDate.setText(order.getCheckIn().toString());
        } else {
            startDate.setText("Not deposited yet");
        }

        orderId.setText("" + order.getOrderId());
        bookingDate.setText(order.getOrderDate().toString());
        orderStatus.setText(order.getStatus());
        note.setText(order.getDescription());
    }

    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }
    private void loadData() {
        if (getIntent().getExtras() != null) {
            orderId = (int) getIntent().getExtras().get("orderId");
            orderStatus = (String) getIntent().getExtras().get("orderStatus");

            cancelOrderBtn = findViewById(R.id.CancelOrderBtn);
            depositOrderBtn = findViewById(R.id.DepositOrderBtn);
            paidTheRestOrderBtn = findViewById(R.id.PaidTheRestOrderBtn);
            switch (orderStatus) {
                case "pending":
                    cancelOrderBtn.setEnabled(true);
                    cancelOrderBtn.setVisibility(View.VISIBLE);
                    depositOrderBtn.setEnabled(true);
                    depositOrderBtn.setVisibility(View.VISIBLE);
                    paidTheRestOrderBtn.setEnabled(false);
                    paidTheRestOrderBtn.setVisibility(View.INVISIBLE);
                    break;
                case "deposited":
                    cancelOrderBtn.setEnabled(true);
                    cancelOrderBtn.setVisibility(View.VISIBLE);
                    depositOrderBtn.setEnabled(false);
                    depositOrderBtn.setVisibility(View.INVISIBLE);
                    paidTheRestOrderBtn.setEnabled(false);
                    paidTheRestOrderBtn.setVisibility(View.INVISIBLE);
                    break;
                case "worked":
                    cancelOrderBtn.setEnabled(false);
                    cancelOrderBtn.setVisibility(View.INVISIBLE);
                    depositOrderBtn.setEnabled(false);
                    depositOrderBtn.setVisibility(View.INVISIBLE);
                    paidTheRestOrderBtn.setEnabled(true);
                    paidTheRestOrderBtn.setVisibility(View.VISIBLE);
                    break;
                case "completed":
                case "cancel":
                    cancelOrderBtn.setEnabled(false);
                    cancelOrderBtn.setVisibility(View.INVISIBLE);
                    depositOrderBtn.setEnabled(false);
                    depositOrderBtn.setVisibility(View.INVISIBLE);
                    paidTheRestOrderBtn.setEnabled(false);
                    paidTheRestOrderBtn.setVisibility(View.INVISIBLE);
                    break;
            }


            cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmDialog();
                }
            });

            depositOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    depositOrderAction();
                }
            });

            paidTheRestOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    paidTheRestOrderAction();
                }
            });
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel order " + orderId + " ?");
        builder.setMessage("Are you sure you want to cancel " + orderId + " ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelOrderAction();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void paidTheRestOrderAction() {
        ApiService.apiService.updateCancelStatus(orderId, "completed").enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void depositOrderAction() {
        payment();
    }


    private void payment() {
        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("orderDetail",orderDetail.get(0));
        //bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void cancelOrderAction() {
        ApiService.apiService.updateCancelStatus(orderId, "cancel").enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cancel Successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.OrderDetailToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.valueOf("Order Id: " + orderId));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickGoServiceDetail(OrderDetail orderDetail) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        Service service = orderDetail.getServicePack();
        bundle.putSerializable("service", service);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openFeedbackDialog(int gravity, Studio studio, OrderDetail orderDetail, Button buttonFeedback) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_feedback_form);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        ImageView studioImage = dialog.findViewById(R.id.StudioAvatarImageFeedback);
        Picasso.get().load(studio.getImage()).into(studioImage);
        TextView NameStudioFeedback = dialog.findViewById(R.id.NameStudioFeedback);
        NameStudioFeedback.setText(studio.getTitle());

        RatingBar ratingStar = dialog.findViewById(R.id.RatingStarFeedback);
        EditText feedbackFormDescription = dialog.findViewById(R.id.FeedbackFormDescription);
        configEditText(feedbackFormDescription);

        Button updateDialog = dialog.findViewById(R.id.SubmitFeedbackDialog);
        updateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = feedbackFormDescription.getText().toString();
                float ratingValue = ratingStar.getRating();
                if (ratingValue == 0) {
                    Toast.makeText(OrderDetailActivity.this, "Please Rating Service Star", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    boolean checkSubmission = submitFeedbackForm(description, ratingValue, orderDetail);
                    if (checkSubmission) {
                        buttonFeedback.setEnabled(false);
                        buttonFeedback.setVisibility(View.INVISIBLE);
                        dialog.dismiss();
                    }
                }
            }
        });
        Button closeBtn = dialog.findViewById(R.id.CancelFeedbackDialog);
        closeBtn.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    private Boolean submitFeedbackForm(String description, float ratingValue, OrderDetail orderDetailValue) {
        int rating = (int) ratingValue;
        OrderDetail feedback = new OrderDetail(rating, description);
        updateData(orderDetailValue.getOrderDetailId(), feedback);
        return true;
    }

    private void updateData(int id, OrderDetail orderDetail) {
        Log.w("updateData: ", id +"");
        Call<OrderDetail> call = ApiService.apiService.createFeedback(id, orderDetail);
        call.enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                if (response.isSuccessful()) {


                    Toast.makeText(OrderDetailActivity.this, "Thank You", Toast.LENGTH_SHORT).show();
                } else {


                    Toast.makeText(OrderDetailActivity.this, "Send Feedback Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {
                // Request failed due to network error or other issues
                // Handle error here
            }
        });
    }

    private void configEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
}