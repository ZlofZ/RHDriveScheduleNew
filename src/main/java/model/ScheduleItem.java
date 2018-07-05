package model;

import java.util.Arrays;
import java.util.Objects;


public class ScheduleItem{
	private String name;
	private String day;
	private String[] materials;
	private int routeNumber;
	private String[] areas;
	private String[] municipalities;
	private String evenWeek;
	private int vehileNr;
	
	public ScheduleItem(String name, String day, String[] materials, int routeNumber, String[] areas, String municipalities[], String evenWeek, int vehileNr){
		this.name=name;
		this.day=day;
		this.materials=materials;
		this.routeNumber=routeNumber;
		this.areas=areas;
		this.municipalities=municipalities;
		this.evenWeek=evenWeek;
		this.vehileNr=vehileNr;
	}
	public ScheduleItem(){
	
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDay(){
		return this.day;
	}
	
	public String[] getMaterials(){
		return this.materials;
	}
	
	public int getRouteNumber(){
		return routeNumber;
	}
	
	public String[] getAreas(){
		return this.areas;
	}
	
	public String[] getMunicipalities(){
		return this.municipalities;
	}
	
	public String getEvenWeek(){
		return evenWeek;
	}
	
	public int getVehileNr(){
		return vehileNr;
	}
	
	@Override
	public String toString(){
		String mat="",are="",mun="";
		
		for(int i=0; i<getAreas().length; i++){
			if(i<getAreas().length-1)are+=areas[i]+",";
			else are+=areas[i];
		}
		for(int i=0; i<getMaterials().length; i++){
			if(i<getMaterials().length-1)mat+=materials[i]+",";
			else mat+=materials[i];
		}
		for(int i=0; i<getMunicipalities().length; i++){
			if(i<getMunicipalities().length-1)mun+=municipalities[i]+",";
			else mun+=municipalities[i];
		}
		
		return name+" | "+day+" | "+mat+" | "+routeNumber+" | "+are+" | "+mun+" | "+evenWeek+" | "+vehileNr;
	}
	
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(o==null||getClass()!=o.getClass()){
			return false;
		}
		ScheduleItem that=(ScheduleItem) o;
		return routeNumber==that.routeNumber&&
				vehileNr==that.vehileNr&&
				Objects.equals(name, that.name)&&
				Objects.equals(day, that.day)&&
				Arrays.equals(materials, that.materials)&&
				Arrays.equals(areas, that.areas)&&
				Arrays.equals(municipalities, that.municipalities)&&
				Objects.equals(evenWeek, that.evenWeek);
	}
	
	@Override
	public int hashCode(){
		
		int result=Objects.hash(name, day, routeNumber, evenWeek, vehileNr);
		result=31*result+Arrays.hashCode(materials);
		result=31*result+Arrays.hashCode(areas);
		result=31*result+Arrays.hashCode(municipalities);
		return result;
	}
}
