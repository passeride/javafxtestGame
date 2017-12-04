package com.passeride.fxtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.passeride.fxtest.helperClasses.DisplayInformation;
import com.passeride.fxtest.helperClasses.House;
import com.passeride.fxtest.helperClasses.Person;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DarkRoom extends Application {

	
	TreeItem<String> rootItem;
	Label wood_label;
	Label people_label;
	Label temprature_label;
	Label time_label;
	
	Calendar time;
	
	int wood = 50;
	int people = 2;
	float temprature = 0.3f;
	
	BorderPane root;
	
	TextArea txt;
	
	public ArrayList<Person> peoples = new ArrayList<>();
	public ArrayList<House> houses = new ArrayList<>();
	
	public static DarkRoom dr;
	
	Random r;
	
	public ArrayList<String> male_names = new ArrayList<>();
	private static final String MALE_NAMES_FILE = "./names/male_name.txt";
	public ArrayList<String> female_names = new ArrayList<>();
	private static final String FEMALE_NAMES_FILE = "./names/female_name.txt";
	
	private Node rootIcon;
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		r = new Random();
		
		male_names = readNames(MALE_NAMES_FILE);
		female_names = readNames(FEMALE_NAMES_FILE);
		
		dr = this;
		// Setting icon
		rootIcon = new ImageView(new Image(getClass().getResourceAsStream("images/person.png")));
		
		// Setting date
		time = Calendar.getInstance();
		
		// Setting root
        root = new BorderPane();
        
        root.setTop(setUpTopView());
        root.setBottom(setUpBottomView());
        root.setLeft(setUpTreeView());
        root.setCenter(setUpTextArea());
        
        updateLabels();

        // Settin up scene
        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	
	private Person newPerson() {
		String name;
		if(r.nextFloat() >= 0.5f) {
			name = female_names.get(r.nextInt(female_names.size()));
		}else {
			name = male_names.get(r.nextInt(male_names.size()));
		}
		int age = r.nextInt(30) + 20;
		
		Person p = new Person(name, age, 100);
		peoples.add(p);
		
		rootItem.getChildren().get(0).getChildren().add(p);
		
		return p;
	}
	
	public void setDefaultCenter() {
		setCenterNode(txt);
	}
	
	private GridPane setUpBottomView() {
        Button btn = new Button();
        btn.setText("Next Day!");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Next Day");
                nextDay();
            }
        });
        
        GridPane bottom = new GridPane();
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.add(btn, 0, 0,  12, 12);
        
        return bottom;
	}
	
	public void removePerson(Person p) {
		peoples.remove(p);
		
	}
	
	private GridPane setUpTopView() {
		GridPane top = new GridPane();

		// Lable
		Label l = new Label("Investment:");
		
		// Top text
		wood_label = new Label("Wood: " + wood );
		people_label = new Label("People: " + people);
		temprature_label = new Label("Temprature: " + temprature + " c");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		time_label = new Label(format1.format(time.getTime()));





        top.setAlignment(Pos.TOP_CENTER);
        //top.setPadding(new Insets(10, 10, 10, 10));
        top.setHgap(10.0f);
        
        //root.add(btn, 1, 1);
        top.add(wood_label, 0, 0);
        top.add(people_label, 2, 0);
        top.add(temprature_label, 4, 0);
        top.add(time_label, 6, 0);
        //top.add(btn_1, 1, 5, 1, 1);
        
        return top;
	}
	
	private TreeView setUpTreeView() {
        // Tree view
		rootItem = new TreeItem<> ("Camp");
        
        rootItem.setExpanded(true);
        
        TreeItem<String> people = new TreeItem<>("People");
        
        TreeItem<String> houses = new TreeItem<>("Houses");
        
        rootItem.getChildren().add(people);
        rootItem.getChildren().add(houses);
        
        // Houses
        for (int i = 0; i < 1; i++) {
        	House h = new House("House " + i, House.HouseType.HOUSE, r.nextInt(30) + 20);
 
        	this.houses.add(h);           
        	houses.getChildren().add(h);
        }
        
        // People
        for (int i = 0; i < 6; i++) {
 
        	
            //TreeItem<String> item = new TreeItem<> ("Message" + i);            
        	newPerson();
        }
        

        
        

        TreeView<String> tree = new TreeView<> (rootItem);
        
   		tree.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				TreeItem<String> ti = tree.getSelectionModel().getSelectedItem();
				if(ti instanceof DisplayInformation) {
					//setText(((DisplayInformation)ti).selected());
					setCenterNode(((DisplayInformation)ti).selected());
				}
			}
			
		});
   		return tree;
	}
	
	private ArrayList<String> readNames(String FILE_NAME) {
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<String> ret = new ArrayList<>();
		
		
		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILE_NAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				ret.add(sCurrentLine.split(" ")[0]);
				System.out.println(sCurrentLine.split(" ")[0]);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return ret;
	}
	
	private void setCenterNode(Node n) {
		root.setCenter(n);
	}
	
	public void jump(Person p) {
		setCenterNode(p.selected());
	}
	
	public void jump(House h) {
		setCenterNode(h.selected());
	}
	
	private TextArea setUpTextArea() {
		txt = new TextArea();
		txt.setEditable(false);
		return txt;
	}
	
	public void setText(String s) {
		txt.setText(s);
	}
	
	public void appendText(String s) {
		txt.appendText("\n" + s);
	}
	
	private void clearText() {
		txt.setText("");
	}
	
	/// This updates the top labels
	
	public void updateLabels() {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
        wood_label.setText("Wood: " + wood);
        people_label.setText("People: " + peoples.size());
        temprature_label.setText("Temprature: " + temprature + " c");
        time_label.setText(format1.format(time.getTime()));
        
        
		
	}
	
	/// This will trigger the next day
	
	public void nextDay() {
		// Sett default Centernode
		setDefaultCenter();
		clearText();
		
		// Increase date
		time.add(Calendar.DAY_OF_YEAR, 1);
		wood -= houses.size();
		appendText(houses.size() + " wood has been used to keep the houses warm!");
		
		for(int i = 0; i < peoples.size(); i++) {
			Person p = peoples.get(i);
			if(p.activity == Person.Activitys.GATHER_WOOD) {
				appendText(p.name + " found some wood");
				wood ++;
			}else if(p.activity == Person.Activitys.LOOK_FOR_SURVIVORS) {
				if(r.nextFloat() >= 0.2f) {
					Person p1 = newPerson();
					appendText(p.name + " found " + p1.name + " Wandering in the woods \n " + p1.name + " has joined the camp");
				}
			}
		}
		
		System.out.println("Wood" + wood);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("date:" + format1.format(time.getTime()));
		
		
		updateLabels();
		appendText("Today is " + getWeekday(time));
	}
	
	private String getWeekday(Calendar c) {
		switch(c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			return "monday";
		case Calendar.TUESDAY:
			return "tuesday";
		case Calendar.WEDNESDAY:
			return "wednesday";
		case Calendar.THURSDAY:
			return "thursday";
		case Calendar.FRIDAY:
			return "friday";
		case Calendar.SATURDAY:
			return "saturday";
		case Calendar.SUNDAY:
			return "sunday";
		default:
			return "";
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
