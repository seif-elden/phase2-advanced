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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static com.example.yarab2.HelloApplication.networking;

public class logincontroller implements Initializable {
    @FXML
    private TextField l_username;

    @FXML
    private TextField l_password;

    @FXML
    private Button login;

    @FXML
    private Button s;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
    }

    private void setupButtonActions() {
        s.setOnAction(event -> redirectToSignupScene());
        login.setOnAction(event -> vcred());
    }

    private void redirectToSignupScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) s.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("signup!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectTohome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) s.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("home!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void vcred() {
        // Implementation of your login verification logic
        System.out.println("Login verification logic here");
        // Add the code to verify the username and password

        String username = l_username.getText();
        String password = l_password.getText();

        networking.currentUser = networking.loginUser(username,password);
        if (networking.currentUser != null){
            redirectTohome();
        }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("wrong username or password");
            alert.showAndWait();
        }

    }
}
