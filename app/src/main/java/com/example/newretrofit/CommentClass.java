package com.example.newretrofit;

import com.google.gson.annotations.SerializedName;

public class CommentClass {
    private  int postId;
    private  int id;
    private  String name;
    private  String email;
    @SerializedName("Body")
    private  String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
