package com.example.demofacebook.UserPage;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.squareup.picasso.Picasso;

import java.sql.Date;

public class UserUpdateActivity extends AppCompatActivity {
    User user;
    ImageView userImage;
    ImageView userImageDialog;
    //    Uri mUriImage;
    TextView userName;
    TextView birthDate;
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
        birthDate = findViewById(R.id.UserDOB);
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
                String token = null, password = null;
                if (checkPassword(token, password)) {
                    openUpdateUserDialog(Gravity.TOP, user);
                } else {
                    return;
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

    private boolean checkPassword(String token, String password) {
        //Api check password
        if (true) {
            return true;
        }
        return false;
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
        EditText editTextDay = dialog.findViewById(R.id.UserDialogDay);
        EditText editTextMonth = dialog.findViewById(R.id.UserDialogMonth);
        EditText editTextYear = dialog.findViewById(R.id.UserDialogYear);

        EditText editTextPassword = dialog.findViewById(R.id.UserDialogPassword);
        configEditPasswordText(editTextPassword);
        EditText editTextRePassword = dialog.findViewById(R.id.UserDialogRePassword);
        configEditPasswordText(editTextRePassword);

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
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(userImageDialog);
        editTextUserName.setText(user.getFullName());
       /* String str = user.getDateOfBirth().toString();*/
        String str = "2001-06-15";
        String[] arrOfStr = str.split("-", 3);
        editTextDay.setText(arrOfStr[2]);
        editTextMonth.setText(arrOfStr[1]);
        editTextYear.setText(arrOfStr[0]);

        editTextPassword.setText(user.getPassword());
        editTextRePassword.setText(user.getPassword());

        //set up 2 button cancel and update

        //Update
        Button btnOpenUpdateDialog = dialog.findViewById(R.id.UpdateDialog);
        btnOpenUpdateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextUserName.getText().toString();
                String day = editTextDay.getText().toString();
                String month = editTextMonth.getText().toString();
                String year = editTextYear.getText().toString();
                String password1 = String.valueOf(editTextPassword.getText());
                String password2 = String.valueOf(editTextRePassword.getText());
                Boolean validation = invalidateMenuInput(name, day, month, year, password1, password2);

                if (validation) {
                    String str = year + "-" + month + "-" + day;
                    Date dateOfBirth = Date.valueOf(str);
//
//                    user.setFullName(editTextUserName.getText().toString());
//                    String url = "https://i.imgur.com/DvpvklR.png";
//                    user.setImage(url);
//                    user.setDateOfBirth(dateOfBirth);
//                    user.setPassword(editTextPassword.getText().toString());
//                    updateUserInfo();
//                    Toast.makeText(UserUpdateActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
//                    CustomerAccount customerAccount = DataLocalManager.getCustomerAccount();
//                    customerAccount.setBirthDate(dateOfBirth);
//                    customerAccount.getUser().setFullName(name);
//                    customerAccount.getUser().setPassword(password1);
//                    updateUser(customerAccount);


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

    }

    private Boolean invalidateMenuInput(String name, String date, String month, String year, String password1, String password2) {
        if (name.isEmpty()
                || date.isEmpty()
                || Integer.parseInt(date) < 0
                || Integer.parseInt(date) > 31
                || month.isEmpty()
                || Integer.parseInt(month) < 0
                || Integer.parseInt(month) > 12
                || year.isEmpty()
                || Integer.parseInt(year) < 1900
                || Integer.parseInt(year) >= 2024
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
            // The user has successfully picked an image from the gallery.
            // You can retrieve the image URI or perform further operations here.

            // Example: Retrieving the image URI
//            String imageUri = data.getData().toString();
//            Uri uri = data.getData();
//            mUriImage = uri;
            String url = "https://i.imgur.com/DvpvklR.png";
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(userImageDialog);

        }
    }

    private void loadUserInfo() {
        if (getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().get("user");
            if (user != null) {
                Picasso.get()
                        .load(user.getImage())
                        .placeholder(R.drawable.download)
                        .error(R.drawable.download)
                        .into(userImage);

                userName.setText(user.getFullName());
                String str = "2001-06-15";
                Date dateOfBirth = Date.valueOf(str);

                birthDate.setText(dateOfBirth.toString());
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
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(userImage);

            userName.setText(user.getFullName());

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

    private void configEditPhoneText(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }
    private void configEditPasswordText(EditText editText) {
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //editText.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}