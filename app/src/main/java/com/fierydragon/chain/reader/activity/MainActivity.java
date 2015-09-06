package com.fierydragon.chain.reader.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.adapter.RecyclerViewMainActivityAdapter;
import com.fierydragon.chain.reader.data.articleData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private Toolbar toolbarMainActivity;
    private RecyclerView recyclerViewMainActivity;

    private List<articleData> articleDataList;
    private RecyclerViewMainActivityAdapter mainActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        toolbarMainActivity = (Toolbar) findViewById(R.id.toolbar);
        toolbarMainActivity.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbarMainActivity);
    }

    private void initRecyclerView() {
        recyclerViewMainActivity = (RecyclerView) findViewById(R.id.recyclerview_activity_main);
        recyclerViewMainActivity.setLayoutManager(new LinearLayoutManager(mContext));

        // TODO 文章分段
        articleDataList = new ArrayList<>();
        articleDataList.add(new articleData("Unit 1 Lesson 1", "Finding fossil man", getString(R.string.test_artical_content)));
        articleDataList.add(new articleData("Unit 1 Lesson 2", "Finding fossil man", getString(R.string.test_artical_content)));
        articleDataList.add(new articleData("Unit 1 Lesson 3", "Finding fossil man", getString(R.string.test_artical_content)));
        articleDataList.add(new articleData("Unit 1 Lesson 4", "Finding fossil man", getString(R.string.test_artical_content)));

        mainActivityAdapter = new RecyclerViewMainActivityAdapter(mContext, articleDataList);
        recyclerViewMainActivity.setAdapter(mainActivityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
