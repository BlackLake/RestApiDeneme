package com.parrotize.restapideneme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parrotize.restapideneme.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonPostsActivity)
    public void onClickPosts(View view)
    {
        Intent intent = new Intent(this,PostsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonUsersActivity)
    public void onClickUsers(View view)
    {
        Intent intent = new Intent(this,UsersActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonAlbumsActivity)
    public void onClickAlbums(View view)
    {
        Intent intent = new Intent(this,AlbumsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonPhotosActivity)
    public void onClickPhotos(View view)
    {
        Intent intent = new Intent(this,PhotosActivity.class);
        startActivity(intent);
    }
}
