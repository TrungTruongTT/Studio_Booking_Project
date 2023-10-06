package com.example.demofacebook.Fragment.MainPageFragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.UserPage.ItemUserAdapter;
import com.example.demofacebook.Adapter.UserPage.UserAdapter;
import com.example.demofacebook.Login.LoginActivity;
import com.example.demofacebook.Login.MainActivity;
import com.example.demofacebook.Model.CustomerAccount;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.MyInterface.IClickItemUserOptionListener;
import com.example.demofacebook.R;
import com.example.demofacebook.Ultils.ShareReference.DataLocalManager;
import com.example.demofacebook.UserPage.UserUpdateActivity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    User user;
    RecyclerView recyclerViewUser;
    UserAdapter userAdapter;
    RecyclerView recyclerViewUserOption;
    ItemUserAdapter itemUserAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = getUser();
        loadUser(view, user);
        loadUserOption(view);
        Button btn_Logout = view.findViewById(R.id.btn_Logout);
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.setCustomerAccount(null);
                DataLocalManager.setTokenResponse(null);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void loadUser(View view, User userValue) {
        //User Information
        recyclerViewUser = view.findViewById(R.id.RecyclerUser);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUser.setLayoutManager(linearLayoutManagerUser);
        userAdapter = new UserAdapter(new IClickItemUserListener() {
            @Override
            public void onClickItemUser(User user) {
                clickGoOption("Edit Profile");
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
        }, getAccountOption(), getListAccountOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);

        //User Option 2
        recyclerViewUserOption = view.findViewById(R.id.RecyclerSetting);
        LinearLayoutManager linearLayoutManagerOption2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption2);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
            }
        }, getListOptionMore(), getListMoreOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);
    }

    private void clickGoOption(String option) {
        if (option.equals("Edit Profile")) {
            Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (option.equals("Password")) {
            Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private User getUser() {
        User user;
        String str = "2001-06-15";
        Date dateOfBirth = Date.valueOf(str);
        //String url = "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg";
        String url = "https://cdn-icons-png.flaticon.com/512/1144/1144760.png";

        //TokenResponse token = DataLocalManager.getTokenResponse();
        CustomerAccount account = DataLocalManager.getCustomerAccount();
        if (account != null) {
            user = new User(account.getUser().getUserId(), account.getUser().getImage(), account.getUser().getFullName(), dateOfBirth, account.getUser().getPhone(), account.getUser().getEmail(), account.getUser().getPassword());
        } else {
            user = new User(1, url, "PhiPhiPhi", dateOfBirth, "0966324244", "Phinhse150972@fpt.edu.vn", "Phinhse150972");
        }
        return user;
        }

    private List<String> getAccountOption() {
        List<String> myList = new ArrayList<>();
        String[] optionName = {"Edit Profile", "Password", "Notification"};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    private List<String> getListOptionMore() {
        List<String> myList = new ArrayList<>();
        String[] optionName = {"Rate & Review", "Help"};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    private List<Drawable> getListAccountOptionIcon() {
        List<Drawable> myList = new ArrayList<>();
        Drawable editProfile = getContext().getResources().getDrawable(R.drawable.baseline_account_circle_24);
        Drawable Password = getContext().getResources().getDrawable(R.drawable.baseline_lock_24);
        Drawable Notifications = getContext().getResources().getDrawable(R.drawable.baseline_notifications_none_24);
        Drawable[] optionName = {editProfile, Password, Notifications};
        for (int i = 0; i < optionName.length; i++) {
            myList.add(optionName[i]);
        }
        return myList;
    }

    private List<Drawable> getListMoreOptionIcon() {
        List<Drawable> myList = new ArrayList<>();
        Drawable rate = getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24);
        Drawable help = getContext().getResources().getDrawable(R.drawable.baseline_question_mark_24);
        Drawable[] optionName = {rate, help};
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
