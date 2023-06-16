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
    private RecyclerView recyclerViewUser;
    private UserAdapter userAdapter;
    //User Option RecyclerView
    private RecyclerView recyclerViewUserOption;
    private ItemUserAdapter itemUserAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = getUser();
        loadUser();
        loadUserOption();

    }

    private void loadUser() {
        //User Information
        recyclerViewUser = getActivity().findViewById(R.id.RecyclerUser);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUser.setLayoutManager(linearLayoutManagerUser);
        userAdapter = new UserAdapter(new IClickItemUserListener() {
            @Override
            public void onClickItemUser(User user) {
                Toast.makeText(getActivity(), user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        }, getUser(), getContext());
        recyclerViewUser.setAdapter(userAdapter);
    }

    private void loadUserOption() {
        //User Option 1
        recyclerViewUserOption = getActivity().findViewById(R.id.Recycler1);
        LinearLayoutManager linearLayoutManagerOption = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
                Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
                clickGoOption(option);
            }
        }, getListOptionName(), getListOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);

        //User Option 2
        recyclerViewUserOption = getActivity().findViewById(R.id.RecyclerSetting);
        LinearLayoutManager linearLayoutManagerOption2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption2);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
                Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
            }
        }, getListOptionName(), getListOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);

        //User Option 3
        recyclerViewUserOption = getActivity().findViewById(R.id.RecyclerResource);
        LinearLayoutManager linearLayoutManagerOption3 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewUserOption.setLayoutManager(linearLayoutManagerOption3);
        itemUserAdapter = new ItemUserAdapter(new IClickItemUserOptionListener() {
            @Override
            public void onClickItemUserOptionListener(String option) {
                Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();


            }
        }, getListOptionName(), getListOptionIcon());
        recyclerViewUserOption.setAdapter(itemUserAdapter);
    }

    private void clickGoOption(String option) {
        if (option.equals("Update Information")) {
            Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("User", user);
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
        Integer userId = 1;
        Integer userImage = R.drawable.download;
        String userName = "PhiPhiPhi";
        String str = "2001-06-15";
        Date dateOfBirth = Date.valueOf(str);

        String phone = "0966324244";
        String email = "Phinhse150972@fpt.edu.vn";
        String password = "Phinhse150972";


        User user = new User(userId, userImage, userName, dateOfBirth, phone, email, password);

        return user;
    }


    private List<String> getListOptionName() {
        List<String> myList = new ArrayList<>();
        String[] optionName = {"Update Information", "Option 2"};
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
