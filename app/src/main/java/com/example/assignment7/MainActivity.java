package com.example.assignment7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Picture;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ConnectivityManager cm;
    private Context context;
    private RequestQueue queue = null;

    private Button searchButton;
    private EditText searchText;
    private ArrayList<Picture> imagelist = new ArrayList<>();
    private mAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new mAdapter(this, R.layout.image, imagelist);
        ListView listview = findViewById(R.id.listView);
        listview.setAdapter(adapter);

        searchButton = new Button(this);
        searchText = new EditText(this);

        searchButton.setText("Search");
        searchText.setHint("Insert a tag");

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    doJsonQuery(searchText.getText().toString());
                }
        });

        LinearLayout toolb = findViewById(R.id.toolbar);
        toolb.setBackgroundColor(Color.MAGENTA);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
        searchText.setBackgroundColor(Color.WHITE);
        toolb.addView(searchText, textParams);
        toolb.addView(searchButton, buttonParams);

        if (context == null) {
            context = getApplicationContext();
        }

        if(!testInternet(context)) {
            Toast.makeText(context, "No network available.", Toast.LENGTH_SHORT).show();
        }
    }
    private void doJsonQuery(String tag) {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        String url = "https://loremflickr.com/json/g/320/240/"+tag+"/all";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            getDataFromResponse(response);
                        } catch (JSONException e) {
                            searchText.setText(e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Tapahtui virhe " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }
    public void getDataFromResponse (JSONObject response) throws JSONException {

        Gson gson = new Gson();

        Picture pict = gson.fromJson(response.toString(), Picture.class);

        imagelist.add(pict);
    }

    private boolean testInternet(Context context) {

        final Network[] allNetworks;
        cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        allNetworks = cm.getAllNetworks();
        return (allNetworks != null);
    }
}