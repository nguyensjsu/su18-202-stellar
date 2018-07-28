package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.starbucks.cmpe202.starbucksmobileapp.R;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Message;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddCardActivity extends AppCompatActivity {


    String userid="";
    String sessionid="";
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    RequestQueue requestQueue;
    String url;
    EditText cardNumber;
    EditText cardCode;
    CheckBox defaultCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        cardNumber=(EditText)findViewById(R.id.cardID_edttxt);
        cardCode=(EditText)findViewById(R.id.cardCode_edttxt);
        defaultCard=(CheckBox)findViewById(R.id.default_card);

        Intent intent = getIntent();

        if(intent.hasExtra("USER_ID")){
            userid = intent.getStringExtra("USER_ID");
            sessionid=intent.getStringExtra("SESSION_ID");
        }
        url="card/addnewcard";
    }

    public void addcard (View view){
        requestQueue = Volley.newRequestQueue(this);
        System.out.println(" DEFAULT ====================== >>>>>>>>> " +defaultCard.isChecked());
        Log.d("userid userid", userid);
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
                    Log.d("user id",cardNumber.getText().toString());
                    jsonObject.put("cardNumber", cardNumber.getText().toString());
                    jsonObject.put("securityCode", cardCode.getText().toString());
                    jsonObject.put("userId", userid);
                    jsonObject.put("isDefault",defaultCard.isChecked());
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


    private void setResponse(String s) {

        Gson gson = new Gson();

        Message msg  =  gson.fromJson(s,Message.class);

        if(msg.getMessage().equals("Card added successfully")){
            Intent i = new Intent(this,ViewCardActivity.class);
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
}
