package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.ArrayList;


import static com.example.yarab2.HelloApplication.networking;


public class homecontroller implements Initializable {
    private ArrayList<Post> followedPostlist;

    @FXML
    private Button logout;

    @FXML
    private Button profile;

    @FXML
    private Button explore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.followedPostlist = new ArrayList<>();
        loadposts();
        setupButtonActions();

    }
    private void loadposts(){
        for (profile x : networking.currentUser.getFollowlist()){
            // render post in home page
            followedPostlist.addAll(x.getPostlist());

        }
        if (followedPostlist != null){
            Collections.sort(followedPostlist);
            System.out.println(followedPostlist);
        }
        else {
            System.out.println("no posts available");
        }
    }

    private void setupButtonActions() {
        logout.setOnAction(event -> logoutfunction());
        profile.setOnAction(event -> myprofile());
        explore.setOnAction(event -> exploreredirect());
    }
    private void myprofile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) profile.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("profilepage!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logoutfunction() {
        try {
            networking.currentUser = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("login!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exploreredirect() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("theexplore.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) explore.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("explore!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
