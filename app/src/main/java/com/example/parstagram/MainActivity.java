package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.parstagram.tabs.Logout;
import com.example.parstagram.tabs.Timeline;
import com.example.parstagram.tabs.Upload;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    private TabLayout tabLayout;
    private Timeline timeline;
    private Upload upload;
    private Logout logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeline = new Timeline();
        upload = new Upload();
        logout = new Logout();

        tabLayout = findViewById(R.id.tabMain);

        Fragment selected = timeline;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = timeline;
                if (position == 0) {
                    tab.setIcon(R.drawable.instagram_home_filled_24);
                    selected = timeline;
                } else if(position == 1) {
                    tab.setIcon(R.drawable.instagram_new_post_filled_24);
                    selected = upload;
                } else if(position == 2) {
                    tab.setIcon(R.drawable.instagram_user_filled_24);
                    selected = logout;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    tab.setIcon(R.drawable.instagram_home_outline_24);
                } else if(position == 1) {
                    tab.setIcon(R.drawable.instagram_new_post_outline_24);
                } else if(position == 2) {
                    tab.setIcon(R.drawable.instagram_user_outline_24);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        getSupportActionBar().setIcon(R.drawable.nux_dayone_landing_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("");
    }

}