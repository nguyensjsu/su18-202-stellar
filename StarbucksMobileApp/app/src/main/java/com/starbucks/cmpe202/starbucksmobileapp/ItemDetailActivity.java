package com.starbucks.cmpe202.starbucksmobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.starbucks.cmpe202.starbucksmobileapp.activity.ViewCartActivity;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Cart;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;




public class ItemDetailActivity extends AppCompatActivity {

    String userid="";
    String sessionid="";
    String price="";
    String imgName="";
    String name="";
    TextView itemname;
    TextView itemprice;
    Spinner quantity;
    ImageView imageView;
    RequestQueue requestQueue;
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    String url;
    Map<String, Integer> idmap;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        itemname=(TextView)findViewById(R.id.itemname);
        itemprice=(TextView)findViewById(R.id.itemprice);
        quantity=(Spinner)findViewById(R.id.quantity);
        imageView=(ImageView)findViewById(R.id.img);
        url="cart/addtocart";
        cart=new Cart();

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("cuppuccino1", R.drawable.cuppuccino1);
        map.put("icedtea", R.drawable.icedtea);
        map.put("starbucksacai", R.drawable.starbucksacai);
        map.put("latte", R.drawable.latte);
        map.put("mochaa", R.drawable.mochaa);
        map.put("hotchoco", R.drawable.hotchoco);

        idmap = new HashMap<String, Integer>();
        idmap.put("cuppuccino1", 1);
        idmap.put("latte", 2);
        idmap.put("mochaa", 3);
        idmap.put("icedtea", 4);
        idmap.put("starbucksacai", 5);
        idmap.put("hotchoco", 6);

        Intent intent = getIntent();

        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
            price=intent.getStringExtra("ITEM_PRICE");
            name=intent.getStringExtra("ITEM_NAME");
            imgName=intent.getStringExtra("ITEM_IMAGE");
        }

        itemname.setText(name);
        itemprice.setText(price);
        System.out.println(map.get(imgName));
        imageView.setImageResource(map.get(imgName));

    }

    public void addtocart(View view) {
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                baseUrl + url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                    Log.d("user id",userid);
                    jsonObject.put("userId", userid);
                    jsonObject.put("itemId", idmap.get(imgName));
                    jsonObject.put("quantity", quantity.getSelectedItem().toString());
                    jsonObject.put("total", Double.parseDouble(quantity.getSelectedItem().toString())*Double.parseDouble(price));
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



            // @Override
//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("userID", userid.getText().toString());
//                params.put("userPin", userpin.getText().toString());
//                return params;
//            }
        };


        requestQueue.add(objectRequest);

    }


    private void setResponse(String s) {

        Gson gson = new Gson();
//
        cart  =  gson.fromJson(s,Cart.class);
//
//        if(user.getMessage().equals("Authenticated Successfully")){
            Intent i = new Intent(this,ViewCartActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
//            Log.d("LOGIN ACTIVITY","user_id: "+user.getUserId());
//            Log.d("LOGIN ACTIVITY","session_id:" +user.getSessionId());
            startActivity(i);
//
//        }
//
//        else {
//            message.setText(user.getMessage());
//        }


    }


}
