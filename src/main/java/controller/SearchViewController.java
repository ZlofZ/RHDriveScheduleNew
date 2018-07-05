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
	
	@FXML
	private void initialize(){
		listOfRoutes=new ArrayList<>();
		populateDayBox();
		populateWeekBox();
		
	}
	
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
	private void routeListRefresh(){
		System.out.println("inListRefresh "+listOfRoutes.toString());
		listClear();
		if(routeSearchBox.getText().isEmpty()){
			listClear();
			for(int i=0; i<listOfRoutes.size(); i++){
				listSwitch(i);
			}
		}else
			for(int i = 0; i < listOfRoutes.size(); i++){
				if(msgSplitAndSearch(i)){
					listSwitch(i);
				}
			}
	}
	
	private void listClear(){
		routeListMonday.getItems().clear();
		routeListTuesday.getItems().clear();
		routeListWednesday.getItems().clear();
		routeListThursday.getItems().clear();
		routeListFriday.getItems().clear();
	}
	private void listSwitch(int i){
		switch(listOfRoutes.get(i).getDay()){
			case "Måndag":routeListMonday.getItems().add(listOfRoutes.get(i));
				break;
			case "Tisdag":routeListTuesday.getItems().add(listOfRoutes.get(i));
				break;
			case "Onsdag":routeListWednesday.getItems().add(listOfRoutes.get(i));
				break;
			case "Torsdag":routeListThursday.getItems().add(listOfRoutes.get(i));
				break;
			case "Fredag":routeListFriday.getItems().add(listOfRoutes.get(i));
				break;
			default:
				routeListMonday.getItems().add(listOfRoutes.get(i));
				break;
		}
	}
	@FXML
	private void menubarPaneFadein(MouseEvent mouseEvent){
		((Pane) mouseEvent.getTarget()).setStyle("-fx-background-color:darkgrey");
	}
	
	@FXML
	private void menubarPaneFadeout(MouseEvent mouseEvent){
		((Pane) mouseEvent.getTarget()).setStyle("-fx-background-color:grey");
	}
	
	@FXML
	private void updateRouteList(KeyEvent k){
		routeListRefresh();
	}
	
	private boolean msgSplitAndSearch(int i) {
		String[] words = routeSearchBox.getText().split(" ");
		int matches = 0;
		for(String s: words){
			if(listOfRoutes.get(i).toString().toLowerCase().contains(s.toLowerCase()))
				matches++;
		}
		
		
		
		return (words.length==matches);
	}
	
	private Alert makeAlert(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Lägg till tur");
		alert.setHeaderText("Konfirmera val");
		alert.show();
		return alert;
	}
	
	@FXML
	private void menuBarPaneMPress(MouseEvent mouseEvent){
		((Label)((Pane) mouseEvent.getTarget()).getChildren().get(0)).textFillProperty().setValue(Color.BLUE);
	}
	
	@FXML
	private void menuBarPaneMRelease(MouseEvent mouseEvent){
		((Label)((Pane) mouseEvent.getTarget()).getChildren().get(0)).textFillProperty().setValue(Color.WHITE);
	}
	
	@FXML
	private void addRoute(MouseEvent mouseEvent){
		addRouteHBox.setVisible(true);
	}
	
	@FXML
	private void editRoute(MouseEvent mouseEvent){
		ArrayList<ScheduleItem> rmList = new ArrayList<>();
		listOfRoutes.removeAll(routeListMonday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListTuesday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListWednesday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListThursday.getSelectionModel().getSelectedItems());
		listOfRoutes.removeAll(routeListFriday.getSelectionModel().getSelectedItems());
		routeListRefresh();
	}
	
	@FXML
	private void addeditButtonClicked(ActionEvent actionEvent){
		//Alert a = makeAlert();
		ScheduleItem si = new ScheduleItem(routeNameInput.getText(), dayBox.getValue().toString(), fractionsInput.getText().split(","),
		                                   Integer.parseInt(routeNrInput.getText()), inputAreas.getText().split(","), inputMunicipality.getText().split(","), weekBox.getValue().toString(), Integer.parseInt(vehicleNrInput.getText()));
		System.out.println(si.toString()+(si==null));
		listOfRoutes.add(si);
		routeListRefresh();
		routeNameInput.clear();
		fractionsInput.clear();
		inputAreas.clear();
		inputMunicipality.clear();
		vehicleNrInput.clear();
		routeNrInput.clear();
		addRouteHBox.setVisible(false);
		System.out.println("things:\n"+si.getMaterials().length+"\n"+si.getAreas()+"\n"+si.getMunicipalities()+"\n \n"+si.getMaterials().toString()+"\n"+si.getAreas().toString()+"\n"+si.getMunicipalities().toString());
		for(int i=0; i<si.getAreas().length; i++){
			System.out.println(si.getAreas()[i]);
		}
	}
	
	@FXML
	private void saveToFile(MouseEvent mouseEvent){
		IOController.save(listOfRoutes);
	}
	
	@FXML
	private void loadFromFile(MouseEvent mouseEvent){
		//listOfRoutes = (ArrayList<ScheduleItem>) IOController.loadFromFile();
		listOfRoutes.clear();
		listOfRoutes = (ArrayList<ScheduleItem>) IOController.load();
		routeListRefresh();
	}
}
