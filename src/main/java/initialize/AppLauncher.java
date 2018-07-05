package initialize;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AppLauncher extends Application{
	private Stage primaryStage;
	
	public AppLauncher(){
	
	}
	
	public AppLauncher(String[] args){
		launch(args);
	}
	
	private void initLayout(){
		try{
			FXMLLoader loader=new FXMLLoader();
			System.out.println("Loader.setlocation");
			loader.setLocation(getClass().getResource("/fxml/SearchLayout.fxml"));
			VBox layout=loader.load();
			
			Scene scene=new Scene(layout);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Renhållningens Körschema");
		
		initLayout();
	}
}
