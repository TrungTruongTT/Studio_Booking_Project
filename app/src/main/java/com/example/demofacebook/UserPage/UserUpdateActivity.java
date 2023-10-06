package com.example.demofacebook.UserPage;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.HomePage.HomeActivity;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.Login_Request;
import com.example.demofacebook.Model.TokenResponse;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdateActivity extends AppCompatActivity {
    User user;
    ImageView userImage;
    ImageView userImageDialog;
    TextView userName;
    TextView phone;
    TextView email;
    TextView password;
    Button btnOpenUpdateDialog;
    private static final int GALLERY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        initView();
        initToolBar();
        loadUserInfo();
    }

    private void initView() {
        userImage = findViewById(R.id.UserImage);
        userName = findViewById(R.id.UserName);
        phone = findViewById(R.id.UserPhone);
        email = findViewById(R.id.UserEmail);
        password = findViewById(R.id.UserPassword);
        btnOpenUpdateDialog = findViewById(R.id.Update);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnterPasswordDialog(Gravity.TOP, view);
            }
        });
    }

    private void openEnterPasswordDialog(int gravity, View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_password_check);

        Window window = dialog.getWindow();
        if (window == null) {
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
        //Update
        Button btnOpenUpdateDialog = dialog.findViewById(R.id.send_Authentication_button);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText passwordConfirm = dialog.findViewById(R.id.dialogPasswordConfirm);
                String password = passwordConfirm.getText().toString();
                String phone = user.getPhone();
                if (password != null) {
                    Login_Request loginAccount = new Login_Request(phone, password);
                    ApiService.apiServiceGuest.login(loginAccount).enqueue(new Callback<TokenResponse>() {
                        @Override
                        public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                            if (response.isSuccessful()) {
                                TokenResponse tokenResponse = response.body();
                                if (tokenResponse != null) {
                                    openUpdateUserDialog(Gravity.TOP, user);
                             } else {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TokenResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Check Connection", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                }
                dialog.dismiss();
            }
        });

        //Cancel
        Button btnOpenCancelDialog = dialog.findViewById(R.id.cancel_Authentication_button);
        btnOpenCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void openUpdateUserDialog(int gravity, User user) {
        if (user == null) {
            return;
        }
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update_user);

        Window window = dialog.getWindow();
        if (window == null) {
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

        userImageDialog = dialog.findViewById(R.id.UserDialogImage);
        EditText editTextUserName = dialog.findViewById(R.id.UserDialogName);
        configEditText(editTextUserName);

        EditText editTextPassword = dialog.findViewById(R.id.UserDialogPassword);
        EditText editTextRePassword = dialog.findViewById(R.id.UserDialogRePassword);


        Button buttonUploadPicture = dialog.findViewById(R.id.UploadAvtarImage);
        buttonUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadUserAvatarImage();
            }
        });

        //set data to edit text
        Picasso.get()
                .load(user.getImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(userImageDialog);
        editTextUserName.setText(user.getFullName());
        editTextPassword.setText(user.getPassword());
        editTextRePassword.setText(user.getPassword());

        //set up 2 button cancel and update

        //Update
        Button btnOpenUpdateDialog = dialog.findViewById(R.id.UpdateDialog);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextUserName.getText().toString();
                String url = "https://i.imgur.com/DvpvklR.png";
                String password1 = String.valueOf(editTextPassword.getText());
                String password2 = String.valueOf(editTextRePassword.getText());
                Boolean validation = invalidateMenuInput(name, password1, password2);

                if (validation) {
                    CustomerAccount customerAccount = DataLocalManager.getCustomerAccount();
                    customerAccount.getUser().setFullName(name);
                    customerAccount.getUser().setPassword(password1);
                    customerAccount.getUser().setImage(url);
                    updateUser(customerAccount);


                    dialog.dismiss();
                } else {
                    Toast.makeText(UserUpdateActivity.this, "Update UnSuccess", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Cancel
        Button btnOpenCancelDialog = dialog.findViewById(R.id.CancelDialog);
        btnOpenCancelDialog.setOnClickListener(view -> dialog.dismiss());


        dialog.show();
    }
    private void updateUser(CustomerAccount customerAccount) {
        CustomerAccount T =  DataLocalManager.getCustomerAccount();
        User T2 = DataLocalManager.getCustomerAccount().getUser();
        CustomerAccount updateUser =
                new CustomerAccount(customerAccount.getCustomerId(),
                        customerAccount.getAddress(),
                        new User(customerAccount.getUser().getImage()
                                , customerAccount.getUser().getFullName(),
                                customerAccount.getUser().getPassword()));

        Log.w("TAG", updateUser + "");

        Call<Void> call = ApiService.apiService.updateCustomer(updateUser.getCustomerId(), updateUser);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    T2.setFullName(updateUser.getUser().getFullName());
                    T2.setImage(updateUser.getUser().getImage());
                    T2.setPassword(updateUser.getUser().getPassword());
                    T.setUser(T2);
                    DataLocalManager.setCustomerAccount(T);
                    user = DataLocalManager.getCustomerAccount().getUser();
                    updateUserInfo();
                    Log.w("TAG", DataLocalManager.getCustomerAccount().getUser().getFullName());

                    Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                } else {


                    Toast.makeText(getApplicationContext(), "not oke", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Request failed due to network error or other issues
                // Handle error here
            }
        });

    }
    private Boolean invalidateMenuInput(String name, String password1, String password2) {
        if (name.isEmpty()
                || password1.isEmpty()
                || password2.isEmpty()) {
            return false;
        }
        if (!password1.equals(password2)) {
            return false;
        }
        return true;
    }
    private void uploadUserAvatarImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String url = "https://i.imgur.com/DvpvklR.png";
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(userImageDialog);

        }
    }

    private void loadUserInfo() {
        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().get("user");
            if (user != null) {
                Picasso.get()
                        .load(user.getImage())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(userImage);

                userName.setText(user.getFullName());
                phone.setText(user.getPhone());
                email.setText(user.getEmail());
                password.setText("**********");
            }
            if (user == null) {
                Toast.makeText(this, "Load User Fail", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void updateUserInfo() {
        if (user != null) {
            Picasso.get()
                    .load(user.getImage())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(userImage);

            userName.setText(user.getFullName());
            phone.setText(user.getPhone());
            email.setText(user.getEmail());
            password.setText("**********");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.UserToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ToolBar)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("User Information");
        }
    }


    private void configEditText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}