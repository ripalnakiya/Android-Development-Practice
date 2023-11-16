package com.example.a44retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView resultView;
    private JsonPlaceholderAPI jsonPlaceholderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.resultView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

//        onGetPostRequest();
//        onGetCommentRequest();
    }

    private void onGetPostRequest() {
//        Call<List<Post>> call = jsonPlaceholderAPI.getPosts();
//        Call<List<Post>> call = jsonPlaceholderAPI.getPosts("posts");
        Call<List<Post>> call = jsonPlaceholderAPI.getPosts("https://jsonplaceholder.typicode.com/posts");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()) {
                    resultView.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                showPosts(posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                resultView.setText(t.getMessage());
            }
        });
    }

    private void onGetCommentRequest() {
//        Call<List<Comment>> call = jsonPlaceholderAPI.getComments(3);

//        Call<List<Comment>> call = jsonPlaceholderAPI.getComments(3, "id", "desc");
//        Call<List<Comment>> call = jsonPlaceholderAPI.getComments(null, null, null);          This will return all 500 comments

//        Call<List<Comment>> call = jsonPlaceholderAPI.getComments(new Integer[]{1,2}, null, null);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("postId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");
        Call<List<Comment>> call = jsonPlaceholderAPI.getComments(parameters);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()) {
                    resultView.setText("Code: " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                showComments(comments);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                resultView.setText(t.getMessage());
            }
        });
    }

    private void showPosts(List<Post> posts) {
        StringBuilder sb = new StringBuilder();

        for (Post post : posts) {
            sb.append("Id: " + post.getId() + "\n");
            sb.append("UserId: " + post.getUserId() + "\n");
            sb.append("Title: " + post.getTitle() + "\n");
            sb.append("Text: " + post.getText() + "\n\n");
        }
        resultView.setText(sb.toString());
    }

    private void showComments(List<Comment> comments) {
        StringBuilder sb = new StringBuilder();
        for (Comment comment : comments) {
            sb.append("PostId: " + comment.getPostId() + "\n");
            sb.append("Id: " + comment.getId() + "\n");
            sb.append("Name: " + comment.getName() + "\n");
            sb.append("Email: " + comment.getEmail() + "\n");
            sb.append("Text: " + comment.getText() + "\n\n");
        }
        resultView.setText(sb.toString());
    }
}