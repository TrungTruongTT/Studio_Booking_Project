package com.example.demofacebook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.example.demofacebook.Adapter.Booking.OrderDetailAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemFeedbackOrderDetailListener;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderDetailListener;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private Studio studio;
    private int orderId;
    private int orderStatus;
    private RecyclerView recyclerView;
    private OrderDetailAdapter orderDetailAdapter;
    private List<Service> mList;
    //Upload Image
    private static final int GALLERY_REQUEST_CODE = 123;
    ImageView uploadImage_Feedback;
    Button cancalOrderBtn;
    Button repurchaseOrderBtn;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        cancalOrderBtn = findViewById(R.id.CancelOrderBtn);
        repurchaseOrderBtn = findViewById(R.id.RepurchaseBtn);
        //Load Studio, OrderId
        loadData();
        //Init ToolBar
        initToolBar();
        //LoadServiceList
        loadServiceList();

    }

    private void loadServiceList() {
        recyclerView = findViewById(R.id.orderDetailRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = getOrderDetailData();
        orderDetailAdapter = new OrderDetailAdapter(this, mList, new IClickItemOrderDetailListener() {
            @Override
            public void onClickItemOrderDetail(Service service) {
                //click on service
                onClickGoServiceDetail(service);
            }

        }, new IClickItemFeedbackOrderDetailListener() {
            @Override
            public void onClickItemFeedbackOrderDetail(Service service, Button button) {
                //click on feedback btn
                Toast.makeText(OrderDetailActivity.this, "feedback " + String.valueOf(service.getServiceId()), Toast.LENGTH_SHORT).show();
                openViewImageFeedbackDialog(Gravity.CENTER, studio, service, button);
            }
        }, orderStatus);
        recyclerView.setAdapter(orderDetailAdapter);
    }

    private void loadData() {
        if (getIntent().getExtras() != null) {
//            studio = (Studio) getIntent().getExtras().get("studio");
            studio = new Studio(1, R.drawable.download, "Studio 122", 40, 5);
            orderId = (int) getIntent().getExtras().get("orderId");
            orderStatus = (int) getIntent().getExtras().get("orderStatus");


            switch (orderStatus) {
                case 1:
                    cancalOrderBtn.setEnabled(true);
                    repurchaseOrderBtn.setVisibility(View.VISIBLE);
                    repurchaseOrderBtn.setEnabled(false);
                    repurchaseOrderBtn.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                case 3:
                    cancalOrderBtn.setEnabled(false);
                    cancalOrderBtn.setVisibility(View.INVISIBLE);
                    repurchaseOrderBtn.setEnabled(true);
                    repurchaseOrderBtn.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.OrderDetailToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.valueOf("Order Number: " + orderId + " Order Status: " + orderStatus));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Service> getOrderDetailData() {
        List<Service> myList = new ArrayList<>();
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(2, R.drawable.download, 4, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(3, R.drawable.download, 4, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 4, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 4, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
    }

    private void onClickGoServiceDetail(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 122", 40, 5);
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
        studioImage.setImageResource(studio.getImage());

        TextView NameStudioFeedback = dialog.findViewById(R.id.NameStudioFeedback);
        NameStudioFeedback.setText(studio.getTitle());

        RatingBar ratingStar = dialog.findViewById(R.id.RatingStarFeedback);
        EditText feedbackFormDescription = dialog.findViewById(R.id.FeedbackFormDescription);
        configEditText(feedbackFormDescription);

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
                    buttonFeedback.setBackgroundResource(R.color.colorAccent);
                }
            }
        });

        Button closeBtn = dialog.findViewById(R.id.CancelFeedbackDialog);
        closeBtn.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    private Boolean submitFeedbackForm(int serviceId, String Description, float rating, String urlImage) {
        Toast.makeText(this, serviceId + Description + "rating " + rating + urlImage, Toast.LENGTH_SHORT).show();
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
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                uploadImage_Feedback.setImageBitmap(bitmap);
                //Glide.with(this).load(uri).into(uploadImage_Feedback);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void configEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

}