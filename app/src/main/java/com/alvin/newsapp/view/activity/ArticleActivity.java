package com.alvin.newsapp.view.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.alvin.newsapp.ArticleListener;
import com.alvin.newsapp.R;
import com.alvin.newsapp.adapter.ArticleMenuAdapter;
import com.alvin.newsapp.api.HeadlineService;
import com.alvin.newsapp.model.Article;
import com.alvin.newsapp.model.ArticleResponse;
import com.alvin.newsapp.model.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticleActivity extends AppCompatActivity implements ArticleListener, SearchView.OnQueryTextListener, ArticleMenuAdapter.OnItemClickListener {

    private Source articleSource;
    ArticleMenuAdapter adapter;
    List<Article> articles, articleClick;
    public static final String ARTICLE_EXTRA = "ARTICLE_EXTRA";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        articleSource = getIntent().getParcelableExtra(MainActivity.SOURCE_EXTRA);
        initiateView();
    }

    private void initiateView() {
        dialog = new ProgressDialog(ArticleActivity.this);
        dialog.setMessage("Fetching News...");
        dialog.show();

        HeadlineService service = new HeadlineService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView rvArticleList = (RecyclerView) findViewById(R.id.rv_article_list);

        toolbar.setTitle(articleSource.getName());
        setSupportActionBar(toolbar);

        articles = new ArrayList<>();

        adapter = new ArticleMenuAdapter(this, articles, this);

        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("sources", articleSource.getId());

        service.createService(this, paramMap);

        rvArticleList.setAdapter(adapter);
        rvArticleList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSuccess(ArticleResponse articleResponse) {
        articles = articleResponse.getArticles();
        adapter.addDataArticle(articles);
        adapter.notifyDataSetChanged();
        dialog.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Article> articleList = filter(articles, newText);

                adapter.setFilter(articleList);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Article> articleList = filter(articles, newText);

        adapter.setFilter(articleList);
        adapter.notifyDataSetChanged();
        return true;
    }

    private List<Article> filter(List<Article> models, String query) {
        query = query.toLowerCase();
        final List<Article> filteredModelList = new ArrayList<>();
        for (Article model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onItemClick(View v, int position) {
        articleClick = adapter.getArticleList();
        Intent intent = new Intent(ArticleActivity.this, ArticleWebView.class);
        intent.putExtra(
                ARTICLE_EXTRA,
                articleClick.get(position)
        );
        startActivity(intent);
    }
}
