package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ScheduleItem;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOController{
	private static final String jsonRouteString = "[{\"name\":\"Sopbil Mariehman\",\"day\":\"Måndag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4010,\"areas\":[\"Jomala Norr\"],\"municipalities\":[\"Mariehamn\",\"Jomala\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":11014},{\"name\":\"Sopbil Eckerö\",\"day\":\"Måndag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4011,\"areas\":[\"Hammarland sommar\"],\"municipalities\":[\"Eckerö\",\"Hammarland\"],\"evenWeek\":\"Ojämn/Båda Sommar\",\"vehileNr\":9565},{\"name\":\"Sopbil Hammarland\",\"day\":\"Måndag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4012,\"areas\":[\"Eckerö Sommar\"],\"municipalities\":[\"Hammarland\",\"Eckerö\"],\"evenWeek\":\"Jämn/Båda Sommar\",\"vehileNr\":9565},{\"name\":\"Kartong+Glas\",\"day\":\"Måndag\",\"materials\":[\"Kartong\",\"Glas\"],\"routeNumber\":6011,\"areas\":[\"Jomala Norra\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":1146},{\"name\":\"Sopbil \\\"Lilla Bilen\\\"\",\"day\":\"Måndag\",\"materials\":[\"Brännbar\",\"Plast-ÅPAB\",\"restavf\",\"bravf-q\"],\"routeNumber\":4013,\"areas\":[\"Järsö\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":981},{\"name\":\"Aluminium\",\"day\":\"Måndag\",\"materials\":[\"Alu\"],\"routeNumber\":8010,\"areas\":[\"Järsö\",\"Jomala Öst\"],\"municipalities\":[\"Mariehamn\",\"Lemland\",\"Lumparland\",\"Finström\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":203},{\"name\":\"Aluminium\",\"day\":\"Måndag\",\"materials\":[\"Alu\"],\"routeNumber\":8012,\"areas\":[\"Jomala Norr\",\"Jomala Väst\"],\"municipalities\":[\"Hammarland\",\"Eckerö\",\"Finström\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":203},{\"name\":\"Q-bin\",\"day\":\"Måndag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":511,\"areas\":[\"Järsö\"],\"municipalities\":[\"Hammarland\",\"Eckerö\",\"Mariehamn\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":7105},{\"name\":\"Q-bin\",\"day\":\"Måndag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":512,\"areas\":[\"Järsö\",\"Västernäs\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":7105},{\"name\":\"Glas-Iglo\",\"day\":\"Måndag\",\"materials\":[\"Glas\",\"Metall\"],\"routeNumber\":800,\"areas\":[\"Jomala -Norra -Västra -Östra\"],\"municipalities\":[\"Saltvik\",\"Sund\",\"Mariehamn\",\"Hammarland\",\"Finström\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":201},{\"name\":\"Sopbil Mariehamn\",\"day\":\"Tisdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4020,\"areas\":[\"Jomala -Norr -Väst -Öst\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":11014},{\"name\":\"Sopbil Jomala\",\"day\":\"Tisdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4021,\"areas\":[\"Norra\",\"Västra\"],\"municipalities\":[\"Jomala\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":9565},{\"name\":\"Sopbil Jomala\",\"day\":\"Tisdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4022,\"areas\":[\"Norra\",\"Östra\"],\"municipalities\":[\"Jomala\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":9565},{\"name\":\"Papper+Metall\",\"day\":\"Tisdag\",\"materials\":[\"Blandpapper\",\"Papp-Vitt\",\"Metall\"],\"routeNumber\":7021,\"areas\":[\"JomalaVäst\",\"JomalaNorr(VV)\"],\"municipalities\":[\"Hammarland\",\"Eckerö(ej VV)\",\"Finström(VV)\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":1146},{\"name\":\"Papper+Metall\",\"day\":\"Tisdag\",\"materials\":[\"Blandpapper\",\"Papp-Vitt\",\"Metall+Alu\"],\"routeNumber\":7022,\"areas\":[\"Jomala Östra\",\"+Ängö\"],\"municipalities\":[\"Lemland\",\"Lumparland\",\"Föglö\",\"Hammarland(VV)\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":1146},{\"name\":\"Sopbil Vårdö\",\"day\":\"Tisdag\",\"materials\":[\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\",\"EJ BIO\"],\"routeNumber\":5021,\"areas\":[\".\"],\"municipalities\":[\"Vårdö\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":8061},{\"name\":\"Papper/Metall Vårdö\",\"day\":\"Tisdag\",\"materials\":[\"Blandpapper\",\"papp-vitt\",\"Metall\"],\"routeNumber\":7000,\"areas\":[\"-\"],\"municipalities\":[\"Vårdö\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":132},{\"name\":\"Plast Mariehamn\",\"day\":\"Tisdag\",\"materials\":[\"Blandplast\",\"Hårdplast\"],\"routeNumber\":5022,\"areas\":[\"Järsö\",\"(Kattby 2ggr/V)\"],\"municipalities\":[\"Mariehamn\",\"Hammarland\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":203},{\"name\":\"Q-bin\",\"day\":\"Tisdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":531,\"areas\":[\"Jomala norra\",\"jomala Västra\",\"Östernäs(VV)\"],\"municipalities\":[\"Finström\",\"Mariehamn\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":7105},{\"name\":\"Q-Bin östernäs\",\"day\":\"Tisdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":522,\"areas\":[\"Östernäs\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":7105},{\"name\":\"Sopbil Mariehamn\",\"day\":\"Onsdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4030,\"areas\":[\"JomalaNorr\",\"JomalaÖster\",\"JomalaVäster\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":11014},{\"name\":\"Sopbil Norra Åland\",\"day\":\"Onsdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4032,\"areas\":[\"JomalaNorra\"],\"municipalities\":[\"Finström\",\"Saltvik\",\"Sund\",\"Geta\",\"Vårdö\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":9565},{\"name\":\"Kartong+Glas\",\"day\":\"Onsdag\",\"materials\":[\"Kartong\",\"Glas\"],\"routeNumber\":6031,\"areas\":[\"Ängö\",\"JomalaÖstra\",\"JomalaNorra\"],\"municipalities\":[\"Lemland\",\"Lumparland\",\"Föglö\",\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":132},{\"name\":\"Papper+Metall\",\"day\":\"Onsdag\",\"materials\":[\"Blandpapper\",\"Papp-Vitt\",\"Metall+Alu\"],\"routeNumber\":7031,\"areas\":[\"JomalaNorra\"],\"municipalities\":[\"Finström\",\"Saltvik\",\"Sund\",\"Geta\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":1146},{\"name\":\"Plast+Tetror\",\"day\":\"Onsdag\",\"materials\":[\"Blandplast\",\"Hårdplast\",\"Tetror\"],\"routeNumber\":5032,\"areas\":[\"JomalaNorra (PC alltid ojämnv)\"],\"municipalities\":[\"Finström\",\"Saltvik\",\"Sund\",\"Geta\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":1146},{\"name\":\"Tetror\",\"day\":\"Onsdag\",\"materials\":[\"Tetor\"],\"routeNumber\":640,\"areas\":[\"Järsö\",\"JomalaNorra\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":203},{\"name\":\"Q-bin Jomala Norr Väst + Mariehamn(VV)\",\"day\":\"Onsdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":531,\"areas\":[\"Jomala(Prästgården Kungsö Gottby(VV)))\"],\"municipalities\":[\"MariehamnCentr(VV)\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":7105},{\"name\":\"Q-bin Mhamn Centrum + Jom(VV)\",\"day\":\"Onsdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":532,\"areas\":[\"Jomala(Prästgården Kungsö Gottby(VV))\"],\"municipalities\":[\"Mariehamn(Centr)\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":7105},{\"name\":\"Dokument LL\",\"day\":\"Onsdag\",\"materials\":[\"Dokument/Sekretess\"],\"routeNumber\":900,\"areas\":[\"JomalaVästr\",\"JomalaÖstra\",\"JomalaNorra\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":725},{\"name\":\"Sopbil Mariehamn\",\"day\":\"Torsdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4040,\"areas\":[\"JomalaVästra\",\"JomalaÖstra\",\"JomalaNorra\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":9565},{\"name\":\"Sopbil Lemland / Lumparland\",\"day\":\"Torsdag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4042,\"areas\":[\"Ängö(Ojämn)\"],\"municipalities\":[\"Lemland\",\"Lumparland\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":11014},{\"name\":\"Sopbil \\\"Lilla Bilen\\\"\",\"day\":\"Torsdag\",\"materials\":[\"Brännbar\",\"Plast-Åpab\",\"Restavf\",\"Brännbar-Q\"],\"routeNumber\":4045,\"areas\":[\"-\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":981},{\"name\":\"Papper+Metall Mhamn\",\"day\":\"Torsdag\",\"materials\":[\"Blandpapper\",\"Papp-Vitt\",\"Metall\"],\"routeNumber\":7042,\"areas\":[\"Järsö\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":1146},{\"name\":\"Papper Mhamn\",\"day\":\"Torsdag\",\"materials\":[\"Blandpapper\",\"Papp-vitt\"],\"routeNumber\":7043,\"areas\":[\"JomalaVäst(Pitney Bowes)\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":203},{\"name\":\"Kartong+Glas\",\"day\":\"Torsdag\",\"materials\":[\"Kartong\",\"Glas\"],\"routeNumber\":6041,\"areas\":[\"JomalaNorra\",\"Vårdö(jämn)\",\"sund+geta(ojämn)\"],\"municipalities\":[\"Finström\",\"Saltvik\",\"Sund\",\"Geta\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":132},{\"name\":\"Mjukplast\",\"day\":\"Torsdag\",\"materials\":[\"Mjukplast\"],\"routeNumber\":5041,\"areas\":[\"JomalaNorr\",\"JomalaÖstra\",\"JomalaVästra\"],\"municipalities\":[\"Mariehamn\",\"Finström\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":203},{\"name\":\"Q-Bin JomÖst,sviby,mhamn\",\"day\":\"Torsdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":541,\"areas\":[\"Mariehamn(Dalbo Österleden(VV))\"],\"municipalities\":[\"Jomala(Kalmarnäs)+sviby\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":7105},{\"name\":\"Q-bin Öled dalb lem lump jom\",\"day\":\"Torsdag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":542,\"areas\":[\"Jomala Öst+Väst(VV)\"],\"municipalities\":[\"Mariehamn(Dalbo+Österleden)\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":7105},{\"name\":\"Sopbil Mariehamn\",\"day\":\"Fredag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4050,\"areas\":[\"Jomala(hela)\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":9565},{\"name\":\"Sopbil föglö\",\"day\":\"Fredag\",\"materials\":[\"Bio\",\"BrännBar\",\"FrukG\",\"GrovAvf\",\"restavf\",\"Plast-Åpab\"],\"routeNumber\":4052,\"areas\":[\"-\"],\"municipalities\":[\"Föglö\"],\"evenWeek\":\"Jämn/Båda Sommar\",\"vehileNr\":11014},{\"name\":\"Plast+Tetror\",\"day\":\"Fredag\",\"materials\":[\"Blandplast\",\"Hårdplast\",\"Tetror\"],\"routeNumber\":5051,\"areas\":[\"JomalaVäst\"],\"municipalities\":[\"Eckerö\",\"Hammarland\",\"Finstöm(VV)\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":1146},{\"name\":\"Kartong\",\"day\":\"Fredag\",\"materials\":[\"Kartong\"],\"routeNumber\":6051,\"areas\":[\"JomalaNorra\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Båda Veckor\",\"vehileNr\":203},{\"name\":\"Kartong+Glas +jämnVV\",\"day\":\"Fredag\",\"materials\":[\"Kartong\",\"Glas\"],\"routeNumber\":6052,\"areas\":[\"JomalaVästra\"],\"municipalities\":[\"Eckerö\",\"Hammarland\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":132},{\"name\":\"Plast+Tetror\",\"day\":\"Fredag\",\"materials\":[\"Blandplast\",\"Hårdplast\",\"Tetror\"],\"routeNumber\":5061,\"areas\":[\"JomalaÖstra\"],\"municipalities\":[\"Lemland\",\"Lumparland\",\"Föglö\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":132},{\"name\":\"Q-bin Norra Åland +mhamn(VV)\",\"day\":\"Fredag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":551,\"areas\":[\"Mariehamn(Strandnäs(VV))\"],\"municipalities\":[\"Saltvik\",\"Sund\",\"Geta\"],\"evenWeek\":\"Jämn Vecka\",\"vehileNr\":7105},{\"name\":\"Q-bin strandnäs\",\"day\":\"Fredag\",\"materials\":[\"Fyrf-A\",\"FyrRB\",\"Brännbar-Q\",\"Entr-Q\",\"Rest-Q\",\"Bio-Q\",\"Fyrf-B\"],\"routeNumber\":552,\"areas\":[\"Strandnäs\"],\"municipalities\":[\"Mariehamn\"],\"evenWeek\":\"Ojämn Vecka\",\"vehileNr\":7105}]";


	public static List<ScheduleItem> makeListFromString(){
		ObjectMapper ob = new ObjectMapper();
		List<ScheduleItem> list;
		try{
			list = ob.readValue(jsonRouteString, new TypeReference<List<ScheduleItem>>(){});
			//System.out.println(list.toString());
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static boolean save(List<ScheduleItem> list, Stage primaryStage){
		//System.out.println("In Save, listSize: "+list.size()+"\n==================================");
		ObjectMapper obMap = new ObjectMapper();
		ArrayNode s = obMap.createArrayNode();
		//System.out.println("adding");
		for(ScheduleItem si : list){
			//System.out.println("looop");
			s.addPOJO(si);
		}
		try{
			File jarFile = new File(IOController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			URL url = IOController.class.getProtectionDomain().getCodeSource().getLocation();
			File file = new File("Routes.json");
			//file = makeFileChooser("Save",primaryStage);
			//readFile(file);
			obMap.writeValue(file, s);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private static File makeFileChooser(String temp, Stage stage){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(temp);
		return fileChooser.showOpenDialog(stage);
	}
	
	public static List<ScheduleItem> load(){
		//System.out.println("inload");
		ObjectMapper ob = new ObjectMapper();
		List<ScheduleItem> list;
		try{
			File jarFile = new File(IOController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			list = ob.readValue(readFile("Routes.json"), new TypeReference<List<ScheduleItem>>(){});
			//System.out.println(list.toString());
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private static void readFromFile(File f) throws IOException {
		FileReader fr = new FileReader(f);
		BufferedReader bt = new BufferedReader(fr);
		String s = bt.readLine();
		//System.out.println(s);
	}
	
	static String readFile(String path){
		String s="";
		try{

			byte[] encoded=Files.readAllBytes(Paths.get(path));
			s =  new String(encoded, Charset.forName("utf-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}

	public static String toUTF(String str){
		String s="";
		try{

			byte[] encoded=str.getBytes();
			s =  new String(encoded, Charset.forName("utf-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
}
