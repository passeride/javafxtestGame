package com.passeride.fxtest.helperClasses;

import java.util.ArrayList;

import com.passeride.fxtest.DarkRoom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class House extends TreeItem<String> implements DisplayInformation {

	public String name;
	
	public int sqmeters;
	
	public HouseType type;
	
	public static enum HouseType{
		HOUSE(0), STORAGE(1), GARDEN(2), WORKSHOP(3);
		
		private final int value;
		private HouseType(int value) {
			this.value = value;
		}
		
	    public int getValue() {
	        return value;
	    }
	}
	
	public ArrayList<Person> resident = new ArrayList<>();
	
	public House(String name, HouseType type, int sqmeters) {
		super(name);
		this.name = name;
		this.type = type;
		this.sqmeters = sqmeters;
		
	}

	@Override
	public Node selected() {
		GridPane root = new GridPane();
		
		Label houseLabel = new Label("HOUSE");
		
		Label nameLabel = new Label("Name: ");
		
		// Create Namefield
		TextField nameField = new TextField(name);
		nameField.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				name = nameField.getText();
				setValue(name);
			}
			
		});
		
		Label sqmeterLabel = new Label("SqMeters: " + sqmeters);
		
		Label residentLabel = new Label("Residents:");
		
		
		
		root.add(houseLabel, 0, 0);
		root.add(nameLabel, 0, 1);
		root.add(nameField, 1, 1);
		root.add(sqmeterLabel, 0, 2);
		root.add(residentLabel, 0, 3);
		
		
		for(int i = 0; i < resident.size(); i++) {
			Button btn = new Button(resident.get(i).name);
			final int ii = i;
			btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent event) {
	            	DarkRoom.dr.jump(resident.get(ii));
	            }
	        });
			System.out.println("Loading shit");
			root.add(btn, 0, 4+i);
		}
		
		
		return root;
	}
	
}
