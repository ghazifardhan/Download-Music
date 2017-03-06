package com.ghazifardhan.downloadmusic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esafirm.rxdownloader.RxDownloader;
import com.ghazifardhan.downloadmusic.adapter.RecyclerItemClickListener;
import com.ghazifardhan.downloadmusic.adapter.RecyclerViewAdapter;
import com.ghazifardhan.downloadmusic.api.ApiClient;
import com.ghazifardhan.downloadmusic.api.ApiService;
import com.ghazifardhan.downloadmusic.models.Item;
import com.ghazifardhan.downloadmusic.models.Youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button buttonSearch;
    private EditText searchText;

    private static final String apiKey = "AIzaSyAviKoCGH3Z4Cok1QFnmG5_4kIrpg65bUg";
    private static final String part = "snippet";

    ApiService service;

    private List<Item> items;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    public void initUI(){
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        searchText = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.card_view_list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        service = ApiClient.getClient().create(ApiService.class);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData(){

        String query = searchText.getText().toString();

        Call<Youtube> call = service.getData(apiKey, part, query);
        call.enqueue(new Callback<Youtube>() {
            @Override
            public void onResponse(Call<Youtube> call, Response<Youtube> response) {
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
        recyclerView.setAdapter(mAdapter);

    }
}
