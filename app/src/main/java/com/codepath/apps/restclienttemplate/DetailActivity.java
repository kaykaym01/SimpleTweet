package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {


    ImageView ivProfileImage;
    TextView tvName;
    TextView tvScreenName;
    TextView tvTweet;
    TextView tvTimestamp;
    TextView tvSource;
    TextView tvRetweets;
    TextView tvRetweetText;
    TextView tvFavorites;
    TextView tvFavoriteText;
    View div1;
    View div2;

    Context context;
    public static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvTweet = findViewById(R.id.tvTweet);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvName = findViewById(R.id.tvName);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvSource = findViewById(R.id.tvSource);
        tvRetweets = findViewById(R.id.tvRetweets);
        tvRetweetText = findViewById(R.id.tvRetweetText);
        tvFavorites = findViewById(R.id.tvFavorites);
        tvFavoriteText = findViewById(R.id.tvFavoriteText);
        div1 = findViewById(R.id.divider);
        div1 = findViewById(R.id.divider2);
        context = DetailActivity.this;

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvTweet.setText(tweet.body);
        tvName.setText(tweet.user.name);
        Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        tvScreenName.setText("@" + tweet.user.screenName);
        tvRetweets.setText(Integer.toString(tweet.retweetCount));
        if (tweet.retweetCount == 1){
            tvRetweetText.setText("Retweet");
        }
        if (tweet.retweetCount == 0){
            tvRetweets.setVisibility(View.GONE);
            tvRetweetText.setVisibility(View.GONE);
        }
        tvFavorites.setText(Integer.toString(tweet.favoriteCount));
        if (tweet.favoriteCount == 1){
            tvFavoriteText.setText("Like");
        }
        if (tweet.favoriteCount == 0){
            tvFavorites.setVisibility(View.INVISIBLE);
            tvFavoriteText.setVisibility(View.INVISIBLE);
        }
        if (tweet.favoriteCount == '0' && tweet.retweetCount == '0'){
            div1.setVisibility(View.INVISIBLE);
            div2.setVisibility(View.INVISIBLE);
        }
        tvTimestamp.setText(TimeFormatter.getTimeStamp(tweet.createdAt) + " · ");
        tvSource.setText(Html.fromHtml(tweet.source));
        tvSource.setClickable(true);
        tvSource.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
