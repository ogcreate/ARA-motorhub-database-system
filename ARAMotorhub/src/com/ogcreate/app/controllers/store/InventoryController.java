package com.ogcreate.app.controllers.store;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ogcreate.app.SettingsWindowHelper;
import com.ogcreate.app.database.DatabaseConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class InventoryController {

    @FXML
    private ComboBox categoryComboBox;

    @FXML
    public void initialize() {
        setupCategoryComboBox();
    }

    private void setupCategoryComboBox() {
        String sql = "SELECT name FROM category ORDER BY name ASC";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categoryComboBox.getItems().add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

     
        categoryComboBox.setOnAction(event -> {
            String selectedCategory = (String) categoryComboBox.getValue();
            if (selectedCategory != null) {
                openCategoryView(selectedCategory);
            }
        });
    }

    private void openCategoryView(String categoryName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/store/Categories.fxml"));
            Parent root = loader.load();

            CategoriesController controller = loader.getController();
            controller.setSelectedCategory(categoryName); // This must exist in CategoriesController

            Stage stage = (Stage) categoryComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Dashboard.fxml", event);
    }

    @FXML
    void handleHomeButton(ActionEvent event) {
        loadScene("/resources/fxml/store/Profile.fxml", event); // or Home.fxml if separate
    }

    @FXML
    void handleLogOutButton(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsWindowHelper.logout(currentStage);
    }

    @FXML
    void handleOpenSettings(ActionEvent event) {
        SettingsWindowHelper.openSettings((Node) event.getSource());
    }

    @FXML
    void handleProfileClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Profile.fxml", event);
    }

    @FXML
    void handleProductsClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Products.fxml", event);
    }

    @FXML
    void handleShopsClick(ActionEvent event) {
        loadScene("/resources/fxml/store/Shops.fxml", event);
    }

    // ✅ Shared scene-loading helper
    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newRoot = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(newRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
