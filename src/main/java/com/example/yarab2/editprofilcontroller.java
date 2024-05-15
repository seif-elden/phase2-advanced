package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.yarab2.HelloApplication.networking;

public class editprofilcontroller implements Initializable {

    @FXML
    private TextField s_email;

    @FXML
    private TextField s_bio;

    @FXML
    private TextField s_pic;

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
        this.s_bio.setText(networking.currentUser.getBio());
        this.s_email.setText(networking.currentUser.getEmail());
        this.s_pic.setText(networking.currentUser.getProfilePic());

    }
    private void setupButtonActions() {
        submit.setOnAction(event -> editmethod());
        cancel.setOnAction(event -> cancelmethod());
    }

    private void editmethod(){
        networking.currentUser.setBio(s_bio.getText());
        networking.currentUser.setEmail(s_email.getText());
        networking.currentUser.setProfilePic(s_pic.getText());
        cancelmethod();
    }
    private void cancelmethod(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) submit.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("login!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
