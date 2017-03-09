package com.example.dq_bassey.github_users;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_user);
        final User user = (User)getIntent().getSerializableExtra(Common.USER_KEY);

        //Initialize ImageView
        ImageView imageView = (ImageView) findViewById(R.id.user_image_view);


       /* Picasso.with(this)
                .load(user.getPhotoUrl())
                .resize(250, 200)
                //.rotate(90)
                .into(imageView);
        */

        //Size of image displayed on list
        int imageWidth = 400;
        int imageHeight = 400;

        //Loading user photo into imageView
        Common.loadImageIntoView(user, imageView, getApplicationContext(), imageWidth, imageHeight);




        Button btn_share=(Button)findViewById(R.id.share_btn);
        btn_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt(user);
            }
        });

        Button btn_user_name = (Button) findViewById(R.id.user_name_detail);
        btn_user_name.setText("Username: "+ user.getUserName());

        TextView textView =(TextView)findViewById(R.id.url_textView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + user.getProfileUrl() + "'> " + user.getProfileUrl() + " </a>";
        textView.setText(fromHtml(text));
    }

    private void shareIt(User user) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        //sharingIntent.setType("text/html");
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share " + user.getUserName() + " Github Profile");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "\nCheck out this awesome developer @" + user.getUserName() + ", " +  user.getProfileUrl());
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}