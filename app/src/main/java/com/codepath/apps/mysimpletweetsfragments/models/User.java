package com.codepath.apps.mysimpletweetsfragments.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by loudl_000 on 3/7/2015.
 */

/*
    "user": {
      "name": "OAuth Dancer",
      "profile_sidebar_fill_color": "DDEEF6",
      "profile_background_tile": true,
      "profile_sidebar_border_color": "C0DEED",
      "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
      "created_at": "Wed Mar 03 19:37:35 +0000 2010",
      ...
      "id": 119476949,
      "id_str": "119476949",
      "screen_name": "oauth_dancer"
 */
public class User {
    // List attributes
    private String _name;
    private long _uid;
    private String _screenName;
    private String _profileImageUrl;
    private String _tagline;
    private int _followersCount;
    private int _followingCount;

    public String getTagline() {
        return _tagline;
    }

    public int getFollowersCount() {
        return _followersCount;
    }

    public int getFriendsCount() {
        return _followingCount;
    }

    public String getName() {
        return _name;
    }

    public long getUid() {
        return _uid;
    }

    public String getScreenName() {
        return _screenName;
    }

    public String getProfileImageUrl() {
        return _profileImageUrl;
    }

    // Deserialize the user JSON ==> User object
    public static User fromJSON(JSONObject json) {
        // Extract and fill the values
        User user = new User();
        try {
            user._name = json.getString("name");
            user._uid = json.getLong("id");
            user._screenName = json.getString("screen_name");
            user._profileImageUrl = json.getString("profile_image_url");
            user._tagline = json.getString("description");
            user._followersCount = json.getInt("followers_count");
            user._followingCount = json.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return the User
        return user;
    }
}
