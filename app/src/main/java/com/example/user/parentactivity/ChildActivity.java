package com.example.user.parentactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://ae.priceomania.com/mobileappwebservices/getchildcategory?catId=14";
    ListView childlistview;
    List<Child> childList;

    String child_list;
    //public static List<String> childs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        childlistview = (ListView) findViewById(R.id.listview1);
        childList = new ArrayList<>();

        Intent intent=getIntent();
        child_list = intent.getStringExtra("category_id");


        loadItemList();
    }
    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.has(child_list)){

                            }
                            JSONArray childArray = obj.getJSONArray("products");

                            for (int i = 0; i < childArray.length(); i++) {
                                JSONObject childObject = childArray.getJSONObject(i);

                                Child child = new Child(childObject.getString("category_id"),
                                        childObject.getString("category_name"),

                                        childObject.getString("category_image"));

                                childList.add(child);
                            }

                            ChildAdapter adapter = new ChildAdapter(childList, getApplicationContext());

                            childlistview.setAdapter(adapter);
//

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
