package com.example.parstagram;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.parstagram.tabs.Logout;
import com.example.parstagram.tabs.Timeline;
import com.example.parstagram.tabs.Upload;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;

    public VPAdapter(@NonNull FragmentManager fm) {
        super(fm);
        items = new ArrayList<>();
        items.add(new Logout());
        items.add(new Timeline());
        items.add(new Upload());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
