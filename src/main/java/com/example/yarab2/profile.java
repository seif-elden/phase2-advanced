package com.example.yarab2;

import java.io.Serializable;
import java.util.ArrayList;

public class profile implements Serializable {
    private String username;
    private String password;
    private String email;
    private String bio;
    private String profilePic;
    private ArrayList<profile> followlist;
    private ArrayList<Post> Postlist;



    public profile(String username, String password,String email, String bio, String profilePic) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.profilePic = profilePic;
        this.followlist = new ArrayList<>();
        this.Postlist = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public ArrayList<Post> getPostlist() {
        return Postlist;
    }

    public ArrayList<profile> getFollowlist() {
        return followlist;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
        db.update(HelloApplication.getNetworking().getprofiles());
    }


    public void setBio(String bio) {
        this.bio = bio;
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public void setEmail(String email) {
        this.email = email;
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public void addFollow(profile user){
        if (!followlist.contains(user)){
            followlist.add(user);
            db.update(HelloApplication.getNetworking().getprofiles());
        }
    }
    public void removeFollow(profile user){
        if (followlist.contains(user)){
            followlist.remove(user);
            db.update(HelloApplication.getNetworking().getprofiles());
        }
    }
    public boolean checkfollow(profile user){
        if (followlist.contains(user)){
            return true;
        }
        return false;
    }

    public void addpost(String content ){
        Postlist.add(new Post(content,this));
        db.update(HelloApplication.getNetworking().getprofiles());
    }

    public int getfollowerscount() {
        return followlist.size();
    }

    public int getpostscount() {
        return Postlist.size();
    }


    @Override
    public String toString() {
        return  "username='" + username + '|' +
                ", bio='" + bio + '|' +
                ", profilePictureUrl='" + profilePic + '|' +
                ", friendsCount=" + followlist.size() +
                ", postsCount=" + Postlist.size();
    }


}
