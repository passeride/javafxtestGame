package com.passeride.fxtest.helperClasses;


import java.util.ArrayList;

import com.passeride.fxtest.DarkRoom;
import com.sun.glass.events.MouseEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Person extends TreeItem<String> implements DisplayInformation{

	public String name;
	public int age;
	public int health;
	public Activitys activity = Activitys.NOTHING;
	public House resident;
	
	public static enum Activitys{
		GATHER_WOOD(0), LOOK_FOR_SURVIVORS(1), PROCRIATE(2), NOTHING(3);
		
		private final int value;
		private Activitys(int value) {
			this.value = value;
		}
		
	    public int getValue() {
	        return value;
	    }
	}
	
	public Person(String name, int age, int health) {
		super(name);
		this.name = name;
		this.age = age;
		this.health = health;
		findHouse();
	}
	
	
	
	private void findHouse() {
		ArrayList<House> houses = DarkRoom.dr.houses;
		
		House best = houses.get(0);
		
		for(House h : houses) {
			if(h.type == House.HouseType.HOUSE) {
				if(best == null) {
					best = h;
				}else if(best.resident.size() / best.sqmeters > h.resident.size() / h.sqmeters) {
					best = h;
				}
			}
		}
		
		if(best != null) {
			resident = best;
			resident.resident.add(this);
			System.out.println(name + " lives in " + resident.name);
		}else {
			System.out.println("No house found for " + name);
		}
		
		
		
	}

	public static String activityName(Activitys a) {
		switch(a) {
		case GATHER_WOOD:
			return "gathering wood";
		case LOOK_FOR_SURVIVORS:
			return "looking for surviors";
		case PROCRIATE:
			return "You know whats up";
		case NOTHING:
			return "LAAAZY! sad";
		default:
			return "";
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Node selected() {
		GridPane root = new GridPane();
		
		Label personLabel = new Label("Person");
		Label nameLabel = new Label("Name:");
		// Create Namefield
		TextField nameField = new TextField(name);
		nameField.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				name = nameField.getText();
				setValue(name);
			}
			
		});
		
		// Resident
		
		Label residentLabel;
		
		if(resident != null)
			residentLabel = new Label("Resident at " + resident.name);
		else
			residentLabel = new Label("Don't have a hus:(:(");
		
		// Wood Gathering
		Label activityLabel = new Label("Activity: ");
		ChoiceBox activitySelector = new ChoiceBox(FXCollections.observableArrayList(
			    "GATHER_WOOD", "LOOK FOR SURVIORS", "SEXYTIME", "NOTHING")
			);
		activitySelector.getSelectionModel().select(activity.getValue());
		activitySelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				int newActivity = (int) newValue;
				activity = Activitys.values()[newActivity];
				System.out.println(newActivity);
			}
		});
		// Button Kick
		Button kick = new Button("Kick out!");
		Person p = this;
		kick.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	DarkRoom.dr.setDefaultCenter();
                DarkRoom.dr.appendText(name + " walks into the snowy dark!");
                DarkRoom.dr.peoples.remove(p);
                getParent().getChildren().remove(p);
            }
        });
		
		root.add(personLabel, 0, 0, 1, 1);
		root.add(nameLabel, 0, 1, 1, 1);
		root.add(nameField, 1, 1, 1, 1);
		root.add(residentLabel, 0, 2, 1, 1);
		root.add(activityLabel, 0, 3, 1, 1);
		root.add(activitySelector, 1, 3, 2, 2);
		root.add(kick, 0, 10);
		
		return root;
		//return  "PERSON\nName: " + name + "\nAge: " + age + "\nHealth: " + health;
	}


	
	
}
