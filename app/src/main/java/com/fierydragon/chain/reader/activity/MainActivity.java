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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private Toolbar toolbarMainActivity;
    private RecyclerView recyclerViewMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
    }

    private void initView() {
        toolbarMainActivity = (Toolbar) findViewById(R.id.toolbar);
        toolbarMainActivity.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbarMainActivity);

        recyclerViewMainActivity = (RecyclerView) findViewById(R.id.recyclerview_activity_main);
        recyclerViewMainActivity.setLayoutManager(new LinearLayoutManager(mContext));

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
