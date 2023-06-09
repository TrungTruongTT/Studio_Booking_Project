package com.example.demofacebook.Fragment.MainPageFragment;

import android.content.Intent;
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

import com.example.demofacebook.Adapter.HomePage.CategoryHomeAdapter;
import com.example.demofacebook.Adapter.HomePage.SortHomeAdapter;
import com.example.demofacebook.Adapter.HomePage.StudioHomeAdapter;
import com.example.demofacebook.HomePage.StudioPageActivity;
import com.example.demofacebook.Model.Category;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.MyInterface.IClickItemCategoryListener;
import com.example.demofacebook.MyInterface.IClickItemSortListener;
import com.example.demofacebook.MyInterface.IClickItemStudioListener;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.Collections;
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
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
        sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
            @Override
            public void onClickItemSort(String sortBy) {
                Toast.makeText(getActivity(), sortBy, Toast.LENGTH_SHORT).show();
                studioHomeAdapter.getFilter().filter("@!" + sortBy);

            }
        });
        recyclerViewSort.setAdapter(sortHomeAdapter);
        //Category list home page
        recyclerViewCategory = view.findViewById(R.id.RecyclerViewCategory);
        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(linearLayoutManagerCategory);
        categoryHomeAdapter = new CategoryHomeAdapter(getCategoryData(), new IClickItemCategoryListener() {
            @Override
            public void onClickItemCategory(Category category) {
                Toast.makeText(getActivity(), category.getCategoryName(), Toast.LENGTH_SHORT).show();
                studioHomeAdapter.getFilter().filter("@!" + category.getCategoryName());
            }
        });
        recyclerViewCategory.setAdapter(categoryHomeAdapter);

        //Studio list home page
        recyclerViewStudio = view.findViewById(R.id.RecyclerViewStudio);
        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        studioHomeAdapter = new StudioHomeAdapter(getStudioData(), new IClickItemStudioListener() {
            @Override
            public void onClickItemStudio(Studio studio) {
                onClickItemGoDetail(studio);
            }
        });
        recyclerViewStudio.setAdapter(studioHomeAdapter);

    }

    private List<Studio> getStudioData() {
        List<Studio> myList = new ArrayList<>();

        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
        String[] studioName = {"Studio Name 1", "Studio Name 2", "Studio Name 3", "Studio Name 4", "Studio Name 5", "Studio Name 6"};
        String[] studioDescription = {"Studio Description 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sed semper elit. Donec at luctus felis, id faucibus nibh. Aliquam.", "Studio Description 2", "Studio Description 3", "Studio Description 4", "Studio Description 5", "Studio Description 6"};
        Integer[] price = {500, 400, 300, 500, 100, 500};
        Integer[] rating = {2, 1, 2, 4, 5, 6};
        for (int i = 0; i < studioName.length; i++) {
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
        }


        return myList;
    }

    private List<Category> getCategoryData() {
        int[] image = {R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download, R.drawable.download};
        String[] categoryName = {"Category 1", "Category 2", "Category 3", "Category 4", "Category 5", "Category 6"};
        List<Category> myList = new ArrayList<>();

        for (int i = 0; i < categoryName.length; i++) {
            myList.add(new Category(image[i], categoryName[i]));
        }

        return myList;
    }

    private List<String> getSortData() {
        String[] sortList = {"All", "Rating", "Price", "Newest", "Famous", "Sort 6"};
        List<String> myList = new ArrayList<>();

        Collections.addAll(myList, sortList);

        return myList;
    }

    private void onClickItemGoDetail(Studio studio) {
        Intent intent = new Intent(getActivity(), StudioPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return view;
    }
}