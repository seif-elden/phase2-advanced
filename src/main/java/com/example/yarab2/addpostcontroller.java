package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.yarab2.HelloApplication.networking;


public class addpostcontroller implements Initializable {
    @FXML
    private Button p_submit;
    @FXML
    private Button back;
    @FXML
    private TextArea p_content;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
    }

    private void setupButtonActions() {
        p_submit.setOnAction(event -> submitpost());
        back.setOnAction(event -> goback());

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
    private void submitpost() {

        String content = p_content.getText();
        if (content !="") {
            networking.currentUser.addpost(content);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml")); // Ensure the path is correct
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) p_submit.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("profile!");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
