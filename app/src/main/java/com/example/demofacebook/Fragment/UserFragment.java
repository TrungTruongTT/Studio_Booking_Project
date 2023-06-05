package com.example.demofacebook.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demofacebook.Adapter.ItemUserAdapter;
import com.example.demofacebook.Adapter.UserAdapter;
import com.example.demofacebook.HomePage.CategoryHomeAdapter;
import com.example.demofacebook.HomePage.StudioHomeAdapter;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemStudioListener;
import com.example.demofacebook.MyInterface.IClickItemUserListener;
import com.example.demofacebook.MyInterface.IClickItemUserOptionListener;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    //User RecyclerView
    private RecyclerView recyclerViewUser;
    private UserAdapter userAdapter;
    //User Option RecyclerView
    private RecyclerView recyclerViewUserOption;
    private ItemUserAdapter itemUserAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
            public void onClickItemUser(String user) {
                Toast.makeText(getActivity(), "UserSelected", Toast.LENGTH_SHORT).show();
            }
        }, getUserImage(), getUserName());
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

        //User Option Resource
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    private List<String> getUserName() {
        List<String> myList = new ArrayList<>();
        String[] userName = {"PhiPhiPhi"};
        for (int i = 0; i < userName.length; i++) {
            myList.add(userName[i]);
        }
        return myList;
    }

    private List<Integer> getUserImage() {
        List<Integer> myList = new ArrayList<>();
        Integer[] userImage = {R.drawable.download};
        for (int i = 0; i < userImage.length; i++) {
            myList.add(userImage[i]);
        }
        return myList;
    }

    private List<String> getListOptionName() {
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
