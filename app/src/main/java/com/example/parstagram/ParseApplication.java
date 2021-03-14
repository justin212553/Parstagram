package com.example.parstagram;

import android.app.Application;

import com.example.parstagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("AxwCazU3He2W3myKPNlgwaHIrSnUzBxb2UmvH1AH")
                .clientKey("ieqRrNViFviOyhLol2QbGTIqEVz3J0Tku0IWi65h")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
