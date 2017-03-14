package com.ghazifardhan.downloadmusic.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.rxdownloader.RxDownloader;
import com.ghazifardhan.downloadmusic.MainActivity;
import com.ghazifardhan.downloadmusic.R;
import com.ghazifardhan.downloadmusic.api.ApiClient;
import com.ghazifardhan.downloadmusic.api.ApiClientSaveDeo;
import com.ghazifardhan.downloadmusic.api.ApiService;
import com.ghazifardhan.downloadmusic.api.ApiServiceSaveDeo;
import com.ghazifardhan.downloadmusic.models.savedeo.Format;
import com.ghazifardhan.downloadmusic.models.savedeo.GrabVideo;
import com.ghazifardhan.downloadmusic.models.savedeo.PostCall;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ghazifardhan on 05/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<String> videoId = new ArrayList<String>();
    ArrayList<String> videoTitle = new ArrayList<String>();
    ArrayList<String> thumbnails = new ArrayList<String>();

    String downloadUrl;
    List<Format> formatList;

    String apiKey = "eHiGg5fDXSmsh3VnGzw1mjcgPfUIp1GYIAejsnpV668FYfXRA9";
    String contentType = "application/x-www-form-urlencoded";

    public RecyclerViewAdapter(Context context, ArrayList<String> videoId, ArrayList<String> videoTitle, ArrayList<String> thumbnails) {

        this.videoId = videoId;
        this.context = context;
        this.videoTitle = videoTitle;
        this.thumbnails = thumbnails;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mTitle.setText(videoTitle.get(position));

        final ApiServiceSaveDeo service = ApiClientSaveDeo.getClient().create(ApiServiceSaveDeo.class);

        holder.mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int adapterPosition = holder.getAdapterPosition();

                String url = "https://www.youtube.com/watch?v=" + videoId.get(adapterPosition);
                //String url = "https://www.youtube.com/watch?v=n1cnVbf_DxQ";

                Call<GrabVideo> call = service.getDataVideo(apiKey, url);
                call.enqueue(new Callback<GrabVideo>() {
                    @Override
                    public void onResponse(Call<GrabVideo> call, Response<GrabVideo> response) {
                        formatList = response.body().getFormats();
                        for(int i=0;i<formatList.size();i++){
                            if(formatList.get(i).getFormatId().equals("140")){
                                downloadUrl = formatList.get(i).getUrl();
                                System.out.println(downloadUrl);
                                System.out.println(formatList.get(i).getFormatId());
                                System.out.println(formatList.get(i).getFormat());
                                System.out.println(formatList.get(i).getExt());
                            }
                        }

                        RxDownloader.getInstance(context)
                                .download(downloadUrl, videoTitle.get(adapterPosition) + ".mp3", "audio/*");
                    }

                    @Override
                    public void onFailure(Call<GrabVideo> call, Throwable t) {

                    }
                });


                /*
                String url = "http://www.youtubeinmp3.com/fetch/?video=https://www.youtube.com/watch?v=" + videoId.get(adapterPosition);
                RxDownloader.getInstance(context)
                        .download(url, videoTitle.get(adapterPosition) + ".mp3", "audio/mpeg");
                */
            }
        });

        Picasso.with(context)
                .load(thumbnails.get(position))
                .into(holder.mThumbnails);


    }

    @Override
    public int getItemCount() {
        return videoId.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle;
        ImageView mThumbnails;
        Button mBtnDownload;

        public ViewHolder(View itemView) {
            super(itemView);

            mThumbnails = (ImageView) itemView.findViewById(R.id.thumbnail_list);
            mTitle = (TextView) itemView.findViewById(R.id.video_title);
            mBtnDownload = (Button) itemView.findViewById(R.id.buttonDownload);
        }
    }
}
