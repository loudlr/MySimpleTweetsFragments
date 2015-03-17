package com.codepath.apps.mysimpletweetsfragments.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.mysimpletweetsfragments.TwitterApplication;
import com.codepath.apps.mysimpletweetsfragments.TwitterClient;
import com.codepath.apps.mysimpletweetsfragments.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

public class MentionsTimelineFragment extends TweetsListFragment {
    private TwitterClient _client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the client
        _client = TwitterApplication.getRestClient();   // Singleton client
        populateTimeline();
    }

    // Send an API request to get the timeline JSON.
    // Fill the listview by creating the tweet objects from the JSON.
    private void populateTimeline() {
        _client.getMentionsTimeline(new JsonHttpResponseHandler() {
            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                // Deserialize JSON
                // Create models and add them to the adapter
                // Load model data into listview
                //ArrayList<Tweet> tweets = Tweet.fromJSONArray(json);
                addAll(Tweet.fromJSONArray(json));
            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }


}
