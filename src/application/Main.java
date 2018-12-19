package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	//A database will be loaded whenever the program is loaded.
	//The main window will show our database.
	private static Stage primaryStage = new Stage();
	//This is the pane that occupies the primaryStage
	public static BorderPane mainVisual = new BorderPane();
	//this holds the table of all of our entries
	//THis window is used for the windows with the add and shit.

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("To Do List");
		showMainVisual();
		File file = new File("resources\\icon.png");
		Image image = new Image(file.toURI().toString());
		ImageView img = new ImageView(image);
		primaryStage.getIcons().add(image);
		
	}
	
	//Loads Menu and tab Panes
	private void showMainVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/mainVisual.fxml"));
		mainVisual = loader.load();
		Scene scene = new Scene(mainVisual);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
