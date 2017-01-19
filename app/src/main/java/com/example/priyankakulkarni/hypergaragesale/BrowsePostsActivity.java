package com.example.priyankakulkarni.hypergaragesale;

import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.priyankakulkarni.garagesale.R;

import java.util.ArrayList;

/**
 * Created by priyankakulkarni on 10/17/16.
 */



public class BrowsePostsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<GarageItem> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //creating objects for recycle view
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DatabaseHandler db = new DatabaseHandler(this);

        //inserting items
        Log.d("Insert: ", "Inserting ..");
       // db.addGarageItem(new GarageItem("Chair", 50.0, "New chair", "abc@gmail.com"));
        //db.addGarageItem(new GarageItem("Table", 80.0, "good condition", "123@gmail.com"));

        myDataset = db.getAllGarageItems();
        for (GarageItem cn : myDataset) {
            String log = "Name: "+cn.getItemName() +" ,price: " + cn.getPrice();
            // Writing Contacts to log
            Log.d("Name: ", log);}

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Item Posted",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    private void showSnackBar() {
        Snackbar.make(findViewById(R.id.coordinatorLayout), "Welcome",
                Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_settings) {
            showSnackBar();
        }
        return super.onOptionsItemSelected(item);
    }
}
