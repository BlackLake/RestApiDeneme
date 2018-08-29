package com.parrotize.restapideneme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parrotize.restapideneme.R;
import com.parrotize.restapideneme.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder>{

    private Context context; //context
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView textViewId;
        TextView textViewUserName;
        TextView textViewName;
        TextView textViewEmail;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            textViewId =  mView.findViewById(R.id.textView_id);
            textViewUserName =  mView.findViewById(R.id.textView_user_name);
            textViewName = mView.findViewById(R.id.textView_name);
            textViewEmail =  mView.findViewById(R.id.textView_email);
            /*

             */
        }
    }

    @Override
    public UserAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_user, parent, false);
        return new UserAdapter.CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(UserAdapter.CustomViewHolder holder, int position) {

        //sets the text for item name and item description from the current item object
        holder.textViewId.setText("User id : "+Integer.toString(users.get(position).getId()));
        holder.textViewUserName.setText("UserName : "+users.get(position).getUserName());
        holder.textViewName.setText("Name : "+users.get(position).getName());
        holder.textViewEmail.setText("Email : "+users.get(position).geteMail());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}