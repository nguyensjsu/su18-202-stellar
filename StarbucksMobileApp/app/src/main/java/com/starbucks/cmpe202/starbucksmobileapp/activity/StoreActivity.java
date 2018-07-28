package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.starbucks.cmpe202.starbucksmobileapp.R;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Store;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Stores;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class StoreActivity extends AppCompatActivity {

    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    String url;
    public Stores stores;
    String userid="";
    String sessionid="";
    TextView store_name;
    TextView store_address;
    ListView storeList;


    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();

        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
        }
        url="store/allstores";
        requestQueue = Volley.newRequestQueue(this);


    JsonArrayRequest objectRequest = new JsonArrayRequest(
            Request.Method.GET,
            baseUrl + url,
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("REST RESPONSE", response.toString());
                    try {
                        setResponse(response.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR RESPONSE", error.toString());
                }
            }
    )


    {

        @Override
        public String getBodyContentType() {
            return "application/json; charset=utf-8";
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("USER_ID", userid);
            params.put("SESSION_ID", sessionid);

            return params;
        }
    };

  //  stores.get(0);

        requestQueue.add(objectRequest);
        storeList = (ListView)findViewById(R.id.storesList);




    }

    private void setResponse(String s) {

        Gson gson = new Gson();

        Store[] stores =  gson.fromJson(s,Store[].class);


        for(Store store :stores ){
            Log.d("NAME:",store.getStoreName());
            Log.d("ADDRESS:",store.getStoreAddress());
        }

        CustomAdapter storelist = new CustomAdapter(stores);
        storeList.setAdapter(storelist);

//        name=stores[0].getStoreName();
//        System.out.println("name from store json "+name);
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


    private class CustomAdapter extends BaseAdapter {
        Store[] storeList;
        CustomAdapter(Store[] storeList){
            this.storeList = storeList;
        }

        @Override
        public int getCount() {
            return storeList.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.storesview,null);
            store_name = (TextView)view.findViewById(R.id.store_name);
            store_address = (TextView)view.findViewById(R.id.store_address);

            store_name.setText(storeList[i].getStoreName());
            store_address.setText(storeList[i].getStoreAddress()+"");


            return view;
        }
    }





}
