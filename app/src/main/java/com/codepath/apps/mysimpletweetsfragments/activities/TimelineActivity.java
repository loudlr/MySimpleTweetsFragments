package com.codepath.apps.mysimpletweetsfragments.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweetsfragments.R;
import com.codepath.apps.mysimpletweetsfragments.fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweetsfragments.fragments.MentionsTimelineFragment;

public class TimelineActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        // Get the ViewPager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        // Set the ViewPager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        // Find the pager sliding tabs
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        // Attach the tabstrip to the ViewPager
        tabStrip.setViewPager(vpPager);
    }

    public void onProfileView(MenuItem mi) {
        // Launch the profile view
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

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
        return super.onOptionsItemSelected(item);
    }

    // Return the order of the fragments in the view pager
    public class TweetsPagerAdapter extends FragmentPagerAdapter {
        private String _tabTitles[] = {getString(R.string.home_tab), getString(R.string.mentions_tab)};

        // Adapter gets the manager to insert or remove fragments from the activity
        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Controls the order and creation of fragments within the pager
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        // Return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return _tabTitles[position];
        }

        // Returns how many fragments there are to swipe between
        @Override
        public int getCount() {
            return _tabTitles.length;
        }
    }
}
