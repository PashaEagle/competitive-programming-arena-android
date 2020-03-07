package com.example.arena;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) throws JSONException {

        final TextView label = (TextView) findViewById(R.id.textView);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        label.setText("Clicked");

// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.109:7777/api/auth/login";
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("username", editUsername.getText().toString());
        jsonBody.put("password", editPassword.getText().toString());

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        label.setText("Response is: " + response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        label.setText("That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}
