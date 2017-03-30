package com.joshuaai.pinterestclone;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.joshuaai.pinterestclone.Adapters.PinterestAdapter;
import com.joshuaai.pinterestclone.Models.Config;
import com.joshuaai.pinterestclone.Models.SpaceItemDecoration;
import com.joshuaai.pinterestclone.Models.UserImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;

    //Creating list of user images
    private List<UserImage> userImageList;

    // Creating views
    private RecyclerView mRecyclerView;
    private PinterestAdapter pinterestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("Pinterest Clone");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.images_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        userImageList = new ArrayList<>();

        getData();

    }

    // This method will get data from the web api
    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            UserImage image = new UserImage();
            JSONObject jsonObject;
            try {
                jsonObject = array.getJSONObject(i);
                image.setImageUrl(jsonObject.getString(Config.TAG_IMAGE_URL));
                image.setTitle(jsonObject.getString(Config.TAG_NAME));
                image.setDescription(jsonObject.getString(Config.TAG_DESCRIPTION));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            userImageList.add(image);
        }
        pinterestAdapter = new PinterestAdapter(userImageList, this);
        mRecyclerView.setAdapter(pinterestAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
