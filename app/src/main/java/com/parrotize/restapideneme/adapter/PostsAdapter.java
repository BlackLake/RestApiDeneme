package com.parrotize.restapideneme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parrotize.restapideneme.model.Post;
import com.parrotize.restapideneme.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.CustomViewHolder>{

    private Context context; //context
    private List<Post> posts ;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView textViewUserId;
        TextView textViewId;
        TextView textViewTitle;
        TextView textViewBody;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            textViewUserId =  mView.findViewById(R.id.textView_user_Id);
            textViewId =  mView.findViewById(R.id.textView_id);
            textViewTitle = mView.findViewById(R.id.textView_title);
            textViewBody =  mView.findViewById(R.id.textView_body);
/*

*/
        }
    }

    @Override
    public PostsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_post, parent, false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PostsAdapter.CustomViewHolder holder, int position) {

        //sets the text for item name and item description from the current item object
        holder.textViewUserId.setText("User id : "+Integer.toString(posts.get(position).getUserId()));
        holder.textViewId.setText("Post id : "+Integer.toString(posts.get(position).getId()));
        holder.textViewTitle.setText(posts.get(position).getTitle());
        holder.textViewBody.setText(posts.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
