package com.example.yarab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.yarab2.HelloApplication.networking;

public class chatcontroller implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button submit;
    @FXML
    private TextField messages;



    @FXML
    private ListView<Message> allmessages;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(profile prof) {
        showmessages(prof);
        setupButtonActions(prof);
    }
    private void setupButtonActions(profile prof) {
        submit.setOnAction(event -> addmessage(prof));
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

    private void addmessage(profile prof){
        String content = messages.getText();
        if (content !="") {
            networking.currentUser.getChats(prof).sendMessage(networking.currentUser,content);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml")); // Ensure the path is correct
                Scene scene = new Scene(loader.load());
                chatcontroller controller = loader.getController(); // Retrieve the controller
                controller.initData(prof); // Pass the profile to the controller
                Stage stage = (Stage) back.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("profile!");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void showmessages(profile prof){
        // Set up the ListView
        allmessages.setItems(javafx.collections.FXCollections.observableArrayList(networking.currentUser.getChats(prof).getMessages()));
        allmessages.setCellFactory(param -> new ListCell<Message>() {
            @Override
            protected void updateItem(Message mm, boolean empty) {
                super.updateItem(mm, empty);
                if (empty || mm == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Custom display text for each post
                    setText(
                             mm.getSender().getUsername() +":" + mm.getMessage() + "\n" + mm.getTimestamp()
                    );  // Adjust this line to display whatever details you want
                }
            }
        });
    }

}