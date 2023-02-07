package com.example.usesretrofit.interfaces;
import com.example.usesretrofit.constants.Constants;
import com.example.usesretrofit.models.Comment;
import com.example.usesretrofit.models.Posts;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Creating an interface that will be used to make the API calls.
public interface ApiService {

/**
 * It will return a list of posts.
 * 
 * @return A list of Posts objects.
 */
    @GET(Constants.GETPOSTS)
    Call<List<Posts>> getPosts();


    // set a variable in id
    ///posts/{id}/comments
    @GET(Constants.GETCOMENTS)
    Call<List<Comment>> getComments(@Path("id") int postId);

    /// comments?postId=1
/**
 * A GET request to the url: https://jsonplaceholder.typicode.com/comments?postId=1
 * 
 * @param postId The id of the post to retrieve comments for.
 * @return A list of comments.
 */
    @GET(Constants.GETCOMENTS)
    Call<List<Comment>> getCommentsQuer(@Query("postId") int postId);

    //MUltiple params in query
// The above code is using Retrofit to make a GET request to the url
// https://jsonplaceholder.typicode.com/posts/1/comments.
    @GET(Constants.GETCOMENTS)
    Call<List<Comment>> getCommentsQuer(
            @Query("postId") int postId,
            @Query("_sort") String sort,
            @Query("_order") String order);
    @POST("/posts")
    Call<Posts> createPost(@Body Posts post);

/**
 * "This function creates a post by sending a form-url-encoded request to the server."
 * 
 * The @FormUrlEncoded annotation tells Retrofit that the request body will use form-url-encoded
 * encoding
 * 
 * @param userId The id of the user
 * @param title The title of the post
 * @param body The request body.
 * @return A Call object with a generic type of Posts.
 */
    @FormUrlEncoded
    @POST("/posts")
    Call<Posts> createPostByFormUrlEncoded(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );

/**
 * It takes a Map<String, String> as a parameter, and sends it as a form-encoded request body
 * 
 * @param fields A map of keys and values.
 * @return A Call object with a generic type of Posts.
 */
    @FormUrlEncoded
    @POST("/posts")
    Call<Posts> createPostByMap(@FieldMap Map<String, String> fields);

}
