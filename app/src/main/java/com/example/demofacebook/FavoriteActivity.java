package com.example.demofacebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.demofacebook.Adapter.Favorite.StudioAdapter;
import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemOrderListener;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudioAdapter studioAdapter;
    private List<Studio> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = (RecyclerView) findViewById(R.id.favoriteRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mList = getListStudioData();
        studioAdapter = new StudioAdapter(mList, this);
        recyclerView.setAdapter(studioAdapter);
    }

    private List<Studio> getListStudioData() {
        List<Studio> myList = new ArrayList<>();
        List<Service> listSevice = new ArrayList<>();
        listSevice.add(new Service(1, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(2, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(3, 1, 3, "String serviceName", 100000, 3));
        listSevice.add(new Service(4, 1, 3, "String serviceName", 100000, 3));
        String str = "2015-03-31";
        Date dateChange = Date.valueOf(str);
        myList.add(new Studio(1, 1, "String title", 3, listSevice));
        myList.add(new Studio(2, 1, "String title", 3, listSevice));
        myList.add(new Studio(3, 1, "String title", 3, listSevice));
        myList.add(new Studio(4, 1, "String title", 3, listSevice));
        myList.add(new Studio(5, 1, "String title", 3, listSevice));
        for (Studio s: myList) {
            Log.d("studio", String.valueOf(s.getServiceList().size()));
        }
        return myList;
    }
}