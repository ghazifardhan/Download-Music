package com.ghazifardhan.downloadmusic;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.ghazifardhan.downloadmusic.adapter.RecyclerViewAdapter;
import com.ghazifardhan.downloadmusic.api.ApiClient;
import com.ghazifardhan.downloadmusic.api.ApiService;
import com.ghazifardhan.downloadmusic.models.Item;
import com.ghazifardhan.downloadmusic.models.Youtube;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonSearch;
    private EditText searchText;
    private ProgressBar progressBar;

    private static final String apiKey = "AIzaSyAviKoCGH3Z4Cok1QFnmG5_4kIrpg65bUg";
    private static final String part = "snippet";

    ApiService service;

    private List<Item> items;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    public void initUI(){
        buttonSearch = (ImageButton) findViewById(R.id.buttonSearch);
        searchText = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.card_view_list);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2378082958694294~4724910360");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        initUI();

        service = ApiClient.getClient().create(ApiService.class);



        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar_menu_1) {
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData(){

        String query = searchText.getText().toString();

        Call<Youtube> call = service.getData(apiKey, part, query);
        call.enqueue(new Callback<Youtube>() {
            @Override
            public void onResponse(Call<Youtube> call, Response<Youtube> response) {
                progressBar.setVisibility(View.GONE);
                List<Item> itemList = response.body().getItems();

                items = itemList;

                setData();
            }

            @Override
            public void onFailure(Call<Youtube> call, Throwable t) {

            }
        });

    }

    private void setData(){

        final ArrayList<String> videoId = new ArrayList<String>();
        final ArrayList<String> videoTitle = new ArrayList<String>();
        ArrayList<String> thumbnails = new ArrayList<String>();
        for(int i=0;i<items.size();i++){
            videoId.add(items.get(i).getId().getVideoId());
            videoTitle.add(items.get(i).getSnippet().getTitle());
            thumbnails.add(items.get(i).getSnippet().getThumbnails().getHigh().getUrl());
        }

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), videoId, videoTitle, thumbnails);
        /*
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String url = "http://www.youtubeinmp3.com/fetch/?video=https://www.youtube.com/watch?v=" + videoId.get(position);
                        RxDownloader.getInstance(MainActivity.this)
                                .download(url, videoTitle.get(position) + ".mp3", "audio/mp3");

                    }
                })
        );
        */
        recyclerView.setAdapter(mAdapter);

    }
}
