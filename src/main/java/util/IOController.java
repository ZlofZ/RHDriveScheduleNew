package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.ScheduleItem;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOController{
	
	
	public static boolean save(List<ScheduleItem> list){
		ObjectMapper obMap = new ObjectMapper();
		ArrayNode s = obMap.createArrayNode();
		
		for(ScheduleItem si : list){
			s.addPOJO(si);
		}
		try{
			obMap.writeValue(new File("Routes.json"), s);
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
	static String readFile(String path){
		String s="";
		try{
			byte[] encoded=Files.readAllBytes(Paths.get(path));
			s =  new String(encoded, Charset.defaultCharset());
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
}
