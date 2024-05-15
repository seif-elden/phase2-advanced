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
import java.util.*;


import static com.example.yarab2.HelloApplication.networking;

public class explorepostscontroller implements Initializable {
    private ArrayList<Post> Postlist;


    @FXML
    private Button back;
    @FXML
    private ListView<Post> allposts;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Postlist = new ArrayList<>();

        setupButtonActions();
        loadposts();
        showposts();
    }


    private void setupButtonActions() {
        back.setOnAction(event -> goback());
    }
    private void showposts(){
        // Set up the ListView
        allposts.setItems(javafx.collections.FXCollections.observableArrayList(Postlist));
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
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("post profile!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadposts(){
        for (profile x : networking.getprofiles().values()){
            // render post in home page
            Postlist.addAll(x.getPostlist());

        }
        if (Postlist != null){
            Postlist.sort(Collections.reverseOrder());
            System.out.println(Postlist);
        }
        else {
            System.out.println("no posts available");
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


