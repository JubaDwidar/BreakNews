package com.juba.breaknews;


import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.juba.breaknews.Api.ApiClient;
import com.juba.breaknews.Api.ApiInterface;
import com.juba.breaknews.Models.Article;
import com.juba.breaknews.Models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String key_api = "e6feb82878c541f89cbb26a9cab00c99";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    Adapter adapter;
    List<Article> articles = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_views);
        swipeRefreshLayout = findViewById(R.id.swip_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(true);

loadingSwipRefreash("");

    }

    public void loadJson(final String keyword) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String country = Utils.getCountry();
swipeRefreshLayout.setRefreshing(true);
        Call<News> call;
        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, country, "publishedAt", key_api);

        }
        call = apiInterface.getNews(country, key_api);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (response.isSuccessful() && response.body().getArticle() != null)
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                {
                    articles = response.body().getArticle();
                    adapter = new Adapter(articles);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest News");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() >= 2) {
 loadingSwipRefreash(s);                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                loadingSwipRefreash(s);

                return false;
            }
        });
        menuItem.getIcon().setVisible(false, false);


        return true;
    }

    @Override
    public void onRefresh() {
        loadJson("");
    }
    private void loadingSwipRefreash(final String word)
    {

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        loadJson(word);
                    }
                }
        );
    }


}
