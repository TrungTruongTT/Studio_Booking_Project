package com.example.demofacebook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.TimePicker.TimePickerAdapter;
import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.SlotBooking;
import com.example.demofacebook.Model.Studio;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickTimeActivity extends AppCompatActivity {
    private Studio studio;
    private RecyclerView recyclerViewTime;
    private TimePickerAdapter timePickerAdapter;
    private Button btn_Continue;
    private Button btn_PickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);
        initToolBar();
        studio = loadStudio();

        recyclerViewTime = findViewById(R.id.time_picker_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewTime.setLayoutManager(linearLayoutManager);


        btn_Continue = findViewById(R.id.btn_Continue);
        btn_Continue.setEnabled(false);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SlotBooking> bookings = timePickerAdapter.getCheckedItems();
                if (bookings.isEmpty()) {
                    Toast.makeText(PickTimeActivity.this, "Please Select Slot You Want", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplication(), BookingStudioActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studio", studio);
                    bundle.putSerializable("bookingSlot", (Serializable) bookings);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        btn_PickDate = findViewById(R.id.btn_PickDate);
        btn_PickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPickDayDialog(Gravity.CENTER);
            }
        });
    }

    private void loadTimeAvailable(int studioId, String date) {
        Call<List<SlotBooking>> call = ApiService.apiService.getSlotByStudioId(studioId, date);
        call.enqueue(new Callback<List<SlotBooking>>() {
            @Override
            public void onResponse(Call<List<SlotBooking>> call, Response<List<SlotBooking>> response) {
                if (response.isSuccessful()) {
                    List<SlotBooking> slotBookings = response.body();
                    if (slotBookings.isEmpty()) {
                        Toast.makeText(PickTimeActivity.this, "No Slot Available on " + date, Toast.LENGTH_SHORT).show();
                        btn_Continue.setEnabled(false);
                        timePickerAdapter = new TimePickerAdapter(slotBookings, studio);
                        recyclerViewTime.setAdapter(timePickerAdapter);
                    } else {
                        btn_Continue.setEnabled(true);
                        timePickerAdapter = new TimePickerAdapter(slotBookings, studio);
                        recyclerViewTime.setAdapter(timePickerAdapter);
                    }
                } else {
                    Toast.makeText(PickTimeActivity.this, "Load Slot Book Fails", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SlotBooking>> call, Throwable t) {
                // Request failed due to network error or other issues
                // Handle error here
            }
        });
    }

    private void openPickDayDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_pickdate);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        DatePicker c = dialog.findViewById(R.id.DatePicker_Calender);

        Button save = dialog.findViewById(R.id.btn_SaveDate);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = c.getYear();
                int month = c.getMonth();
                int day = c.getDayOfMonth();
                btn_PickDate.setText("Day: " + day + " - " + (month + 1) + " - " + year);
                loadTimeAvailable(studio.getStudioId(), year + "-" + (month + 1) + "-" + day);
                dialog.dismiss();
            }
        });
        Button closeBtn = dialog.findViewById(R.id.btn_CancelPickDate);
        closeBtn.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarPickTimeActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Schedule");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Studio loadStudio() {
        if (getIntent().getExtras() != null) {
            Studio studio = (Studio) getIntent().getExtras().get("studio");
            if (studio != null) {
                return studio;
            }
        }
        return null;
    }
}