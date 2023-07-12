package com.example.demofacebook.Fragment.MainPageFragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.UserPage.ItemUserAdapter;
import com.example.demofacebook.Adapter.UserPage.UserAdapter;
import com.example.demofacebook.Model.User;
import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.MyInterface.IClickItemUserOptionListener;
import com.example.demofacebook.R;
import com.example.demofacebook.UserPage.UserUpdateActivity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    User user;
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
        loadUser(view);
        loadUserOption(view);
    }

    private void loadUser(View view) {
        //User Information
        recyclerViewUser = view.findViewById(R.id.RecyclerUser);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUser.setLayoutManager(linearLayoutManagerUser);
        userAdapter = new UserAdapter(new IClickItemUserListener() {
            @Override
            public void onClickItemUser(User user) {
                clickGoOption("Update Information");
            }
        }, getUser());
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
                Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    private User getUser() {
        String str = "2001-06-15";
        Date dateOfBirth = Date.valueOf(str);
        String url = "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg";
        User user = new User(1, url, "PhiPhiPhi", dateOfBirth, "0966324244", "Phinhse150972@fpt.edu.vn", "Phinhse150972");

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

}
