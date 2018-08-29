package com.parrotize.restapideneme.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("posts")
    Call<List<Post>> getPostsById(@Query("id") Integer id);

    @GET("posts")
    Call<List<Post>> getPostsByUserId(@Query("userId") Integer userId);

    @POST("posts")
    Call<Post> postNewPost(@Body Post post);


    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users")
    Call<List<User>> getUsersById(@Query("id") Integer id);

    @POST("users")
    Call<User> postNewUser(@Body User user);


}
