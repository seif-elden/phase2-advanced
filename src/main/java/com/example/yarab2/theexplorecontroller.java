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
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.yarab2.HelloApplication.networking;

public class theexplorecontroller implements Initializable {

    @FXML
    private Button back;
    @FXML
    private ListView<profile> allusers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupButtonActions();
        showusers();
    }
    private void setupButtonActions() {
        back.setOnAction(event -> goback());
    }
    private void showusers(){
        // Set up the ListView
        Collection<profile> allProfiles = networking.getprofiles().values();

        // Filter out the currentUser from the list
        List<profile> filteredProfiles = allProfiles.stream()
                .filter(prof -> prof != networking.currentUser)
                .collect(Collectors.toList());

        allusers.setItems(javafx.collections.FXCollections.observableArrayList(filteredProfiles));
        allusers.setCellFactory(param -> new ListCell<profile>() {
            @Override
            protected void updateItem(profile prof, boolean empty) {
                super.updateItem(prof, empty);
                if (empty || prof == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Custom display text for each user
                    setText(
                            "name: : " + prof.getUsername()+"\n"
                    );  // Adjust this line to display whatever details you want
                    setOnMouseClicked(event -> gotouserptof(prof));
                }
            }
        });
    }

    public void gotouserptof(profile prof){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewothers.fxml")); // Ensure the path is correct
            Scene scene = new Scene(loader.load());
            viewotherscontroller controller = loader.getController(); // Retrieve the controller
            controller.initData(prof); // Pass the profile to the controller
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("view profile!");
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
