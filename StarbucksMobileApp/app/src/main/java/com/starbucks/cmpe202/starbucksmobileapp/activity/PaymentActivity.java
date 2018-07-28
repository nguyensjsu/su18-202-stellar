package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.starbucks.cmpe202.starbucksmobileapp.R;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Card;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Message;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.ViewCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {


    String userid="";
    String sessionid="";
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    RequestQueue requestQueue;
    String url;
    TextView totaltxt;
    TextView cardnumber;
    String orderId="";
    ListView cardList;
    TextView cardNumber;
    TextView cardBalance;
    Card[] cards;
    TextView cardnumberPay;
    double sum=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        url="card/viewallcards";
        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
            sum=intent.getDoubleExtra("TOTAL",0);
        }

        cardnumberPay=(TextView)findViewById(R.id.cardnumberPay);
        totaltxt=(TextView)findViewById(R.id.totaltxt);


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

        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v,
                                    int position, long id) {
                cardnumberPay.setText(cards[position].getCardNumber());

            }
        });





    }

    private void setResponse(String s) {

        Gson gson = new Gson();
        this.cards =  gson.fromJson(s,Card[].class);
        CustomAdapter cardlist = new CustomAdapter(cards);
        for(Card card: cards){
            if(card.getIsDefault()){
                cardnumberPay.setText(card.getCardNumber());
            }
        }
        cardList.setAdapter(cardlist);
    }

    public void payment(View view){
        url="payment/makepayment";

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
                            setpaymentResponse(response.toString());
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
                    jsonObject.put("cardNumber", cardnumberPay.getText().toString());
                    jsonObject.put("total", sum);
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

    }


    private void setpaymentResponse(String s) {

        Gson gson = new Gson();

        Message msg  =  gson.fromJson(s,Message.class);

        if(msg.getMessage().equals("Payment done Successfully")){
            Intent i = new Intent(this,MenuActivity.class);
            i.putExtra("USER_ID",userid);
            i.putExtra("SESSION_ID",sessionid);
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();

        }

        else {
            Toast.makeText(this, msg.getMessage(), Toast.LENGTH_LONG).show();
        }



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
        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.paymentcardview,null);
            cardNumber = (TextView)view.findViewById(R.id.cardNumberVal);
            cardBalance = (TextView)view.findViewById(R.id.cardBalanceVal);

            cardNumber.setText(cardList[i].getCardNumber());
            cardBalance.setText("$"+cardList[i].getBalance());


            return view;
        }
    }
}
