package com.passeride.fxtest;


import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindo extends Application {

	public int randomTall;
	public int numberOfGuesses = 0;
	
	public void start(Stage primaryStage) throws Exception {
		
		setNumber();
		// TODO Auto-generated method stub
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();*/
	}
	
	public void setNumber() {
		Random r = new Random();
		randomTall = r.ints(0, 100).findFirst().getAsInt();
		numberOfGuesses = 0;
		
		//System.out.println("Randomtall is " + randomTall);
		
		askForNumber();
	}
	
	public void askForNumber() {
        TextInputDialog dialog = new TextInputDialog("Number HERE");
        dialog.setTitle("Stupidtitle");
        dialog.setHeaderText("Stupidgame");
        dialog.setContentText("3n73r numb3r:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        	int answer = Integer.parseInt(result.get());
        	numberOfGuesses++;
        	if(answer == randomTall) {
        		correctAnswer();
        	}else if(answer > randomTall) {
        		toHigh();
        	}else if(answer < randomTall) {
        		toLow();
        	}
            System.out.println("Your numb: " + result.get());
        }
	}
	
	public void toHigh() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("You dumb");
        alert.setHeaderText("Stupid u ar");
        alert.setContentText("ToHIGH BRO!");

        alert.showAndWait();
        
        askForNumber();
	}
	
	public void toLow() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("You dumb");
        alert.setHeaderText("Stupid u ar");
        alert.setContentText("To LOW BRO!");

        alert.showAndWait();
        
        askForNumber();
	}
	
	public void correctAnswer() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("STUPID U R");
		alert.setHeaderText("IQ <3");
		alert.setContentText("U mad it only " + numberOfGuesses);

		ButtonType buttonTypeOne = new ButtonType("Fuck this!");
		ButtonType buttonTypeTwo = new ButtonType("AGAIN AGAIN!");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		    // ... user chose "One"
		} else if (result.get() == buttonTypeTwo) {
		    // ... user chose "Two"
			System.out.println("Stupid cunt!");
			setNumber();
		}
		
	}
	
	 public static void main(String[] args) {
	        launch(args);
	 }

}
