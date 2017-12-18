package com.alvin.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alvin.newsapp.adapter.SourceMenuAdapter;
import com.alvin.newsapp.api.ApiController;
import com.alvin.newsapp.api.NewsSourceService;
import com.alvin.newsapp.model.Source;
import com.alvin.newsapp.model.SourceResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SourceListener{

    public static final String SOURCE_EXTRA = "SOURCE_EXTRA";
    List<Source> sources;
    SourceMenuAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_menu);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Fetching News Source...");
        dialog.show();
        NewsSourceService service = new NewsSourceService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        RecyclerView rvSourceList = (RecyclerView) findViewById(R.id.rv_source_menu);

        toolbar.setTitle("NewsApp");
        setSupportActionBar(toolbar);

        service.createService(this);

        sources = new ArrayList<>();

        adapter = new SourceMenuAdapter(this, sources);


        rvSourceList.setAdapter(adapter);
        // Set layout manager to position the items
        rvSourceList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccess(SourceResponse sourceResponse) {
        sources = sourceResponse.getSources();
        adapter.SetOnItemClickListener(new SourceMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra(
                        SOURCE_EXTRA,
                        sources.get(position)
                );
                startActivity(intent);
            }
        });
        adapter.addDataSource(sources);
        adapter.notifyDataSetChanged();
        dialog.hide();
    }


}
