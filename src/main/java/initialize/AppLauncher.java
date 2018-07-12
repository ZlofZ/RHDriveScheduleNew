package initialize;

import controller.SearchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AppLauncher extends Application{
	private Stage primaryStage;
	
	public AppLauncher(){
	
	}
	
	public AppLauncher(String[] args){
		launch(args);
	}
	
	private void initLayout(){
		try{
			SearchViewController controller = new SearchViewController(primaryStage);
			FXMLLoader loader=new FXMLLoader();
			//System.out.println("Loader.setlocation");
			System.out.println(getClass().getResource("/fxml/SearchLayout.fxml").getPath().replace("!",""));
			String s = getClass().getResource("/fxml/SearchLayout.fxml").getPath();
			s.replace("Växel","V%c3%a4xel");
			System.out.println(s);
			URL u = new URL(s);
			loader.setLocation(u);
			loader.setController(controller);
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
