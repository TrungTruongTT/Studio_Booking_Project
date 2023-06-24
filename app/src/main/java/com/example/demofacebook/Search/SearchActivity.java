package com.example.demofacebook.Search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofacebook.Adapter.StudioDetail.Interface.IClickItemServiceListener;
import com.example.demofacebook.Adapter.StudioDetail.ServiceAdapter;
import com.example.demofacebook.Fragment.Service.ServicePage;
import com.example.demofacebook.Model.Service;
import com.example.demofacebook.Model.Studio;
import com.example.demofacebook.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    SearchView searchView;

    private RecyclerView recyclerViewStudio;
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //ToolBar
        toolbar = findViewById(R.id.myToolBarSearch);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search View");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_navbar));

        //studioList
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudioSearch);
        serviceAdapter = new ServiceAdapter(getStudioData(), new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Service Service) {
                onClickItemGoDetail(Service);
            }
        });

        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        recyclerViewStudio.setAdapter(serviceAdapter);
        recyclerViewStudio.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                serviceAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewStudio.setVisibility(View.VISIBLE);
                serviceAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private List<Service> getStudioData() {
        List<Service> myList = new ArrayList<>();
        myList.add(new Service(1, R.drawable.download, 4, "Service 1",
                "<p>Please contact me first before starting order.</p><p><br></p><p>Hello, I'm Lana, I will be happy to shoot <strong>professional photo</strong> with your product and my adorable models)</p><p>I specialize in creating professional, high quality and selling product images.&nbsp;I have the&nbsp;models for your product (baby, mom and dad, toddler, and dog).</p><p>I live in Florida, so can use a beautiful nature to create an amazing lifestyle photos. Also I will be happy to create studio high quality photos for your brand.</p><p><br></p><p><strong>What you'll get:</strong></p><ul><li>High quality JPEG image</li><li>Images taken with professional high end SLRs and equipment (inc. Canon 6D mark ii)&nbsp;</li><li>Free image enhancement and editing to polish off the final product.</li></ul><p><br></p><p><strong>If you're an e-commerce seller on platforms such as eBay, Amazon, Shopify or Etsy, then&nbsp;this is the gig for you!</strong></p><p><br></p><p><strong>Why me?&nbsp;</strong>I'm hardworking and always aim for a quality results. You'll get the cheapest deal from me right now whilst I'm building my Fiverr reviews! If you are interested, write to me and we will discuss the details of the order.&nbsp;</p><p><strong>I</strong>&nbsp;<strong>would be</strong>&nbsp;<strong>really</strong>&nbsp;<strong>glad to work with you!&nbsp;</strong></p>", 350, 500));
        myList.add(new Service(2, R.drawable.download, 3, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(3, R.drawable.download, 2, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(4, R.drawable.download, 5, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(5, R.drawable.download, 1, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(6, R.drawable.download, 4, "Service 1",
                "<p>Please contact me first before starting order.</p><p><br></p><p>Hello, I'm Lana, I will be happy to shoot <strong>professional photo</strong> with your product and my adorable models)</p><p>I specialize in creating professional, high quality and selling product images.&nbsp;I have the&nbsp;models for your product (baby, mom and dad, toddler, and dog).</p><p>I live in Florida, so can use a beautiful nature to create an amazing lifestyle photos. Also I will be happy to create studio high quality photos for your brand.</p><p><br></p><p><strong>What you'll get:</strong></p><ul><li>High quality JPEG image</li><li>Images taken with professional high end SLRs and equipment (inc. Canon 6D mark ii)&nbsp;</li><li>Free image enhancement and editing to polish off the final product.</li></ul><p><br></p><p><strong>If you're an e-commerce seller on platforms such as eBay, Amazon, Shopify or Etsy, then&nbsp;this is the gig for you!</strong></p><p><br></p><p><strong>Why me?&nbsp;</strong>I'm hardworking and always aim for a quality results. You'll get the cheapest deal from me right now whilst I'm building my Fiverr reviews! If you are interested, write to me and we will discuss the details of the order.&nbsp;</p><p><strong>I</strong>&nbsp;<strong>would be</strong>&nbsp;<strong>really</strong>&nbsp;<strong>glad to work with you!&nbsp;</strong></p>", 350, 500));
        myList.add(new Service(7, R.drawable.download, 3, "Service 2",
                "Service Description 1\nService Description 2\nService Description 3", 4, 500));
        myList.add(new Service(8, R.drawable.download, 2, "Service 3",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(9, R.drawable.download, 5, "Service 4",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        myList.add(new Service(10, R.drawable.download, 1, "Service 5",
                "Service Description 1\nService Description 2\nService Description 3", 350, 500));
        return myList;
    }

    private void onClickItemGoDetail(Service service) {
        Intent intent = new Intent(this, ServicePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        Studio studio = new Studio(1, R.drawable.download, "Studio 1 test", 500, 5, "Description\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\nDescription\n");
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}