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


public class profilecontroller implements Initializable {
    @FXML
    private Button addnewpost;
    @FXML
    private Button back;

    @FXML
    private Button  editprof;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
        showuserdata();
        showuserposts();
    }
    private void setupButtonActions() {
        addnewpost.setOnAction(event -> redirectToaddpost());
        back.setOnAction(event -> goback());
        editprof.setOnAction(actionEvent -> goedit());
    }
    private void goedit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editprofile.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) addnewpost.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("edit profile!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void viewfollwinfo(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("followinglist.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            followinglistcontroller controller = loader.getController(); // Retrieve the controller
            controller.initData(networking.currentUser); // Pass the profile to the controller
            Stage stage = (Stage) addnewpost.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("your followin!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectToaddpost() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addpost.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) addnewpost.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("add post!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showuserdata(){
        Image image = new Image("file:///C:/Users/DELL/IdeaProjects/yarab2/src/main/resources/com/example/yarab2/download.png"); // or URL for external images
        profileimg.setImage(image);
        username.setText(networking.currentUser.getUsername());
        bio.setText(networking.currentUser.getBio());
        followerscount.setText(""+networking.currentUser.getfollowerscount());
        postcount.setText(""+networking.currentUser.getpostscount());
    }
    private void showuserposts(){
        // Set up the ListView
        myposts.setItems(javafx.collections.FXCollections.observableArrayList(networking.currentUser.getPostlist()));
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
            stage.setTitle("view post!");
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
