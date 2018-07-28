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
import android.widget.RadioButton;
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
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Card;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ViewCardActivity extends AppCompatActivity {

    String userid="";
    String sessionid="";
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    String url;
    RequestQueue requestQueue;
    ListView cardList;
    TextView cardNumber;
    TextView cardBalance;
    RadioButton defaultcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        Intent intent = getIntent();
        url="card/viewallcards";
        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
        }



        requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.POST,
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
            @Override
            public byte[] getBody() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userId", userid);
                    final String mRequestBody = jsonObject.toString();
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (JSONException uee) {
                    uee.printStackTrace();
                    Log.e("ERROR RESPONSE", uee.getMessage());
                    return null;

                } catch (UnsupportedEncodingException uee) {
                    uee.printStackTrace();
                    Log.e("ERROR RESPONSE", uee.getMessage());
                    return null;
                }
            }
        };

        requestQueue.add(objectRequest);
        cardList = (ListView)findViewById(R.id.cardList);




    }

    private void setResponse(String s) {

        Gson gson = new Gson();
        Card[] cards =  gson.fromJson(s,Card[].class);
        CustomAdapter cardlist = new CustomAdapter(cards);
        cardList.setAdapter(cardlist);
    }


    public void addcard (View view){
        Intent i = new Intent(this,AddCardActivity.class);
        i.putExtra("USER_ID",userid);
        i.putExtra("SESSION_ID",sessionid);
        startActivity(i);
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
        Card[] cardList;
        CustomAdapter(Card[] cardList){
            this.cardList = cardList;
        }

        @Override
        public int getCount() {
            return cardList.length;
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

            view = getLayoutInflater().inflate(R.layout.cardview,null);
            cardNumber = (TextView)view.findViewById(R.id.cardNumberVal);
            cardBalance = (TextView)view.findViewById(R.id.cardBalanceVal);
            defaultcard = (RadioButton) view.findViewById(R.id.default_card);

            cardNumber.setText(cardList[i].getCardNumber());
            cardBalance.setText("$"+cardList[i].getBalance());
            defaultcard.setChecked(cardList[i].getIsDefault());


            return view;
        }
    }

}
