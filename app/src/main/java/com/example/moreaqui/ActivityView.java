package com.example.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityView extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        list = (ListView) findViewById(R.id.listview);

        ArrayAdapter adapter = new ArrayAdapter(ActivityView.this, android.R.layout.simple_list_item_1, android.R.id.text1, ControllDatabase.getListtData(MainActivity.getDB().Open()));
        MainActivity.getDB().close();

        list.setAdapter(adapter);

    }
}