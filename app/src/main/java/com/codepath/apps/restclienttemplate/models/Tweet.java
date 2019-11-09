package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public long id;
    public int retweetCount;
    public int favoriteCount;
    public String source;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    public Tweet(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("full_text")){
            body = jsonObject.getString("full_text");
        }
        else {
            body = jsonObject.getString("text");
        }
        createdAt = jsonObject.getString("created_at");
        user = new User(jsonObject.getJSONObject("user"));
        id = jsonObject.getLong("id");
        retweetCount = jsonObject.getInt("retweet_count");
        favoriteCount = jsonObject.getInt("favorite_count");
        source = jsonObject.getString("source");
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            tweets.add(new Tweet(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getFormattedTimestamp(){
        return TimeFormatter.getTimeDifference(createdAt);
    }
}
