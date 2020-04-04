package com.example.newretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<PostClass>> getPostClass();
    @GET("posts/{id}/comments")
    Call<List<CommentClass>> getCommentClass(@Path("id") int id);
    @POST("posts")
    Call<PostClass> sendData(@Body PostClass postClass);
}
