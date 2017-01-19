package com.example.priyankakulkarni.hypergaragesale;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.priyankakulkarni.garagesale.R;


/**
 * Created by priyankakulkarni on 10/17/16.
 */

public class PostActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private Toolbar myToolbar;
    private String titleText1;
    private String priceText1;
    private String descText1;
    private double price;
    private String emailAddress1;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        db = new DatabaseHandler(this);

    }

    /*public void newPostAdded(View v) {
        showSnackBar();
    }*/

    private void showSnackBar(View v) {
        if(v == null) {
            Snackbar.make(findViewById(R.id.coordinatorLayout), R.string.new_post_snackbar,
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(v, R.string.new_post_snackbar,
                    Snackbar.LENGTH_SHORT).show();
        }
    }
    public void newPostAdded(View v) {
        addNewItem();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_browse_post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_post) {
            showSnackBar(null);
            addNewItem();

        }
        return super.onOptionsItemSelected(item);
    }

    public void addNewItem(){

        EditText titleText = (EditText) findViewById(R.id.textView_title);
        EditText descText = (EditText) findViewById(R.id.textView_desc);
        EditText priceText = (EditText) findViewById(R.id.textView_price);
        EditText emailAddress = (EditText) findViewById(R.id.textView_email);

        titleText1 = titleText.getText().toString();
        priceText1 = priceText.getText().toString();
        descText1 = descText.getText().toString();
        emailAddress1 = emailAddress.getText().toString();

        try{
            price = new Double(priceText1);
        } catch (NumberFormatException e){
            price = 0;
        }

        GarageItem garageItem = new GarageItem(titleText1,price,descText1,emailAddress1);
        db.addGarageItem(garageItem);
        startActivity(new Intent(this, BrowsePostsActivity.class));
    }
}