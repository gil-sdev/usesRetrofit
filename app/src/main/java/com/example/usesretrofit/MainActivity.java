package com.example.usesretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usesretrofit.constants.Constants;
import com.example.usesretrofit.interfaces.ApiService;
import com.example.usesretrofit.models.Comment;
import com.example.usesretrofit.models.Posts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btnGet, getBtnGet;
    TextView txtScreen;

    Retrofit retrofit;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = (Button) findViewById(R.id.btnGet);
        getBtnGet =(Button) findViewById(R.id.btnGetPost);
        txtScreen = (TextView) findViewById(R.id.txtResult);

        btnGet.setOnClickListener(btnGetClick);
        getBtnGet.setOnClickListener(btnCommentClick);

// Creating an instance of retrofit.
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URIAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        // Creating an instance of the interface ApiService.
        apiService = retrofit.create(ApiService.class);

    }

    View.OnClickListener btnGetClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                getPosts();
            }catch (Exception e){
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener btnCommentClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         //   getCommentsIdByQuery();
         //   createPost();
        //    createPostFormUrlEncoded();
            createPostBycreatePostByMap();
        }
    };

    private void getPosts(){
        //instance of retrofit


         //get all posts
        Call<List<Posts>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                String tmptext = "";
                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,""+response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Posts> data = response.body();

                for (Posts post:data) {
                    tmptext += post.getUserId() + "\n";
                    tmptext += post.getId()+ "\n";
                    tmptext += post.getTitle()+ "\n";
                }
                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }

    private void getComments(){
        //instance of retrofit


        //get all posts
  // Creating a call object that will be used to make the network request.
        Call<List<Comment>> call = apiService.getComments(5);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                String tmptext = "";
                List<Comment> data = response.body();

                for (Comment comment:data) {
                    tmptext += comment.getPostId() + "\n";
                    tmptext += comment.getId() + "\n";
                    tmptext += comment.getName()+ "\n";
                    tmptext += comment.getEmail()+ "\n";

                }
                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }
    private void getCommentsIdByQuery(){

        // Calling the method getComments from the interface ApiService.
        Call<List<Comment>> call = apiService.getComments(2);

   // Making a call to the API and getting the response.
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(),Toast.LENGTH_LONG).show();
                }
                String tmptext = "";
                List<Comment> data = response.body();

                for (Comment comment:data) {
                    tmptext += comment.getPostId() + "\n";
                    tmptext += comment.getId() + "\n";
                    tmptext += comment.getName()+ "\n";
                    tmptext += comment.getEmail()+ "\n";

                }
                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void createPost(){
        Posts posts = new Posts(23,"New Title of post","We are using retrofit to post by verbo post");
        Call<Posts> call = apiService.createPost(posts);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                String tmptext = "";
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                Posts postResponse = response.body();

                tmptext +="Code: "+ response.code() + "\n";
                tmptext += "Id: " +postResponse.getId()+ "\n";
                tmptext +=  "User: " +postResponse.getUserId()+ "\n";
                tmptext +=  "Title: " +postResponse.getTitle()+ "\n";
                tmptext +=  "Body: " +postResponse.getBody()+ "\n";

                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    private void createPostFormUrlEncoded(){
       // Posts posts = new Posts(10,"New Title of post createPostFormUrlEncoded","We are using retrofit to post by verbo post");
        Call<Posts> call = apiService.createPostByFormUrlEncoded(10, "New Title of post createPostFormUrlEncoded","We are using retrofit to post by verbo post");

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                String tmptext = "";
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                Posts postResponse = response.body();

                tmptext +="Code: "+ response.code() + "\n";
                tmptext += "Id: " +postResponse.getId()+ "\n";
                tmptext +=  "User: " +postResponse.getUserId()+ "\n";
                tmptext +=  "Title: " +postResponse.getTitle()+ "\n";
                tmptext +=  "Body: " +postResponse.getBody()+ "\n";

                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }



    private void createPostBycreatePostByMap(){
        Map<String, String> fields = new HashMap<>();
        fields.put("userId","15" );
        fields.put("title","New Title of post createPostFormUrlEncoded Fields MAp" );
        fields.put("body","We are using retrofit to post by verbo pos" );
        Call<Posts> call = apiService.createPostByMap(fields);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                String tmptext = "";
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.message(),Toast.LENGTH_LONG).show();
                    return;
                }
                Posts postResponse = response.body();

                tmptext +="Code: "+ response.code() + "\n";
                tmptext += "Id: " +postResponse.getId()+ "\n";
                tmptext +=  "User: " +postResponse.getUserId()+ "\n";
                tmptext +=  "Title: " +postResponse.getTitle()+ "\n";
                tmptext +=  "Body: " +postResponse.getBody()+ "\n";

                txtScreen.setText(tmptext);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }





}