package com.ghazifardhan.downloadmusic;

import android.app.Application;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by ghazifardhan on 06/03/17.
 */

public class DownloadMusic extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Just for cache Application's Context, and ':filedownloader' progress will NOT be launched
         * by below code, so please do not worry about performance.
         */
        FileDownloader.init(getApplicationContext());
    }
}
