package oodj_vaccineregistrationsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class RegistrationSystemMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("PeopleView&SearchVaccineDetails.fxml"));
            Scene scene = new Scene(root,1030,780);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Book Vaccination Appointment");
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
