package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.yarab2.HelloApplication.networking;

public class signupcontrroller implements Initializable {
    @FXML
    private TextField s_user;

    @FXML
    private TextField s_pass;

    @FXML
    private TextField s_email;

    @FXML
    private TextField s_bio;

    @FXML
    private TextField s_pic;


    @FXML
    private Button s_redirect_login;

    @FXML
    private Button signup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
    }

    private void setupButtonActions() {
        s_redirect_login.setOnAction(event -> redirectTologinScene());
        signup.setOnAction(event -> addcred());
    }

    private void redirectTologinScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) s_redirect_login.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("login!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addcred() {
        // Implementation of your login verification logic
        System.out.println("signup verification logic here");
        // Add the code to verify the username and password
        String username = s_user.getText();
        String password = s_pass.getText();
        String bio = s_bio.getText();
        String email = s_email.getText();
        String pic = s_pic.getText();
        if (!networking.registerUser(username,password,email,bio,pic)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("please try again with valid data");
            alert.showAndWait();
        }else {
            redirectTologinScene();
        }
    }
}
