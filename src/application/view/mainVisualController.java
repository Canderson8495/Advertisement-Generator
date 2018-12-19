package application.view;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random; 
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

public class mainVisualController {
	
	
	
	
	//Injected FXML field from mainVisual.fxml
	@FXML private TextField bedNumField;
	@FXML private TextField	bathNumField;
	@FXML private TextField	CeilingHeightField;
	@FXML private TextField locationField;
	@FXML private RadioButton dogParkField;
	@FXML private RadioButton catAllowedField;
	@FXML private RadioButton dogAllowedField;
	@FXML private RadioButton washerDryerField;
	@FXML private RadioButton fitnessCenterField;
	@FXML private RadioButton poolField;
	@FXML private ComboBox counterMatBox;
	@FXML private ComboBox areaBox;
	@FXML private ComboBox applianceMatBox;
	@FXML private ComboBox flooringMatBox;
	
	
	
	//Required ObservableList to initialize ComboBoxs above
	ObservableList<String> counterMatList = FXCollections.observableArrayList("Granite", "Quartz", "Not Listed");
	ObservableList<String> areaList = FXCollections.observableArrayList("Urban", "Suburban", "Rural", "High-end", "Coporate", "Old");
	ObservableList<String> applianceMatList = FXCollections.observableArrayList("Stainless Steel", "Black", "Not Listed");
	ObservableList<String> flooringMatList = FXCollections.observableArrayList("Laminate", "Hard-wood", "Tile", "Carpet", "Not Listed");
	

	@FXML private TextFlow advertisement;
	
	@FXML 
	private void initialize() {
		System.out.println("initialized");
		counterMatBox.setItems(counterMatList);
		areaBox.setItems(areaList);
		applianceMatBox.setItems(applianceMatList);
		flooringMatBox.setItems(flooringMatList); 
	}
	
	
	@FXML
	private void OkBtn() throws IOException {
		
		advertisement.getChildren().clear();
		//For simplicity sakes, we will NOT be parsing an input back to number sake. 
		String bedNum =  bedNumField.getText();
		String bathNum = bathNumField.getText();
		String ceilingHeight = CeilingHeightField.getText();
		String location = locationField.getText();
		boolean dogPark = dogParkField.isSelected();
		boolean catAllowed = catAllowedField.isSelected();
		boolean dogAllowed = dogAllowedField.isSelected();
		boolean washerDryer = washerDryerField.isSelected();
		boolean fitnessCenter = fitnessCenterField.isSelected();
		boolean pool = poolField.isSelected();
		String counterMat = counterMatBox.getValue().toString();
		String area = areaBox.getValue().toString();
		String applianceMat = applianceMatBox.getValue().toString();
		String flooringMat = flooringMatBox.getValue().toString();
		
		Text advertisementText = new Text(generateAdvertisement(bedNum, bathNum,  ceilingHeight,  location,  dogPark, dogAllowed, catAllowed, washerDryer,
				fitnessCenter, pool, counterMat, area, applianceMat, flooringMat   ));
		//This is where we update the right pane 
		System.out.println(advertisementText);
		advertisementText.setFont(Font.font ("Verdana", 18));
		advertisement.getChildren().add(advertisementText);
		
	}
	
	public String generateAdvertisement(String bedNum, String bathNum, String ceilingHeight, String location, boolean dogPark, boolean dogAllowed, boolean catAllowed, boolean washerDryer, boolean fitnessCenter, boolean pool, String counterMat, String area, String applianceMat, String flooringMat) {
		System.out.println("Entered generate");
		String buffer = "";
		//To add variety to the generated advertisements
		buffer+= generateEntry(bedNum, bathNum, location);
		if(Integer.parseInt(ceilingHeight) >8) {
			buffer += generateHeight(ceilingHeight);
		}
		if(!counterMat.equals("Not Listed")) {
			buffer += generateAmenity(counterMat, flooringMat, washerDryer);
		}
		buffer += "\n";
		if(fitnessCenter) {
			buffer += generateFitnessAmenity(fitnessCenter, pool); 
		}
		if(catAllowed || dogAllowed) {
			buffer += generatePets(catAllowed, dogAllowed, dogPark);
		}
		return buffer;
	}
	/*
	 * Planning on throwing the generated sentences into an array, 
	 * so that I can pull rand nums to randomly select different statements to allow for variety.
	 * Maybe I'll even throw it into a static class just to be neat called adGen, but for now I'll just create switch statements that allow for random numbers to be generated.
	*/
	
	Random rand = new Random(); 
	

	private String generatePets(boolean catAllowed, boolean dogAllowed, boolean dogPark) {
		if(dogAllowed) {
			if(catAllowed) {
				if(dogPark) {
					switch(rand.nextInt(1)){
						case 0: {
							return "Be sure to bring your furry friend along too, not only is this apartment pet friendly, but it also has an animal park within walking distance! ";
						}
					}
					
				}
				switch(rand.nextInt(1)) {
					case 0:{
						return "Be sure to bring your furry friend along too, because this apartment is officially pet friendly. ";
					}
				}
				
			}
			if(dogPark) {
				return "Be sure to bring your furry friend along too, not only is this apartmenet dog friendly, but it also has an animal park within walking distance! ";
			}
			return "Be sure to bring your furry friend along too, because this apartment is officially dog friendly. ";
		}else if(catAllowed) {
			if(dogPark) {
				return "Be sure to bring your furry friend along too, not only is this apartment cat friendly, but it also has an animal park within walking distance! ";
			}
		}
		return "Be sure to bring your furry friend along too, because this apartment is officially cat friendly .";
	}

	private String generateAmenity(String counterMat, String flooringMat, boolean washerDryer) {
		if(!counterMat.equals("Not Listed")) {
			if(washerDryer) {
				return ", and let's not forget about the " + counterMat + " counters, " + flooringMat + "floors, and the included washer and dryer, that will make sure you are always comfortable." ;
			}
			return ", and let's not forget about the " + counterMat + " counters, " + flooringMat + "floors, that will make sure you are always comfortable.";
		}
		if(washerDryer) {
			return ", and let's not forget about the " + counterMat + " counters, and the included washer and dryer, that will make sure you are always comfortable.";
		}
		return ", and let's not forget about the " + counterMat + " counters, that will make sure you are always comfortable.";
	}

	private String generateFitnessAmenity(boolean fitnessCenter, boolean pool) {
		if(pool) {
			return " Not to mention the money you'll save on a gym membership. This state-of-the-art fitness center is available to all residents to not only keep you physically fit, but also fiscally fit as well. It even comes with a pool! ";
		}
		return "No more paying a gym membership to stay fit! This state-of-the-art fitness center is available to all residents to not only keep you physically fit, but also fiscally fit as well! ";
	}

	private String generateHeight(String ceilingHeight) {
		return "This apartment boasts " + ceilingHeight + " foot raised ceilings";
	}

	public String generateEntry(String bedNum, String bathNum, String location) {
		return "Are you interested in beautiful and stylish " + bedNum + " bed / " + bathNum + " bath apartment located in " + location + ". Well we have got a place for you."; 
	}
	
}

