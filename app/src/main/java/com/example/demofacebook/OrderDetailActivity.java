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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Adapter.Favorite.BookingPageFragment.Interface.Booking.OrderDetailAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.BookingGroupItem;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.OrderInformation;
import com.example.demofacebook.Model.SlotBookingItem;
import com.example.demofacebook.Model.Studio;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetailActivity extends AppCompatActivity {
    private Studio studio;
    private int orderId;
    private OrderInformation orderInformation;

    ExpandableListView expandableListView;
    List<BookingGroupItem> mGroupList;
    Map<BookingGroupItem, List<SlotBookingItem>> mListItems;
    OrderDetailAdapter orderDetailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getData();
        initToolBar();

        initData();

        expandableListView = findViewById(R.id.ExpandableOrderDetailBooking);
        mListItems = getListItems();
        mGroupList = new ArrayList<>(mListItems.keySet());

        orderDetailAdapter = new OrderDetailAdapter(mGroupList, mListItems, new IClickItemFeedbackOrderDetailListener() {
            @Override
            public void onClickItemFeedbackOrderDetail(int orderDetailId, Button buttonFeedback) {
                openFeedbackDialog(Gravity.CENTER, studio, buttonFeedback, orderDetailId);
            }
        }, getApplicationContext(), orderInformation.getStatus(), orderInformation.getOrderDetail());
        expandableListView.setAdapter(orderDetailAdapter);

    }

    private void initData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        TextView bookingPrice = findViewById(R.id.tv_OrderDetail_BookingPrice);
//        TextView fees = findViewById(R.id.tv_OrderDetail_FeesPrice);
        TextView totalPrice = findViewById(R.id.tv_OrderDetail_TotalPrice);

        int _totalPrice = 0;
        for (int i = 0; i < orderInformation.getOrderDetail().size(); i++) {
            _totalPrice = _totalPrice + orderInformation.getOrderDetail().get(i).getPrice();
        }

        bookingPrice.setText(numberFormat.format(_totalPrice) + "VND");

        int roundedPrice = customRound(_totalPrice * 0.05);
//        fees.setText(numberFormat.format(roundedPrice) + "VND");
//        totalPrice.setText(numberFormat.format(roundedPrice + _totalPrice) + "VND");
        totalPrice.setText(numberFormat.format(_totalPrice) + "VND");
    }

    private void getData() {
        if (getIntent().getExtras() != null) {
            orderInformation = (OrderInformation) getIntent().getExtras().get("orderInformation");
            studio = orderInformation.getStudio();
            orderId = orderInformation.getOrderId();
        }
        return;
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

    public static int customRound(double number) {
        double fractionalPart = number - (int) number;

        if (fractionalPart >= 0.5) {
            return (int) Math.round(number);
        } else {
            return (int) Math.floor(number);
        }
    }

    private Map<BookingGroupItem, List<SlotBookingItem>> getListItems() {
        Map<BookingGroupItem, List<SlotBookingItem>> listMap = new HashMap<>();
        BookingGroupItem groupItem = new BookingGroupItem(orderInformation.getOrderId(), studio.getAvatarStudio(), studio.getName(), studio.getName(),
                orderInformation.getOrderDetail().get(0).getStartTime());

        List<SlotBookingItem> items = new ArrayList<>();
        for (int i = 0; i < orderInformation.getOrderDetail().size(); i++) {
            OrderDetail t = orderInformation.getOrderDetail().get(i);

            String starTime = t.getStartTime();
            String endTime = t.getEndTime();
            items.add(new SlotBookingItem(t.getOrderDetailId(), starTime + " - " + endTime, t.getRating(), t.getContent(), t.getPostDate()));
        }


        listMap.put(groupItem, items);

        return listMap;
    }

    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }

//    public String formatDateString(String string) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//            java.util.Date utilDate = sdf.parse(string);
//            SimpleDateFormat outputSdf = new SimpleDateFormat("EEE, dd - MM - yyyy");
//            String formattedDate = outputSdf.format(utilDate);
//            return formattedDate;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public String formatTimeString(String string) {
//        ZonedDateTime zonedDateTime = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            zonedDateTime = ZonedDateTime.parse(string);
//        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            String timeOnly = zonedDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
//            return timeOnly;
//        }
//        return null;
//    }

//    void confirmDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Cancel order " + orderId + " ?");
//        builder.setMessage("Are you sure you want to cancel " + orderId + " ?");
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                cancelOrderAction();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void openFeedbackDialog(int gravity, Studio studio, Button buttonFeedback, int orderDetailId) {
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
        Picasso.get().load(studio.getAvatarStudio()).into(studioImage);
        TextView NameStudioFeedback = dialog.findViewById(R.id.NameStudioFeedback);
        NameStudioFeedback.setText(studio.getName());

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
                    boolean checkSubmission = submitFeedbackForm(orderDetailId, description, ratingValue);
                    if (checkSubmission) {
                        Toast.makeText(OrderDetailActivity.this, "Thank You", Toast.LENGTH_SHORT).show();
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
    private Boolean submitFeedbackForm(int OrderDetailId, String description, float ratingValue) {
        int rating = (int) ratingValue;
        OrderDetail feedback = new OrderDetail(rating, description);
        SendFeedBack(OrderDetailId, feedback);
        return true;
    }

    private void SendFeedBack(int OrderDetailId, OrderDetail orderDetail) {
        Call<OrderDetail> call = ApiService.apiService.createFeedback(OrderDetailId, orderDetail);
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
                Toast.makeText(OrderDetailActivity.this, "Send Feedback Fail Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
}