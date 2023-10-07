package com.example.demofacebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.TimePicker.DatePickerAdapter;
import com.example.demofacebook.Adapter.TimePicker.IClickItemDatePickerListener;
import com.example.demofacebook.Adapter.TimePicker.TimePickerAdapter;
import com.example.demofacebook.HomePage.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class PickTimeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDate;
    private RecyclerView recyclerViewTime;
    private DatePickerAdapter datePickerAdapter;
    private TimePickerAdapter timePickerAdapter;
    private Button btn_Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);
        initToolBar();
        loadDatePickerValue();
        loadTimeAvailable();

        btn_Continue = findViewById(R.id.btn_Continue);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), BookingStudioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadDatePickerValue() {
        List<DatePicker> dataPickerList = new ArrayList<DatePicker>();
        dataPickerList.add(new DatePicker(1, "15", "Oct"));
        dataPickerList.add(new DatePicker(2, "16", "Oct"));
        dataPickerList.add(new DatePicker(3, "17", "Oct"));
        dataPickerList.add(new DatePicker(4, "18", "Oct"));
        dataPickerList.add(new DatePicker(5, "19", "Oct"));
        recyclerViewDate = findViewById(R.id.date_picker_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDate.setLayoutManager(linearLayoutManager);
        datePickerAdapter = new DatePickerAdapter(dataPickerList, new IClickItemDatePickerListener() {
            @Override
            public void onItemDatePickerListener(DatePicker datePicker) {
//                timePickerAdapter.getFilter().filter(datePicker.getDate());
            }
        });
        recyclerViewDate.setAdapter(datePickerAdapter);

    }

    private void loadTimeAvailable() {
        List<TimePicker> timePickers = new ArrayList<TimePicker>();
        timePickers.add(new TimePicker(1, "Test Studio", "https://artriva.com/images/portfolio/studio-b/studio-b-live.jpg",
                "Test Studio", 120000000, "7:00 - 9:00"));
        timePickers.add(new TimePicker(2, "Test Studio 1", "https://artriva.com/images/portfolio/studio-b/studio-b-live.jpg",
                "Test Studio", 120000000, "9:00 - 11:00"));
        timePickers.add(new TimePicker(3, "Test Studio 2", "https://artriva.com/images/portfolio/studio-b/studio-b-live.jpg",
                "Test Studio", 120000000, "11:00 - 13:00"));
        timePickers.add(new TimePicker(4, "Test Studio 3", "https://artriva.com/images/portfolio/studio-b/studio-b-live.jpg",
                "Test Studio", 120000000, "13:00 - 15:00"));
        timePickers.add(new TimePicker(5, "Test Studio 4", "https://artriva.com/images/portfolio/studio-b/studio-b-live.jpg",
                "Test Studio", 120000000, "15:00 - 17:00"));
        recyclerViewTime = findViewById(R.id.time_picker_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTime.setLayoutManager(linearLayoutManager);
        timePickerAdapter = new TimePickerAdapter(timePickers);
        recyclerViewTime.setAdapter(timePickerAdapter);
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
}