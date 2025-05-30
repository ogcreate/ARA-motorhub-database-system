package com.ogcreate.app.controllers.auth;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterFirstController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button nextButton;

    @FXML
    private Button signInButton;

    @FXML
    private PasswordField userPasswordField;

    @FXML
    void handleNextButtonClick(MouseEvent event) {
        System.out.println("Next Clicked");

        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/auth/RegisterSecond.fxml"));
        Parent newRoot = loader.load();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        Scene newScene = new Scene(newRoot);
        currentStage.setScene(newScene);
        currentStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    void handleSignInButtonClick(MouseEvent event) {
        System.out.println("Sign in go back");

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/auth/LoginController.fxml"));
            Parent newRoot = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(newRoot);
            currentStage.setScene(newScene);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
