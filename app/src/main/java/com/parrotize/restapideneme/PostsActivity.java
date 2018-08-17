package com.parrotize.restapideneme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class PostsActivity extends AppCompatActivity {


    PostsAdapter postsAdapter;
    EditText editTextId;
    EditText editTextTitle;
    EditText editTextBody;
    List<Post> posts;
    ListView listView;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

    }

    public void onClickCreatePost(View view)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                if (posts==null)
                {
                    posts= new ArrayList<>();
                }

                editTextTitle= (EditText) findViewById(R.id.editText_title);
                editTextBody= (EditText) findViewById(R.id.editText_body);

                URL apiEndPoint = null;
                HttpsURLConnection myConnection = null;
                try {
                    // Create URL
                    apiEndPoint = new URL("https://jsonplaceholder.typicode.com/posts/");

                    // Create connection
                    myConnection = (HttpsURLConnection) apiEndPoint.openConnection();
                    myConnection.setRequestMethod("POST");

                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("title", editTextTitle.getText());
                    jsonObject.put("body", editTextBody.getText());
                    jsonObject.put("userId",1);

                    // Create the data
                    String myData = jsonObject.toString();
                    //String myData = "{\"userId\": 100,\"id\": 100,\"title\": \"main title\",\"body\": \"main body\"}";

                    // Enable writing
                    myConnection.setDoOutput(true);

                    // Write the data
                    myConnection.getOutputStream().write(myData.getBytes());

                    if (myConnection.getResponseCode() == 201) {
                        // Success
                        // Further processing here
                        InputStream responseBody = myConnection.getInputStream();

                        JsonReader jsonReader = new JsonReader(new InputStreamReader(responseBody, "UTF-8"));

                        post = readPost(jsonReader);
                        //post = (Post) readPostsArray(jsonReader).get(0);

                        posts.add(post);

                    } else {
                        // Error handling code goes here
                    }
                    myConnection.disconnect();



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        if (posts != null)
        {
            listView = (ListView) findViewById(R.id.listViewPosts);
            postsAdapter=new PostsAdapter(this,posts);
            listView.setAdapter(postsAdapter);
        }

    }


    public void onClickGetByUserId(View view) throws ExecutionException, InterruptedException {
        editTextId = (EditText) findViewById(R.id.editText_id);
        Toast.makeText(PostsActivity.this, editTextId.getText(), Toast.LENGTH_SHORT).show();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                editTextId = (EditText) findViewById(R.id.editText_id);

                URL apiEndPoint = null;
                HttpsURLConnection myConnection = null;
                try {
                    // Create URL
                    apiEndPoint = new URL("https://jsonplaceholder.typicode.com/posts?userId="+editTextId.getText());

                    // Create connection
                    myConnection = (HttpsURLConnection) apiEndPoint.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                        InputStream responseBody = myConnection.getInputStream();

                        posts = readJsonStream(responseBody);


                    } else {
                        // Error handling code goes here
                    }
                    myConnection.disconnect();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        if (posts != null)
        {
            listView = (ListView) findViewById(R.id.listViewPosts);
            postsAdapter=new PostsAdapter(this,posts);
            listView.setAdapter(postsAdapter);
        }
    }
    public void onClickGetByPostId(View view)
    {
        editTextId = (EditText) findViewById(R.id.editText_id);
        Toast.makeText(PostsActivity.this, editTextId.getText(), Toast.LENGTH_SHORT).show();


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                editTextId = (EditText) findViewById(R.id.editText_id);

                URL apiEndPoint = null;
                HttpsURLConnection myConnection = null;
                try {
                    // Create URL
                    apiEndPoint = new URL("https://jsonplaceholder.typicode.com/posts?id="+editTextId.getText());

                    // Create connection
                    myConnection = (HttpsURLConnection) apiEndPoint.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                        InputStream responseBody = myConnection.getInputStream();

                        posts = readJsonStream(responseBody);


                    } else {
                        // Error handling code goes here
                    }
                    myConnection.disconnect();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        if (posts != null)
        {
            listView = (ListView) findViewById(R.id.listViewPosts);
            postsAdapter=new PostsAdapter(this,posts);
            listView.setAdapter(postsAdapter);
        }
    }

    public List readJsonStream(InputStream in) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readPostsArray(jsonReader);
        }
        finally {
            jsonReader.close();
        }
    }


    public List readPostsArray(JsonReader jsonReader) throws IOException {
        List<Post> posts = new ArrayList();

        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            posts.add(readPost(jsonReader));
        }
        jsonReader.endArray();
        return posts;
    }

    public Post readPost(JsonReader jsonReader) throws IOException {

        int userId = -1;
        int id= -1;
        String title = null;
        String body = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext())
        {
            String name = jsonReader.nextName();
            if (name.equals("userId"))
            {
                userId = jsonReader.nextInt();
            } else if (name.equals("id"))
            {                id = jsonReader.nextInt();

            } else if (name.equals("title"))
            {
                title = jsonReader.nextString();

            } else if (name.equals("body"))
            {
                body = jsonReader.nextString();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();

        Post post = new Post(userId,id,title,body);
        return post;
    }


}
