package com.ghazifardhan.downloadmusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.esafirm.rxdownloader.RxDownloader;
import com.ghazifardhan.downloadmusic.MainActivity;
import com.ghazifardhan.downloadmusic.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText(videoTitle.get(position));


        holder.mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                String url = "http://www.youtubeinmp3.com/fetch/?video=https://www.youtube.com/watch?v=" + videoId.get(adapterPosition);
                RxDownloader.getInstance(context)
                        .download(url, videoTitle.get(adapterPosition) + ".mp3", "audio/mpeg");
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
