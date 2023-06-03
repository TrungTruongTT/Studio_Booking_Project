package com.example.demofacebook.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.HomePage.CategoryHomeAdapter;
import com.example.demofacebook.HomePage.SortHomeAdapter;
import com.example.demofacebook.HomePage.StudioHomeAdapter;
import com.example.demofacebook.Model.Category;
import com.example.demofacebook.R;
import com.example.demofacebook.Model.Studio;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    //Studio
    private RecyclerView recyclerViewStudio;
    private StudioHomeAdapter studioHomeAdapter;

    //category
    private RecyclerView recyclerViewCategory;
    private CategoryHomeAdapter categoryHomeAdapter;

    //sort
    private RecyclerView recyclerViewSort;
    private SortHomeAdapter sortHomeAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Sort list home page
        recyclerViewSort = view.findViewById(R.id.RecyclerSort);
        sortHomeAdapter = new SortHomeAdapter(getActivity());
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL ,false);
        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);

        recyclerViewSort.setFocusable(false);
        recyclerViewSort.setNestedScrollingEnabled(false);

        sortHomeAdapter.SetData(getSortData());
        recyclerViewSort.setAdapter(sortHomeAdapter);

        //Category list home page
        recyclerViewCategory = view.findViewById(R.id.RecyclerViewCategory);
        categoryHomeAdapter = new CategoryHomeAdapter(getActivity());
        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL ,false);
        recyclerViewCategory.setLayoutManager(linearLayoutManagerCategory);

        recyclerViewCategory.setFocusable(false);
        recyclerViewCategory.setNestedScrollingEnabled(false);

        categoryHomeAdapter.SetData(getCategoryData());
        recyclerViewCategory.setAdapter(categoryHomeAdapter);



        //Studio list home page
        recyclerViewStudio = view.findViewById(R.id.RecyclerViewStudio);
        studioHomeAdapter = new StudioHomeAdapter(getActivity());
        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL ,false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        recyclerViewStudio.setFocusable(false);
        recyclerViewStudio.setNestedScrollingEnabled(false);
        studioHomeAdapter.SetData(getStudioData());
        recyclerViewStudio.setAdapter(studioHomeAdapter);





//lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Studio list = myList.get(position);
//        Toast.makeText(getActivity(), list.getTitle(), Toast.LENGTH_SHORT).show();
//    }
//});
    }
    private List<Studio> getStudioData(){
        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
        String[] studioName = {"Studio Name 1","Studio Name 2","Studio Name 3","Studio Name 4","Studio Name 5","Studio Name 6" };
        String[] studioDescription = {"Studio Description 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sed semper elit. Donec at luctus felis, id faucibus nibh. Aliquam.","Studio Description 2","Studio Description 3","Studio Description 4","Studio Description 5","Studio Description 6" };
        String[] price = {"Price: 500$","Price: 500$","Price: 500$","Price: 500$","Price: 500$","Price: 500$" };
        String[] rating = {"⭐: 5.0","⭐: 5.0","⭐: 5.0","⭐: 5.0","⭐: 5.0","⭐: 5.0" };
        List<Studio> myList = new ArrayList<>();

        for (int i = 0; i < studioName.length; i++){
            myList.add(new Studio(image[i] , studioName[i], studioDescription[i], price[i], rating[i]));
            myList.add(new Studio(image[i] , studioName[i], studioDescription[i], price[i], rating[i]));
        }

        return myList;
    }
    private List<Category> getCategoryData(){
        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
        String[] categoryName = {"Category 1","Category 2","Category 3","Category 4","Category 5","Category 6" };
        List<Category> myList = new ArrayList<>();

        for (int i = 0; i < categoryName.length; i++){
            myList.add(new Category(image[i] , categoryName[i]));
        }

        return myList;
    }

    private List<String> getSortData(){
        String[] sortList = {"Category 1","Category 2","Category 3","Category 4","Category 5","Category 6" };
        List<String> myList = new ArrayList<>();

        for (int i = 0; i < sortList.length; i++){
            myList.add(new String(sortList[i]));
        }

        return myList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container , false);
        return view;
    }
}