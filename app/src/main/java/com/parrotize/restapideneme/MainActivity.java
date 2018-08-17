package com.parrotize.restapideneme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPosts(View view)
    {
        Intent intent = new Intent(this,PostsActivity.class);
        startActivity(intent);
    }
    public void onClickUsers(View view)
    {

        Intent intent = new Intent(this,UsersActivity.class);
        startActivity(intent);
    }
    public void onClickAlbums(View view)
    {

        Intent intent = new Intent(this,AlbumsActivity.class);
        startActivity(intent);
    }
    public void onClickPhotos(View view)
    {

        Intent intent = new Intent(this,PhotosActivity.class);
        startActivity(intent);
    }
}
