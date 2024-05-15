package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
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

    @FXML
    private Button exploreposts;

    @FXML
    private ListView<Post> allposts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.followedPostlist = new ArrayList<>();
        loadposts();
        setupButtonActions();
        showposts();

    }

    private void showposts(){
        // Set up the ListView
        allposts.setItems(javafx.collections.FXCollections.observableArrayList(followedPostlist));
        allposts.setCellFactory(param -> new ListCell<Post>() {
            @Override
            protected void updateItem(Post post, boolean empty) {
                super.updateItem(post, empty);
                if (empty || post == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setOnMouseClicked(event -> gotopost(post));
                    // Custom display text for each post
                    setText(
                                    "author : " + post.getAuthor().getUsername() +"\n" +
                                    "content : " + post.getContent() +"\n" +
                                    "number of likes : " + post.likeCount() +"\n" +
                                    "number of comments : " + post.commentCount() +"\n"

                    );  // Adjust this line to display whatever details you want
                }
            }
        });
    }
    private void gotopost(Post p){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showpost.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            showpostcontroller controller = loader.getController(); // Retrieve the controller
            controller.initData(p); // Pass the profile to the controller
            Stage stage = (Stage) explore.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("post profile!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadposts(){
        for (profile x : networking.currentUser.getFollowlist()){
            // render post in home page
            followedPostlist.addAll(x.getPostlist());

        }
        if (followedPostlist != null){
            followedPostlist.sort(Collections.reverseOrder());
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
        exploreposts.setOnAction(actionEvent -> goexploreposts());
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
            stage.setTitle("explore users!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goexploreposts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exploreposts.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) explore.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("explore posts!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
