package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.yarab2.HelloApplication.networking;

public class showpostcontroller implements Initializable {

    @FXML
    private Button submitcomment;
    @FXML
    private Button like;
    @FXML
    private Button back;

    @FXML
    private Label theauthor;
    @FXML
    private Label thecontnt;
    @FXML
    private Label thetimestamp;
    @FXML
    private Label lcount;
    @FXML
    private TextField newcomment;

    @FXML
    private ListView<comment> commentssection;


    private Post currentPost; // To hold the passed profile

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void initData(Post p) {
        this.currentPost = p; // Store the profile
        showpost();
        setupButtonActions();
        showpostcomments(); // Update posts list


    }
    private void showpostcomments(){
        // Set up the ListView
        commentssection.setItems(javafx.collections.FXCollections.observableArrayList(this.currentPost.getComments()));
        commentssection.setCellFactory(param -> new ListCell<comment>() {
            @Override
            protected void updateItem(comment c, boolean empty) {
                super.updateItem(c, empty);
                if (empty || c == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Custom display text for each post
                    setText(
                            "author : " + c.getAuthor().getUsername() +"\n"+
                                    "content : " + c.getContent() +"\n" +
                                    "date : " + c.getTimestamp().toString() +"\n"

                    );  // Adjust this line to display whatever details you want
                }
            }
        });
    }

    void showpost(){
        theauthor.setText(this.currentPost.getAuthor().getUsername());
        thecontnt.setText(this.currentPost.getContent());
        thetimestamp.setText(this.currentPost.getTimestamp().toString());
        lcount.setText(""+this.currentPost.likeCount());
    }

    private void setupButtonActions() {
        back.setOnAction(event -> goback());
        submitcomment.setOnAction(event -> addcomment());
        like.setOnAction(event -> addlike());
    }

    private void addcomment(){
        String content = newcomment.getText();
        if (content != ""){
            this.currentPost.addComment(networking.currentUser,content);
            showpostcomments();

        }
    }

    private void addlike(){
        this.currentPost.toggleLike(networking.currentUser);
        lcount.setText(""+this.currentPost.likeCount());
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
