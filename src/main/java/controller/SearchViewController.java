package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.ScheduleItem;
import util.IOController;

import java.util.ArrayList;

public class SearchViewController{
	@FXML
	private TextField routeSearchBox;
	@FXML
	private ListView routeListMonday;
	@FXML
	private Pane searchPagePane;
	@FXML
	private Pane MapPagePane;
	@FXML
	private Pane ListPagePane;
	@FXML
	private Pane addRouteButton;
	@FXML
	private Pane editRouteButton;
	@FXML
	private ChoiceBox dayBox;
	@FXML
	private TextField routeNrInput;
	@FXML
	private TextField vehicleNrInput;
	@FXML
	private ChoiceBox weekBox;
	@FXML
	private TextField fractionsInput;
	@FXML
	private TextField inputAreas;
	@FXML
	private Button confirmEditButton;
	@FXML
	private HBox addRouteHBox;
	@FXML
	private TextField routeNameInput;
	@FXML
	private TextField inputMunicipality;
	@FXML
	private ListView routeListTuesday;
	@FXML
	private ListView routeListWednesday;
	@FXML
	private ListView routeListThursday;
	@FXML
	private ListView routeListFriday;
	
	private ArrayList<ScheduleItem> listOfRoutes;
	
	//FXML Methods.........................................
	
	@FXML
	private void initialize(){
		listOfRoutes=new ArrayList<>();
		populateDayBox();
		populateWeekBox();
		
	}
	
	//Top bar FXML Methods
	
	@FXML
	private void menubarPaneFadein(MouseEvent mouseEvent){
		((Pane) mouseEvent.getTarget()).setStyle("-fx-background-color:darkgrey");
	}
	
	@FXML
	private void menubarPaneFadeout(MouseEvent mouseEvent){
		((Pane) mouseEvent.getTarget()).setStyle("-fx-background-color:grey");
	}
	
	@FXML
	private void menuBarPaneMPress(MouseEvent mouseEvent){
		((Label)((Pane) mouseEvent.getTarget()).getChildren().get(0)).textFillProperty().setValue(Color.BLUE);
	}
	
	@FXML
	private void menuBarPaneMRelease(MouseEvent mouseEvent){
		((Label)((Pane) mouseEvent.getTarget()).getChildren().get(0)).textFillProperty().setValue(Color.WHITE);
	}
	
	//Searchbar Activation method
	
	@FXML
	private void updateRouteList(KeyEvent k){
		refreshDisplayedRoutes();
	}
	
	//Add and Remove Button Methods
	
	@FXML
	private void addRoute(MouseEvent mouseEvent){
		addRouteHBox.setVisible(true);
	}
	
	@FXML
	private void removeRoute(MouseEvent mouseEvent){
		ArrayList<ScheduleItem> rmList = new ArrayList<>();
		listOfRoutes.removeAll(routeListMonday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListTuesday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListWednesday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListThursday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListFriday.getSelectionModel().getSelectedItems());
		refreshDisplayedRoutes();
	}
	
	//Add Route OK button
	
	@FXML
	private void addeditButtonClicked(ActionEvent actionEvent){
		//Alert a = makeAlert();
		ScheduleItem si = new ScheduleItem(routeNameInput.getText(), dayBox.getValue().toString(), fractionsInput.getText().split(","),
		                                   Integer.parseInt(routeNrInput.getText()), inputAreas.getText().split(","), inputMunicipality.getText().split(","), weekBox.getValue().toString(), Integer.parseInt(vehicleNrInput.getText()));
		System.out.println("is the item null? "+(si==null));
		listOfRoutes.add(si);
		refreshDisplayedRoutes();
		routeNameInput.clear();
		fractionsInput.clear();
		inputAreas.clear();
		inputMunicipality.clear();
		vehicleNrInput.clear();
		routeNrInput.clear();
		addRouteHBox.setVisible(false);
		//System.out.println("things:\n"+si.getMaterials().length+"\n"+si.getAreas()+"\n"+si.getMunicipalities()+"\n \n"+si.getMaterials().toString()+"\n"+si.getAreas().toString()+"\n"+si.getMunicipalities().toString());
		for(int i=0; i<si.getAreas().length; i++){
			System.out.println(si.getAreas()[i]);
		}
	}
	
	//Save And Load
	
	@FXML
	private void saveToFile(MouseEvent mouseEvent){
		IOController.save(listOfRoutes);
	}
	
	@FXML
	private void loadFromFile(MouseEvent mouseEvent){
		//listOfRoutes = (ArrayList<ScheduleItem>) IOController.loadFromFile();
		listOfRoutes.clear();
		listOfRoutes = (ArrayList<ScheduleItem>) IOController.load();
		refreshDisplayedRoutes();
	}
	
	//Helper Methods...............................................
	
	private void populateWeekBox(){
		weekBox.getItems().addAll("Jämn Vecka","Ojämn Vecka","Båda Veckor","Jämn/Båda Sommar","Ojämn/Båda Sommar");
	}
	
	private void populateDayBox(){
		dayBox.getItems().add("Måndag");
		dayBox.getItems().add("Tisdag");
		dayBox.getItems().add("Onsdag");
		dayBox.getItems().add("Torsdag");
		dayBox.getItems().add("Fredag");
	}
	
	private void refreshDisplayedRoutes(){
		System.out.println("inListRefresh");
		clearListViews();
		if(routeSearchBox.getText().isEmpty())
			addAllRoutesToDisplayElements();
		else
			for(ScheduleItem item:listOfRoutes)
				for(String s : routeSearchBox.getText().split(" "))
					if(item.toString().matches(s))
						addRouteToDisplayElement(item);
	}
	
	private void clearListViews(){
		routeListMonday.getItems().clear();
		routeListTuesday.getItems().clear();
		routeListWednesday.getItems().clear();
		routeListThursday.getItems().clear();
		routeListFriday.getItems().clear();
	}
	
	private void printListOfRoutes(){
		System.out.println("PrintList");
		for (ScheduleItem s:listOfRoutes) {
			System.out.println(s);
		}
	}
	
	private void addAllRoutesToDisplayElements(){
		clearListViews();
		for(ScheduleItem item : listOfRoutes)
			addRouteToDisplayElement(item);
	}
	
	private void addRouteToDisplayElement(ScheduleItem item){
		printListOfRoutes();
		switch(item.getDay()){
			case "Måndag":routeListMonday.getItems().add(item);
				break;
			case "Tisdag":routeListTuesday.getItems().add(item);
				break;
			case "Onsdag":routeListWednesday.getItems().add(item);
				break;
			case "Torsdag":routeListThursday.getItems().add(item);
				break;
			case "Fredag":routeListFriday.getItems().add(item);
				break;
			default:
				routeListMonday.getItems().add(item);
				break;
		}
	}
}
