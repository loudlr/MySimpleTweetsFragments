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
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    // Deserialize the user JSON ==> User object
    public static User fromJSON(JSONObject json) {
        // Extract and fill the values
        User user = new User();
        try {
            user.name = json.getString("name");
            user.uid = json.getLong("id");
            user.screenName = json.getString("screen_name");
            user.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // return the User
        return user;
    }
}
