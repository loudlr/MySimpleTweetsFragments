package com.codepath.apps.mysimpletweetsfragments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mysimpletweetsfragments.R;
import com.codepath.apps.mysimpletweetsfragments.adapters.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweetsfragments.models.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by loudl_000 on 3/16/2015.
 */
public class TweetsListFragment extends Fragment {
    private ArrayList<Tweet> _tweets;
    private TweetsArrayAdapter _aTweets;
    private ListView _lvTweets;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_tweets_list, parent, false);
        // Find the listview
        _lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        // Connect adapter to listview
        _lvTweets.setAdapter(_aTweets);
        return v;
    }

    // Creation lifecycle event
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ArrayList from data source
        _tweets = new ArrayList<>();
        // Construct the adapter from data source
        _aTweets = new TweetsArrayAdapter(getActivity(), _tweets);
    }

    public void addAll(List<Tweet> tweets) {
        _aTweets.addAll(tweets);
    }
}
