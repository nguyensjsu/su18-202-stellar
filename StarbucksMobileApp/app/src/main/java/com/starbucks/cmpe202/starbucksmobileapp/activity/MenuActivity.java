package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.starbucks.cmpe202.starbucksmobileapp.ItemDetailActivity;
import com.starbucks.cmpe202.starbucksmobileapp.R;

public class MenuActivity extends AppCompatActivity {


    String userid="";
    String sessionid="";
    ImageView item1;
    ImageView item2;
    ImageView item3;
    ImageView item4;
    ImageView item5;
    ImageView item6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        item1=(ImageView)findViewById(R.id.item1);
        item2=(ImageView)findViewById(R.id.item2);
        item3=(ImageView)findViewById(R.id.item3);
        item4=(ImageView)findViewById(R.id.item4);
        item5=(ImageView)findViewById(R.id.item5);
        item6=(ImageView)findViewById(R.id.item6);

        Intent intent = getIntent();

        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
        }

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","Iced Tea");
                i.putExtra("ITEM_PRICE","3.50");
                i.putExtra("ITEM_IMAGE","icedtea");
                startActivity(i);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","Strawberry Acai");
                i.putExtra("ITEM_PRICE","4.0");
                i.putExtra("ITEM_IMAGE","starbucksacai");
                startActivity(i);
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","Latte");
                i.putExtra("ITEM_PRICE","3.50");
                i.putExtra("ITEM_IMAGE","latte");
                startActivity(i);
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","Mocha");
                i.putExtra("ITEM_PRICE","3.20");
                i.putExtra("ITEM_IMAGE","mochaa");
                startActivity(i);
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","Cappucino");
                i.putExtra("ITEM_PRICE","4.0");
                i.putExtra("ITEM_IMAGE","cuppuccino1");
                startActivity(i);
            }
        });

        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ItemDetailActivity.class);
                i.putExtra("USER_ID",userid);
                i.putExtra("SESSION_ID",sessionid);
                i.putExtra("ITEM_NAME","HOT CHOCOLATE");
                i.putExtra("ITEM_PRICE","4.0");
                i.putExtra("ITEM_IMAGE","hotchoco");
                startActivity(i);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        for (int i = 0; i < menu.size(); i++) {
            if(i==0){
                menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            }
            else{
                menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.mycart_menu){
            Intent i = new Intent(this,ViewCartActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            startActivity(i);
            return true;
        }

        else if (id == R.id.shop_menu){
            Intent i = new Intent(this,MenuActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            startActivity(i);
            return true;

        }

        else if (id == R.id.orders_menu){
            Intent i = new Intent(this,ViewOrdersActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            startActivity(i);
            return true;

        }

        else if (id == R.id.payments_menu){
            Intent i = new Intent(this,ViewCardActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            startActivity(i);
            return true;

        }

        else if (id == R.id.store_menu){
            Intent i = new Intent(this,StoreActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
