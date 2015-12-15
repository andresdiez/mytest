package com.diez.andres.mytest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HectorAndres on 12/14/2015.
 */
public class BackTask1 extends AsyncTask<String,String, ArrayList<Row>> {


    private final AdapterList adapter;
    private final ProgressDialog progress;
    Row data;

    public BackTask1(AdapterList adapter, ProgressDialog progress) {
        this.adapter=adapter;
        this.progress=progress;
    }


    @Override
    protected ArrayList<Row> doInBackground(String... params) {
        try {


            /**
             * creates an String load request
             * */
            String url = "https://api.instagram.com/v1/tags/search?q={query}&access_token=1308988645.1677ed0.ac270617d2114686b28c0d5a7fe819cc";
            String urlimg = "https://api.instagram.com/v1/tags/{query}/media/recent?access_token=1308988645.1677ed0.ac270617d2114686b28c0d5a7fe819cc&count=1";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            String result = restTemplate.getForObject(url, String.class,params[0]);

            /**fetch jason array and read content
             * */
            JSONObject json = new JSONObject(result);
            JSONArray articles = json.getJSONArray("data");



            for (int i=0;i<articles.length();i++)
            {

                String name=articles.getJSONObject(i).getString("name");

                String result2 = restTemplate.getForObject(urlimg, String.class, name);
                JSONObject json2 = new JSONObject(result2);
                JSONArray articles2 = json2.getJSONArray("data");

                JSONObject imgsobj=articles2.getJSONObject(0).getJSONObject("images");
                JSONObject urlsobj=imgsobj.getJSONObject("low_resolution");


                URL newurl = new URL(urlsobj.getString("url"));
                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

                data=new Row(name,mIcon_val);
                publishProgress();



            }


            return null;

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        progress.dismiss();
        adapter.add(data);

    }
}
