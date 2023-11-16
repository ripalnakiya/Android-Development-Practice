package com.example.a44retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

//        onGetPostRequest();
//        onGetCommentRequest();
//        onPostRequest();
//        onPutRequest();
//        onPatchRequest();
        onDeleteRequest();
    }

    private void onGetPostRequest() {
//        Call<List<Post>> call = jsonPlaceholderAPI.getPosts();
//        Call<List<Post>> call = jsonPlaceholderAPI.getPosts("posts");
        Call<List<Post>> call = jsonPlaceholderAPI.getPosts("https://jsonplaceholder.typicode.com/posts");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
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
                if (!response.isSuccessful()) {
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

    private void onPostRequest() {
//        Post post = new Post(23, "New title", "New text");
//        Call<Post> call = jsonPlaceholderAPI.createPost(post);

//        Call<Post> call = jsonPlaceholderAPI.createPost(23, "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "My title");
        Call<Post> call = jsonPlaceholderAPI.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    resultView.setText("Code: " + response.code());
                    return;
                }

                Post postRespose = response.body();
                resultView.setText("Code: " + response.code() + "\n\n");
                showPost(postRespose);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                resultView.setText(t.getMessage());
            }
        });
    }

    private void onPutRequest() {
        Post post = new Post(12, null, "New Text");
        Call<Post> call = jsonPlaceholderAPI.putPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    resultView.setText("Code: " + response.code());
                    return;
                }
                Post postRespose = response.body();
                resultView.setText("Code: " + response.code() + "\n\n");
                showPost(postRespose);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                resultView.setText(t.getMessage());
            }
        });
    }

    private void onPatchRequest() {
        Post post = new Post(12, null, "New Text");
        Call<Post> call = jsonPlaceholderAPI.patchPost(1, post);
        // This modifies only the userId and text field (Leaving id and title as original)

        // In some cases, we really want to replace the original title with a null value
        // In that case we'll need to force Gson to serialize null values
        // Gson gson = new GsonBuilder().serializeNulls().create(); and then pass it as argument as Convertor Factory
        // See line 36

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    resultView.setText("Code: " + response.code());
                    return;
                }
                Post postRespose = response.body();
                resultView.setText("Code: " + response.code() + "\n\n");
                showPost(postRespose);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                resultView.setText(t.getMessage());
            }
        });
    }

    private void onDeleteRequest() {
        Call<Void> call = jsonPlaceholderAPI.deletePost(1);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                resultView.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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

    private void showPost(Post post) {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + post.getId() + "\n");
        sb.append("UserId: " + post.getUserId() + "\n");
        sb.append("Title: " + post.getTitle() + "\n");
        sb.append("Text: " + post.getText() + "\n\n");

        resultView.append(sb.toString());
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