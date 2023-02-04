package com.example.usesretrofit.interfaces;
import com.example.usesretrofit.constants.Constants;
import com.example.usesretrofit.models.Posts;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface ApiService {
    @GET(Constants.GETPOSTS)
    Call<List<Posts>> getPosts();
}
