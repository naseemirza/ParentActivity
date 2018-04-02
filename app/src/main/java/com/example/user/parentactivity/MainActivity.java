package com.example.user.parentactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://ae.priceomania.com/mobileappwebservices/getparentcategory";
    ListView listView;
    List<Parent> parentList;

    int parentID ;
    public static List<String> childs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview1);
        parentList = new ArrayList<>();

        loadItemList();
    }

    private void loadItemList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parentList.clear();

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray parentArray = obj.getJSONArray("products");

                            for (int i = 0; i < parentArray.length(); i++) {
                                JSONObject heroObject = parentArray.getJSONObject(i);

                                Parent parent = new Parent(heroObject.getString("category_id"),
                                        heroObject.getString("category_name"),

                                        heroObject.getString("category_image"));

                                parentList.add(parent);
                            }

                            ParentAdapter adapter = new ParentAdapter(parentList, getApplicationContext());

                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                    Intent intent=new Intent(MainActivity.this,ChildActivity.class);
                                    String cat_id=((TextView) view.findViewById(R.id.parent_id)).getText().toString();
                                     intent.putExtra("category_id", cat_id);
                                     startActivity(intent);
                                }
                            });

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
