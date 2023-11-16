package com.example.a44retrofit;

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
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

// Retrofit will automatically Implement these interfaces
public interface JsonPlaceholderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();

    // Directly get results from URL
    @GET
    Call<List<Post>> getPosts(@Url String url);

//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int postId);

    // For single Query
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);

    // For multiple Queries
    @GET("comments")
    Call<List<Comment>> getComments(
            @Query("postId") Integer[] postId,
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy
    );
    // Use of wrapper classes (Integer) allows the values to be nullable
    // We can query multiple values by declaring the Query as array

    // Alternate way for Multiple Queries
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String, String> parameters);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    
}
