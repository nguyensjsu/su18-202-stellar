package com.starbucks.cmpe202.starbucksmobileapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Store;
import com.starbucks.cmpe202.starbucksmobileapp.responseVo.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText userid;
    EditText userpin;
    TextView message;
    String baseUrl = "http://10.0.0.67:8080/starbucks/";
    RequestQueue requestQueue;
    String url;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid=(EditText)findViewById(R.id.userID_edttxt);
        userpin=(EditText)findViewById(R.id.userPIN_edttxt);
        message=(TextView) findViewById(R.id.message_id);

        url="auth/authenticateUser";
    }

    public void login(View view){
        requestQueue = Volley.newRequestQueue(this);

        Log.d("userid userid", userid.getText().toString());
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
                public byte[] getBody() {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        Log.d("user id",userid.getText().toString());
                        jsonObject.put("userID", userid.getText().toString());
                        jsonObject.put("userPin", userpin.getText().toString());
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

        user  =  gson.fromJson(s,Users.class);

        if(user.getMessage().equals("Authenticated Successfully")){
            Intent i = new Intent(this,MenuActivity.class);
            i.putExtra("USER_ID",user.getUserId());
            i.putExtra("SESSION_ID",user.getSessionId());
            Log.d("LOGIN ACTIVITY","user_id: "+user.getUserId());
            Log.d("LOGIN ACTIVITY","session_id:" +user.getSessionId());
            startActivity(i);

        }

        else {
            message.setText(user.getMessage());
        }


    }
}
