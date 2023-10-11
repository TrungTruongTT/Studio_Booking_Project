package com.example.demofacebook;

import android.app.Dialog;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Adapter.Chat.Booking.OrderDetailAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.BookingGroupItem;
import com.example.demofacebook.Model.OrderDetail;
import com.example.demofacebook.Model.SlotBookingItem;
import com.example.demofacebook.Model.Studio;

import java.text.NumberFormat;
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
    private String orderStatus;

    ExpandableListView expandableListView;
    List<BookingGroupItem> mGroupList;
    Map<BookingGroupItem, List<SlotBookingItem>> mListItems;
    OrderDetailAdapter orderDetailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initToolBar();

        expandableListView = findViewById(R.id.ExpandableOrderDetailBooking);
        mListItems = getListItems();
        mGroupList = new ArrayList<>(mListItems.keySet());

        orderDetailAdapter = new OrderDetailAdapter(mGroupList, mListItems, new IClickItemFeedbackOrderDetailListener() {
            @Override
            public void onClickItemFeedbackOrderDetail(OrderDetail orderDetail, Button buttonFeedback) {
                openFeedbackDialog(Gravity.CENTER, studio, buttonFeedback);
            }
        }, getApplicationContext(), "1");
        expandableListView.setAdapter(orderDetailAdapter);

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

    private Map<BookingGroupItem, List<SlotBookingItem>> getListItems() {
        Map<BookingGroupItem, List<SlotBookingItem>> listMap = new HashMap<>();

        BookingGroupItem groupItem = new BookingGroupItem(1, "https://static.wikia.nocookie.net/avatar/images/9/91/Avatar_Studios.png/revision/latest?cb=20210225004145" +
                "s.io%2Fwp-content%2Fuploads%2Fsites%2F6%2F2021%2F02%2F23%2FAvatar_Studios_logo_large_02_22_21.jpg", "Avatar", "Avatar Studio", "10-10-2023");

        List<SlotBookingItem> items = new ArrayList<>();
        items.add(new SlotBookingItem(1, " 8:00 - 10:00"));
        items.add(new SlotBookingItem(2, "10:00 - 12:00"));
        items.add(new SlotBookingItem(3, "12:00 - 14:00"));

        listMap.put(groupItem, items);

        return listMap;
    }

    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }


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


    private void openFeedbackDialog(int gravity, Studio studio, Button buttonFeedback) {
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
//        Picasso.get().load(studio.getImage()).into(studioImage);
//        TextView NameStudioFeedback = dialog.findViewById(R.id.NameStudioFeedback);
//        NameStudioFeedback.setText(studio.getTitle());

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
                    boolean checkSubmission = submitFeedbackForm(description, ratingValue);
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

    //, OrderDetail orderDetailValue
    private Boolean submitFeedbackForm(String description, float ratingValue) {
//        int rating = (int) ratingValue;
//        OrderDetail feedback = new OrderDetail(rating, description);
//        SendFeedBack(orderDetailValue.getOrderDetailId(), feedback);
        return true;
    }

    private void SendFeedBack(int id, OrderDetail orderDetail) {
        Log.w("updateData: ", id + "");
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