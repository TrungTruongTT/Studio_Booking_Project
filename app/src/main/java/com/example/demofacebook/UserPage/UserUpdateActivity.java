package com.example.demofacebook.UserPage;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;

import java.sql.Date;

public class UserUpdateActivity extends AppCompatActivity {
    User user;
    ImageView userImage;
    TextView userName;
    TextView birthDate;
    TextView phone;
    TextView email;
    TextView password;
    Button btnOpenUpdateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_update);
        userImage = findViewById(R.id.UserImage);
        userName = findViewById(R.id.UserName);
        birthDate = findViewById(R.id.UserDOB);
        phone = findViewById(R.id.UserPhone);
        email = findViewById(R.id.UserEmail);
        password = findViewById(R.id.UserPassword);
        btnOpenUpdateDialog = findViewById(R.id.Update);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateUserDialog(Gravity.TOP);
                loadUserInfo();
            }
        });


        initToolBar();
        loadUserInfo();
    }

    private void openUpdateUserDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update_user);
        Window window = dialog.getWindow();
        if (window == null) {
            Toast.makeText(this, "Loix", Toast.LENGTH_SHORT).show();
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        ImageView userImage = dialog.findViewById(R.id.UserImage);
        EditText editTextUserName = dialog.findViewById(R.id.UserName);
        EditText editTextDay = dialog.findViewById(R.id.UserDay);
        EditText editTextMonth = dialog.findViewById(R.id.UserMonth);
        EditText editTextYear = dialog.findViewById(R.id.UserYear);
        EditText editTextPhone = dialog.findViewById(R.id.UserPhone);
        EditText editTextEmail = dialog.findViewById(R.id.UserEmail);
        EditText editTextPassword = dialog.findViewById(R.id.UserPassword);
        //set data to edit text
        userImage.setImageResource(user.getImage());
        editTextUserName.setText(user.getName());


        String str = user.getDateOfBirth().toString();
        String[] arrOfStr = str.split("-", 3);
        editTextDay.setText(arrOfStr[2]);
        editTextMonth.setText(arrOfStr[1]);
        editTextYear.setText(arrOfStr[0]);
        editTextPhone.setText(user.getPhone());
        editTextEmail.setText(user.getEmail());
        editTextPassword.setText(user.getPassword());

        //set up 2 button cancel and update
        Button btnOpenUpdateDialog = dialog.findViewById(R.id.UpdateDialog);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(editTextUserName.getText().toString());

                int day = Integer.valueOf(editTextDay.getText().toString());
                int month = Integer.valueOf(editTextMonth.getText().toString());
                int year = Integer.valueOf(editTextYear.getText().toString());


                String str = year + "-" + month + "-" + day;
                Date dateOfBirth = Date.valueOf(str);
                user.setDateOfBirth(dateOfBirth);
                user.setPhone(editTextPhone.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());

                updateUserInfo();

                Toast.makeText(UserUpdateActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        Button btnOpenCancelDialog = dialog.findViewById(R.id.CancelDialog);
        btnOpenCancelDialog.setOnClickListener(view -> dialog.dismiss());


        dialog.show();
    }


    private void loadUserInfo() {
        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().get("User");
            if (user != null) {
                userImage.setImageResource(user.getImage());
                userName.setText(user.getName());
                birthDate.setText(user.getDateOfBirth().toString());
                phone.setText(user.getPhone());
                email.setText(user.getEmail());
                password.setText(user.getPassword());
            }
            if (user == null) {
                Toast.makeText(this, "Load User Fail", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void updateUserInfo() {
        if (user != null) {
            userImage.setImageResource(user.getImage());
            userName.setText(user.getName());
            birthDate.setText(user.getDateOfBirth().toString());
            phone.setText(user.getPhone());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
        if (user == null) {
            Toast.makeText(this, "Load User Fail", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.UserToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.AppBarColor)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("User Information");
        }
    }

    private void onImageClick() {
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserUpdateActivity.this, ":oke", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}