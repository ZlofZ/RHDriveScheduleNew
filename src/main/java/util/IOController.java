package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.ScheduleItem;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOController{
	
	
	public static boolean save(List<ScheduleItem> list){
		System.out.println("In Save, listSize: "+list.size()+"\n==================================");
		ObjectMapper obMap = new ObjectMapper();
		ArrayNode s = obMap.createArrayNode();
		System.out.println("adding");
		for(ScheduleItem si : list){
			System.out.println("looop");
			s.addPOJO(si);
		}
		try{
			File file = new File("Routes.json");
			//readFile(file);
			obMap.writeValue(file, s);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static List<ScheduleItem> load(){
		System.out.println("inload");
		ObjectMapper ob = new ObjectMapper();
		List<ScheduleItem> list = new ArrayList<>();
		ScheduleItem item;
		ObjectNode an;
		System.out.println("loaded file: "+readFile("Routes.json"));
		try{
			list = ob.readValue(readFile("Routes.json"), new TypeReference<List<ScheduleItem>>(){});
			System.out.println(list.toString());
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
		System.out.println(s);
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
}
