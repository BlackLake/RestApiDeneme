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
import com.parrotize.restapideneme.R;
import com.parrotize.restapideneme.model.RetrofitClientInstance;
import com.parrotize.restapideneme.model.User;
import com.parrotize.restapideneme.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    EditText editTextUserName;
    EditText editTextName;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        getAllUsers();
    }

    public void onClickCreateNewUser(View view)
    {

        editTextUserName = findViewById(R.id.editTextUserName);
        String userName = editTextUserName.getText().toString();
        editTextName = findViewById(R.id.editTextName);
        String name = editTextName.getText().toString();
        editTextEmail = findViewById(R.id.editTextEmail);
        String email = editTextEmail.getText().toString();

        User user = new User(100, name,userName,email);

        CreateNewUser(user);
    }

    private void CreateNewUser(final User user)
    {
        progressDoalog = new ProgressDialog(UsersActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<User> call = service.postNewUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDoalog.dismiss();
                List<User> users = new ArrayList<>();
                users.add(response.body());
                generateDataList(users);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(UsersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllUsers()
    {
        progressDoalog = new ProgressDialog(UsersActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<User>> call = service.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(UsersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserById(Integer id)
    {
        progressDoalog = new ProgressDialog(UsersActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<User>> call = service.getUsersById(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(UsersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<User> users) {
        recyclerView = findViewById(R.id.recyclerViewUsers);
        userAdapter = new UserAdapter(this,users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UsersActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
    }

}
