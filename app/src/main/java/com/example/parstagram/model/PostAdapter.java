package com.example.parstagram.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parstagram.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.Collections;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";
    Context context;
    List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View PostView = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(PostView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPostimageTimeline;
        TextView tvDescriptionTimeline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPostimageTimeline =itemView.findViewById(R.id.ivPostimageTimeline);
            tvDescriptionTimeline = itemView.findViewById(R.id.tvDescriptionTImeline);

        }

        public void bind(Post post) {
            tvDescriptionTimeline.setText(post.getDescription());

            ParseFile image = post.getImage();

            image.getDataInBackground(new GetDataCallback() {

                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        ivPostimageTimeline.setImageBitmap(bmp);
                    }
                    else {
                        Log.e(TAG,"Problem load image the data.",e);
                    }
                }
            });
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        Collections.reverse(posts);
        notifyDataSetChanged();
    }
}
