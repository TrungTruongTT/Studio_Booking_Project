package com.example.demofacebook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.Chat.Booking.OrderDetailAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderDetailListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Order;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.squareup.picasso.Picasso;

import java.util.List;

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
    //Upload Image
    private static final int GALLERY_REQUEST_CODE = 123;
    ImageView uploadImage_Feedback;
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
        getOrderData(new View(getApplicationContext()), orderId);
        //Action Button


    }

    private void loadServiceList(List<OrderDetail> orderDetail) {
        recyclerView = findViewById(R.id.orderDetailRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        orderDetailAdapter = new OrderDetailAdapter(orderDetail, new IClickItemOrderDetailListener() {
            @Override
            public void onClickItemOrderDetail(Service service) {
                //click on service
                onClickGoServiceDetail(service);
            }
        }, new IClickItemFeedbackOrderDetailListener() {
            @Override
            public void onClickItemFeedbackOrderDetail(Service service, Button button) {
                //click on feedback btn
               openViewImageFeedbackDialog(Gravity.CENTER, studio, service, button);
            }
        }, orderStatus);
        recyclerView.setAdapter(orderDetailAdapter);
    }

    private void getOrderData(@NonNull View view, int orderId) {
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

    private void loadStudioData(List<OrderDetail> orderDetail) {
        studio = orderDetail.get(0).getServicePack().getStudio();
        Order order = orderDetail.get(0).getOrder();
        //Studio Information
        ImageView studioAvatar = findViewById(R.id.StudioAvatarImage_OrderDetail);
        TextView studioName = findViewById(R.id.StudioName_OrderDetail);
        TextView studioRating = findViewById(R.id.StudioRating_OrderDetail);
        if (studio.getImage() != null) {
            Picasso.get().load(studio.getImage())
                    .error(R.drawable.download)
                    .placeholder(R.drawable.download)
                    .into(studioAvatar);
        } else {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png")
                    .error(R.drawable.download)
                    .placeholder(R.drawable.download)
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
        totalPrice.setText(totalPriceValue + " VND");

        if (order.getDeposit() != null) {
            deposited.setText(order.getDeposit() + " VND");
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
                case "canceled":
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
                    cancelOrderAction();
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

    private void paidTheRestOrderAction() {
    }

    private void depositOrderAction() {
        
    }

    private void cancelOrderAction() {

//        ApiService.apiService.updateCancelStatus(orderId, "canceled");
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.OrderDetailToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.valueOf("Order Id: " + orderId));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickGoServiceDetail(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openViewImageFeedbackDialog(int gravity, Studio studio, Service service, Button buttonFeedback) {
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
//        studioImage.setImageResource(studio.getImage());
        Picasso.get().load(studio.getImage()).into(studioImage);
        TextView NameStudioFeedback = dialog.findViewById(R.id.NameStudioFeedback);
        NameStudioFeedback.setText(studio.getTitle());

        RatingBar ratingStar = dialog.findViewById(R.id.RatingStarFeedback);
        EditText feedbackFormDescription = dialog.findViewById(R.id.FeedbackFormDescription);
        configEditText(feedbackFormDescription);
//
        uploadImage_Feedback = dialog.findViewById(R.id.UploadImage_Feedback);
        uploadImage_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFeedbackImage();
            }
        });

        Button updateDialog = dialog.findViewById(R.id.SubmitFeedbackDialog);
        updateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkSubmission = submitFeedbackForm(service.getServiceId(),
                        feedbackFormDescription.getText().toString(),
                        ratingStar.getRating(), "");
                if (checkSubmission) {
//                    buttonFeedback.setBackgroundResource(R.color.colorAccent);
                    buttonFeedback.setEnabled(false);
                    buttonFeedback.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                }
            }
        });

        Button closeBtn = dialog.findViewById(R.id.CancelFeedbackDialog);
        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    private Boolean submitFeedbackForm(int serviceId, String Description, float rating, String urlImage) {
        return true;
    }
    private void uploadFeedbackImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // The user has successfully picked an image from the gallery.
            // You can retrieve the image URI or perform further operations here.

            // Example: Retrieving the image URI
//            String imageUri = data.getData().toString();
//            Uri uri = data.getData();
//            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            String url = "https://i.imgur.com/DvpvklR.png";
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(uploadImage_Feedback);

        }
    }

    private void configEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
}