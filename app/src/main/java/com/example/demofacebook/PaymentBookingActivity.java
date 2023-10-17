package com.example.demofacebook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;

public class PaymentBookingActivity extends AppCompatActivity {
    Button btn_Continue;
    Button btn_Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_booking);
        initToolBar();

        btn_Continue = findViewById(R.id.btn_DonePayment);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), PaymentSuccessful.class);
                startActivity(intent);
            }
        });
        btn_Cancel = findViewById(R.id.btn_CancelPaymentBooking);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });

        TextView textForCopy = findViewById(R.id.tv_TextForCopy);
        String text = getOrderId() + "_" + DataLocalManager.getCustomerAccount().getPhone();
        textForCopy.setText(text);
        textForCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Description to clipboard", textForCopy.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentBookingActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });


        TextView bankNumber = findViewById(R.id.tv_BankNumber);
        bankNumber.setText("0966324244");
        bankNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Bank Number to clipboard ", bankNumber.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(PaymentBookingActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int getOrderId() {
        if (getIntent().getExtras() != null) {
            int orderId = (int) getIntent().getExtras().get("orderId");
            return orderId;
        }
        return 0;
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarPaymentBookingStudioActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Payment Order");
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
