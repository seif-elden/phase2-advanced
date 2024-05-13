package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class profilecontroller implements Initializable {
    @FXML
    private Button addnewpost;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
    }
    private void setupButtonActions() {
        addnewpost.setOnAction(event -> redirectToaddpost());
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
}
