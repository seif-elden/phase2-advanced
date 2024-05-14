package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.yarab2.HelloApplication.networking;


public class viewotherscontroller implements Initializable {
    @FXML
    private Button followuser;
    @FXML
    private Button unfollowuser;
    @FXML
    private Button back;
    @FXML
    private Label username;
    @FXML
    private Label bio;
    @FXML
    private Label followerscount;
    @FXML
    private Label postcount;
    @FXML
    private ListView<Post> myposts;
    @FXML
    private ImageView profileimg;

    private profile currentUser; // To hold the passed profile



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
    }
    private void setupButtonActions() {
        followuser.setOnAction(event -> followetheuser());
        unfollowuser.setOnAction(event -> unfollowetheuser());
        back.setOnAction(event -> goback());
    }

    public void initData(profile prof) {
        this.currentUser = prof; // Store the profile
        showuserdata(); // Update UI with profile data
        showuserposts(); // Update posts list
        if (networking.currentUser.checkfollow(this.currentUser)){
            this.followuser.setVisible(false);
        }else {
            this.unfollowuser.setVisible(false);
        }
    }

    private void followetheuser() {
        networking.currentUser.addFollow(this.currentUser);
        this.followuser.setVisible(false);
        this.unfollowuser.setVisible(true);
    }

    private void unfollowetheuser() {
        networking.currentUser.removeFollow(this.currentUser);
        this.followuser.setVisible(true);
        this.unfollowuser.setVisible(false);
    }

    private void showuserdata(){
        Image image = new Image("file:///C:/Users/DELL/IdeaProjects/yarab2/src/main/resources/com/example/yarab2/download.png"); // or URL for external images
        profileimg.setImage(image);
        username.setText(this.currentUser.getUsername());
        bio.setText(this.currentUser.getBio());
        followerscount.setText(""+this.currentUser.getfollowerscount());
        postcount.setText(""+this.currentUser.getpostscount());
    }
    private void showuserposts(){
        // Set up the ListView
        myposts.setItems(javafx.collections.FXCollections.observableArrayList(this.currentUser.getPostlist()));
        myposts.setCellFactory(param -> new ListCell<Post>() {
            @Override
            protected void updateItem(Post post, boolean empty) {
                super.updateItem(post, empty);
                if (empty || post == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Custom display text for each post
                    setText(
                            "author : " + post.getAuthor().getUsername() +"\n" +
                                    "content : " + post.getContent() +"\n" +
                                    "number of likes : " + post.likeCount() +"\n" +
                                    "number of comments : " + post.commentCount() +"\n"

                    );  // Adjust this line to display whatever details you want
                    setOnMouseClicked(event -> gotopost(post));
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
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("post profile!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goback() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("home!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
