package com.codepath.apps.mysimpletweetsfragments.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.mysimpletweetsfragments.R;
import com.codepath.apps.mysimpletweetsfragments.TwitterApplication;
import com.codepath.apps.mysimpletweetsfragments.TwitterClient;
import com.codepath.apps.mysimpletweetsfragments.adapters.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweetsfragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {
    private TwitterClient _client;
    private ArrayList<Tweet> _tweets;
    private TweetsArrayAdapter _aTweets;
    private ListView _lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // Find the listview
        _lvTweets = (ListView) findViewById(R.id.lvTweets);
        // Create the ArrayList from data source
        _tweets = new ArrayList<>();
        // Construct the adapter from data source
        _aTweets = new TweetsArrayAdapter(this, _tweets);
        // Connect adapter to listview
        _lvTweets.setAdapter(_aTweets);
        // Get the client
        _client = TwitterApplication.getRestClient();   // Singleton client
        populateTimeline();
    }

    // Send an API request to get the timeline JSON.
    // Fill the listview by creating the tweet objects from the JSON.
    private void populateTimeline() {
        _client.getHomeTimeline(new JsonHttpResponseHandler() {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                // Deserialize JSON
                // Create models and add them to the adapter
                // Load model data into listview
                //ArrayList<Tweet> tweets = Tweet.fromJSONArray(json);
                _aTweets.addAll(Tweet.fromJSONArray(json));
                Log.d("DEBUG", _aTweets.toString());
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
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
