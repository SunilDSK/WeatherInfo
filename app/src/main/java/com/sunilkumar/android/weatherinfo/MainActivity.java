package com.sunilkumar.android.weatherinfo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView info;
    private String url = "http://api.openweathermap.org/data/2.5/weather?q=Hubli,in&appid=39e430ff0fa367b17f8225b66fae0a55";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = getApplicationContext();

        button = (Button) findViewById(R.id.button);
        info = (TextView) findViewById(R.id.info);

        DownloadTask mDownloadTask = new DownloadTask(mContext);
        mDownloadTask.execute(url);

    }
}
