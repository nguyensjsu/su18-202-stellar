package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.ViewCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {


    String userid="";
    String sessionid="";
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    RequestQueue requestQueue;
    String url;
    ListView cartList;
    TextView price;
    TextView itemName;
    ImageView imageView;
    TextView quantity;
    TextView total;
    TextView total_id;
    Map<String, Integer> idmap;
    double sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();

        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
            sum=intent.getDoubleExtra("TOTAL",0);

        }
        url="cart/viewcart";

        idmap = new HashMap<String, Integer>();
        idmap.put("Cappucino", R.drawable.cuppuccino1);
        idmap.put("Latte", R.drawable.latte);
        idmap.put("Mocha", R.drawable.mochaa);
        idmap.put("Iced Tea", R.drawable.icedtea);
        idmap.put("Strawberry Acai", R.drawable.starbucksacai);
        idmap.put("HOT CHOCOLATE", R.drawable.hotchoco);
        total_id=(TextView)findViewById(R.id.total_id);
        DecimalFormat fmt = new DecimalFormat("0.00");
        String amtfmt= "$"+fmt.format(sum) ;
        total_id.setText(amtfmt);

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
        cartList = (ListView)findViewById(R.id.cartList);




    }

    private void setResponse(String s) {

        Gson gson = new Gson();
        ViewCart[] cart =  gson.fromJson(s,ViewCart[].class);
        CustomAdapter cartlist = new CustomAdapter(cart);
        cartList.setAdapter(cartlist);
    }


    public void paynow(View view){
        Intent i = new Intent(this,PaymentActivity.class);
        i.putExtra("USER_ID",userid);
        i.putExtra("SESSION_ID",sessionid);
        i.putExtra("TOTAL",sum);
        startActivity(i);
    }
    private class CustomAdapter extends BaseAdapter {
        ViewCart[] cartList;
        CustomAdapter(ViewCart[] cartList){
            this.cartList = cartList;
        }

        @Override
        public int getCount() {
            return cartList.length;
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

            view = getLayoutInflater().inflate(R.layout.cartview,null);
            itemName = (TextView)view.findViewById(R.id.itemName);
            price = (TextView)view.findViewById(R.id.price);
            quantity=(TextView)view.findViewById(R.id.quantity);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            total =(TextView)view.findViewById(R.id.total);

            itemName.setText(cartList[i].getItemName());

            DecimalFormat fmt = new DecimalFormat("0.00");
            String amtfmt= "$"+fmt.format(cartList[i].getItemPrice()) ;

            price.setText("Price: "+amtfmt);
            quantity.setText("Qty: "+cartList[i].getQuantity());
            amtfmt="$"+fmt.format(cartList[i].getTotal()) ;
            total.setText("Total: $"+amtfmt);
            imageView.setImageResource(idmap.get(cartList[i].getItemName()));


            return view;
        }
    }
}
