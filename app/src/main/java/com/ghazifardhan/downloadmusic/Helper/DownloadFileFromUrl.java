package com.ghazifardhan.downloadmusic.Helper;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ghazifardhan on 07/03/17.
 */

public class DownloadFileFromUrl extends AsyncTask<String, String, String> {

    protected  void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            int lenghOfFile = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            OutputStream output = new FileOutputStream("Download/Downloaded Music/");
            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1){
                total += count;
                publishProgress(""+(int)((total*100)/lenghOfFile));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e){
            Log.e("Error : ", e.getMessage());
        }

        return null;
    }

    protected void onProgressUpdate(String... progress) {

    }

    protected void onPostExecute(String file_url){

        String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
    }
}
