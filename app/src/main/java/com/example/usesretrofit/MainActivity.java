package com.example.usesretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usesretrofit.constants.Constants;
import com.example.usesretrofit.interfaces.ApiService;
import com.example.usesretrofit.models.Posts;

import java.util.List;

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

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URIAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //instance of API
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



}