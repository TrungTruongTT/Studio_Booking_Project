package com.example.demofacebook.Fragment.MainPageFragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.UserPage.ItemUserAdapter;
import com.example.demofacebook.Adapter.UserPage.UserAdapter;

import com.example.demofacebook.Api.ApiService;
import com.example.demofacebook.Model.Service;

import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.TokenResponse;

import com.example.demofacebook.Model.User;
import com.example.demofacebook.Model.UserByPhone;
import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.MyInterface.IClickItemUserOptionListener;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.example.demofacebook.UserPage.UserUpdateActivity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    User user;
    UserByPhone userByPhone;
    //User RecyclerView
    RecyclerView recyclerViewUser;
    UserAdapter userAdapter;
    //User Option RecyclerView
    RecyclerView recyclerViewUserOption;
    ItemUserAdapter itemUserAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = getUser();
        loadUser(view, user);
//        Log.d("D","Check");
//        ApiService.apiService.getUserByPhoneOrEmail("0369998759").enqueue(new Callback<UserByPhone>() {
//            @Override
//            public void onResponse(Call<UserByPhone> call, Response<UserByPhone> response) {
//                if (response.isSuccessful()) {
//                    userByPhone = response.body();
//                    user = userByPhone.getUser();
//                    loadUser(view, user);
//                   Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "oke r", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<UserByPhone> call, Throwable t) {
//                Toast.makeText(getContext(), "oke r 222", Toast.LENGTH_SHORT).show();
//            }
//        });


        loadUserOption(view);
    }



    private void loadUser(View view, User userValue) {
        //User Information
        recyclerViewUser = view.findViewById(R.id.RecyclerUser);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUser.setLayoutManager(linearLayoutManagerUser);
        userAdapter = new UserAdapter(new IClickItemUserListener() {
            @Override
            public void onClickItemUser(User user) {
                clickGoOption("Update Information");
            }
        }, userValue);
        recyclerViewUser.setAdapter(userAdapter);
    }

    private void loadUserOption(View view) {
        //User Option 1
        recyclerViewUserOption = view.findViewById(R.id.Recycler1);
        LinearLayoutManager linearLayoutManagerOption = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
                clickGoOption(option);
            }
        }, getListOptionName1(), getListOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);

        //User Option 2
        recyclerViewUserOption = view.findViewById(R.id.RecyclerSetting);
        LinearLayoutManager linearLayoutManagerOption2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption2);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
            }
        }, getListOptionName2(), getListOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);
    }

    private void clickGoOption(String option) {
        if (option.equals("Update Information")) {
            Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
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
                    Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
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

    private User getUser() {
        User user;
        String str = "2001-06-15";
        Date dateOfBirth = Date.valueOf(str);
        String url = "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg";
        //TokenResponse token = DataLocalManager.getTokenResponse();
        CustomerAccount account = DataLocalManager.getCustomerAccount();
        if (account != null) {
            user = new User(account.getUser().getUserId(), account.getUser().getImage(), account.getUser().getFullName(), dateOfBirth, account.getUser().getPhone(), account.getUser().getEmail(), account.getUser().getPassword());
        } else {
            user = new User(1, url, "PhiPhiPhi", dateOfBirth, "0966324244", "Phinhse150972@fpt.edu.vn", "Phinhse150972");
        }
            return user;
        }

    private List<String> getListOptionName1() {
        List<String> myList = new ArrayList<>();
        String[] optionName = {"Update Information", "Option 2"};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    private List<String> getListOptionName2() {
        List<String> myList = new ArrayList<>();
        String[] optionName = {"Option 1", "Option 2"};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    private List<Drawable> getListOptionIcon() {
        List<Drawable> myList = new ArrayList<>();
        Drawable img = getContext().getResources().getDrawable(R.drawable.baseline_settings_24);
        Drawable[] optionName = {img, img};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }
}
