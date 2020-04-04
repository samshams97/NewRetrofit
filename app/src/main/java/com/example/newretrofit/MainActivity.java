package com.example.newretrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtShowResult;
    private JsonPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtShowResult = findViewById(R.id.txtShowResult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
      // getPost();
      //  getComment();
        sendPost();

    }

    private void sendPost() {
        final PostClass postClass = new PostClass(7,"hello world","Hi");
        Call<PostClass> postClassCall = jsonPlaceHolder.sendData(postClass);
        postClassCall.enqueue(new Callback<PostClass>() {
            @Override
            public void onResponse(Call<PostClass> call, Response<PostClass> response) {
                if(!response.isSuccessful()){
                    txtShowResult.setText("Code:" +response.code());
                    return;
                }
                PostClass postClass1 = response.body();
                String postData = "";
                postData += "code:" +response.code()+ "\n";
                postData += "userId:" +postClass1.getUserId() + "\n";
                postData += "userId:" +postClass1.getTitle() + "\n";
                postData += "userId:" +postClass1.getText() + "\n\n";
                txtShowResult.append(postData);
            }

            @Override
            public void onFailure(Call<PostClass> call, Throwable t) {
                txtShowResult.setText(t.getMessage());

            }
        });
    }

    private void getComment() {
        Call<List<CommentClass>> call = jsonPlaceHolder.getCommentClass(5);
        call.enqueue(new Callback<List<CommentClass>>() {
            @Override
            public void onResponse(Call<List<CommentClass>> call, Response<List<CommentClass>> response) {
                if ((!response.isSuccessful())){
                    txtShowResult.setText("Code : " + response.code());
                    return;
                }
                List<CommentClass> commentClasses = response.body();
                for (CommentClass commentClass : commentClasses){
                    String comment = "";
                    comment += "Post id :" + commentClass.getPostId() + "\n";
                    comment += "id :" + commentClass.getId() + "\n";
                    comment += "name :" + commentClass.getName() + "\n";
                    comment += "email :" + commentClass.getEmail() + "\n";
                    comment += "Body :" + commentClass.getText() + "\n\n";
                    txtShowResult.append(comment);
                }
            }

            @Override
            public void onFailure(Call<List<CommentClass>> call, Throwable t) {
                txtShowResult.setText(t.getMessage());

            }
        });
    }

    private void getPost() {
        Call<List<PostClass>> listCall = jsonPlaceHolder.getPostClass();
        listCall.enqueue(new Callback<List<PostClass>>() {
            @Override
            public void onResponse(Call<List<PostClass>> call, Response<List<PostClass>> response) {
                if (!response.isSuccessful()) {
                    txtShowResult.setText("code :" + response.code());
                    return;
                }
                List<PostClass> postClasses = response.body();
                for (PostClass postClass : postClasses) {
                    String content = "";
                    content += "user id :" + postClass.getUserId() + "\n";
                    content += " id :" + postClass.getId() + "\n";
                    content += " title :" + postClass.getTitle() + "\n";
                    content += " Body :" + postClass.getText() + "\n\n";
                    txtShowResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<PostClass>> call, Throwable t) {
                txtShowResult.setText(t.getMessage());

            }
        });
    }
}
