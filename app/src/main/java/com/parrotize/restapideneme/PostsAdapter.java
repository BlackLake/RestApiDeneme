package com.parrotize.restapideneme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends BaseAdapter{

    private Context context; //context
    private List<Post> posts ;
    private Post post;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        post=posts.get(position);

        rowView = LayoutInflater.from(context).inflate(R.layout.list_item_post, null);

        // get the TextView for item name and item description
        TextView textViewUserId = (TextView) rowView.findViewById(R.id.textView_user_Id);
        TextView textViewId = (TextView) rowView.findViewById(R.id.textView_id);
        TextView textViewTitle = (TextView) rowView.findViewById(R.id.textView_title);
        TextView textViewBody = (TextView) rowView.findViewById(R.id.textView_body);

        //sets the text for item name and item description from the current item object
        textViewUserId.setText("User id : "+Integer.toString(post.getUserId()));
        textViewId.setText("Post id : "+Integer.toString(post.getId()));
        textViewTitle.setText(post.getTitle());
        textViewBody.setText(post.getBody());

        // returns the view for the current row
        return rowView;
    }

    public void refresh(List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

}
