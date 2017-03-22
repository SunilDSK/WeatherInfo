package com.sunilkumar.android.weatherinfo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by Sunilkumar on 22-03-2017.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {

    Context mContext;
    public DownloadTask(Context context){
        mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection mHttpURLConnection = null;
        try {
            url = new URL(urls[0]);

            mHttpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = mHttpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;

                result+=current;
                data = inputStreamReader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject mJsonObject = new JSONObject(result);
            JSONObject temperature = mJsonObject.getJSONObject("main");
            //Get the temperature
            double temp = Double.parseDouble(temperature.getString("temp"));
            JSONArray weather = mJsonObject.getJSONArray("weather");
            //Get the description
            JSONObject description = new JSONObject(weather.getString(0));
            String desc = description.getString("description");
            //Get the city name
            String city = mJsonObject.getString("name");
            //Convert kelvin to celsius
            temp-=273.15;
            //Display only 3 numbers after decimal point
            temp = Double.parseDouble(new DecimalFormat("##.###").format(temp));
            Toast.makeText(mContext,"Its "+ desc+" with "+ temp +" in "+city+".",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
