package com.codepath.apps.mysimpletweetsfragments.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweetsfragments.R;
import com.codepath.apps.mysimpletweetsfragments.TwitterApplication;
import com.codepath.apps.mysimpletweetsfragments.TwitterClient;
import com.codepath.apps.mysimpletweetsfragments.fragments.UserTimelineFragment;
import com.codepath.apps.mysimpletweetsfragments.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ProfileActivity extends ActionBarActivity {
    TwitterClient _client;
    User _user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _client = TwitterApplication.getRestClient();
        // Get the account info
        _client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                _user = User.fromJSON(response);    // Current user account's info
                getSupportActionBar().setTitle("@" + _user.getScreenName());
                populateProfileHeader(_user);
            }
        });

        // Get screen name from activity that launches this
        String screenName = getIntent().getStringExtra("screen_name");
        if (savedInstanceState == null) {
            // Create the user timeline fragment
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);
            // Display user fragment within this activity dynamically
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, userTimelineFragment);
            ft.commit();    // Changes the fragments
        }
    }

    private void populateProfileHeader(User user) {
        TextView tvName = (TextView) findViewById(R.id.tvFullName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvName.setText(user.getName());
        tvTagline.setText(user.getTagline());
        tvFollowers.setText(user.getFollowersCount() + " followers");
        tvFollowing.setText(user.getFriendsCount() + " following");
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
