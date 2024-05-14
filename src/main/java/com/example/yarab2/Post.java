package com.example.yarab2;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Post implements Serializable ,Comparable<Post>  {
    static int counter = 1;
    final private int id;
    private profile author;
    private String content;
    private String img;
    private LocalDateTime timestamp;
    private ArrayList<comment> comments;
    private ArrayList<profile> likes;

    public Post(String content, profile author) {
        this.id = counter;
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        counter++;
    }

    public ArrayList<comment> getComments() {
        return comments;
    }

    public ArrayList<profile> getLikes() {
        return likes;
    }

    public profile getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getImg() {
        return img;
    }

    public void addComment(profile a , String c) {
        comments.add(new comment(a , c));
        db.update(HelloApplication.getNetworking().getprofiles());
    }
    public void removeComment(comment mycomment) {
        comments.remove(mycomment);
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public void toggleLike(profile user) {
        if (likes.contains(user)){
            likes.remove(user);
        }
        else {
            likes.add(user);
        }
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public boolean checkifliked(profile user){
        if (likes.contains(user)){
            return true;
        }
        return false;
    }

    public void setImg(String img) {
        this.img = img;
        db.update(HelloApplication.getNetworking().getprofiles());

    }


    public int likeCount(){
        return likes.size();
    }

    public int commentCount(){
        return comments.size();
    }

    @Override
    public String toString() {
        return "author= " + author.getUsername() + '|' +
                "content= " + content + '|' +
                "comments= " + comments.size() +'|' +
                "likes= " + likes.size() ;
    }

    public int compareTo(Post other) {
        return this.timestamp.compareTo(other.timestamp);
    }

}
