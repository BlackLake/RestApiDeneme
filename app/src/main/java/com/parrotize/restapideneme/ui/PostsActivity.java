package com.parrotize.restapideneme.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parrotize.restapideneme.model.GetDataService;
import com.parrotize.restapideneme.model.Post;
import com.parrotize.restapideneme.R;
import com.parrotize.restapideneme.model.RetrofitClientInstance;
import com.parrotize.restapideneme.adapter.PostsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {


    PostsAdapter postsAdapter;
    @BindView(R.id.recyclerViewPosts) RecyclerView recyclerViewPosts;
    ProgressDialog progressDialog;

    @BindView(R.id.editText_id) EditText editTextId;
    @BindView(R.id.editText_title) EditText editTextTitle;
    @BindView(R.id.editText_body) EditText editTextBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        getAllPosts();
    }

    @OnClick(R.id.buttonCreatePost)
    public void onClickCreatePost(View view)
    {
        String title = editTextTitle.getText().toString();
        String body = editTextBody.getText().toString();

        Post post = new Post(100,200,title,body);

        createNewPost(post);
    }

    @OnClick(R.id.buttonGetPostByUserId)
    public void onClickGetByUserId(View view)
    {
        String id = editTextId.getText().toString();
        Toast.makeText(PostsActivity.this, id, Toast.LENGTH_SHORT).show();

        getPostsByUserId(Integer.parseInt(id));

    }

    @OnClick(R.id.buttonGetPostByPostId)
    public void onClickGetPostByPostId(View view)
    {
        String id = editTextId.getText().toString();
        Toast.makeText(PostsActivity.this, id, Toast.LENGTH_SHORT).show();

        getPostsById(Integer.parseInt(id));
    }

    private void createNewPost(final Post post)
    {
        progressDialog = new ProgressDialog(PostsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Post> call = service.postNewPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                progressDialog.dismiss();
                List<Post> posts = new ArrayList<>();
                posts.add(response.body());
                generateDataList(posts);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllPosts()
    {
        progressDialog = new ProgressDialog(PostsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Post>> call = service.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPostsById(Integer id)
    {
        progressDialog = new ProgressDialog(PostsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Post>> call = service.getPostsById(id);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPostsByUserId(Integer id)
    {
        progressDialog = new ProgressDialog(PostsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Post>> call = service.getPostsByUserId(id);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Post> posts) {
        postsAdapter = new PostsAdapter(this,posts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PostsActivity.this);
        recyclerViewPosts.setLayoutManager(layoutManager);
        recyclerViewPosts.setAdapter(postsAdapter);
    }

}
