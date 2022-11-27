package se.lu.ics;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application { 
	 
    @Override 
    public void start(Stage primaryStage) { 
      try { 
AnchorPane root =    
 (AnchorPane)FXMLLoader.load(getClass().getResource("/resources/fxml/HomePage.fxml")); 
Scene scene = new Scene(root); 
  
   
primaryStage.setScene(scene); 
  
primaryStage.setTitle("Home-Page"); 
primaryStage.show(); 
       } catch(Exception e) { 
e.printStackTrace(); 
       } 
     } 

     public static void main(String[] args) { 
launch(args); 
     } 
   } 