package com.example.parstagram.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.parstagram.R;
import com.example.parstagram.model.Post;
import com.example.parstagram.model.PostAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Timeline extends Fragment {

    public static final String TAG = "Timeline";

    List<Post>posts;
    SwipeRefreshLayout swipeContainer;
    RecyclerView rvPosts;
    PostAdapter postAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        rvPosts = view.findViewById(R.id.rvPosts);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(getActivity(), posts);

        rvPosts.setAdapter(postAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryPosts();
            }
        });
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_purple,
                android.R.color.black);


        queryPosts();
        return view;
    }


    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        query.include(Post.KEY_USER);
        query.include(Post.KEY_IMAGE);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> attrivedPosts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                postAdapter.clear();
                postAdapter.addAll(attrivedPosts);
                swipeContainer.setRefreshing(false);
            }
        });
    }
}