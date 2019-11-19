import java.io.Serializable;
public class Train implements Serializable{
	private int train_no;
	private String train_name;
	private String[] destinations;
	private String[] time;
	private int vacSL;
	private int ocSL;
	private int vacAC;
	private int ocAC;
	
	Train(int train_no, String train_name, String[] destinations, String[] time, int vacSL, int ocSL, int vacAC, int ocAC){
		this.train_no = train_no;
		this.train_name = train_name;
		this.destinations = destinations;
		this.time = time;
		this.vacSL = vacSL;
		this.ocSL = ocSL;
		this.vacAC = vacAC;
		this.ocAC = ocAC;
	}
	
	public boolean isCompatible(String source, String destination){
		int sourceIndex=-1, destIndex=-1;
		for(int i=0; i<destinations.length; i++){
			if(destinations[i].equalsIgnoreCase(source)){
				sourceIndex=i;
				break;
			}
		}
		if(sourceIndex != -1){
			for(int i=sourceIndex+1; i<destinations.length; i++){
				if(destinations[i].equalsIgnoreCase(destination)){
					destIndex=i;
					break;
				}
			}
		}
		else return false;
		if(sourceIndex > -1 && destIndex > 0) return true;
		else return false;
	}
	
	public int getTrainNo(){
		return train_no;
	}
	
	public String getTrainName(){
		return train_name;
	}
	
	public String getTime(String place){
		int index=0;
		for(int i=0; i<destinations.length; i++){
			if(destinations[i].equals(place)){
				index = i;
				break;
			}
		}
		return time[index];
	}

	public String[] getDestinations(){
		return destinations;
	}

	public String[] getTimes(){
		return time;
	}
	
	public int getVacSL(){
		return vacSL;
	}
	
	public int getOcSL(){
		return ocSL;
	}
	
	public int getVacAC(){
		return vacAC;
	}
	
	public int getOcAC(){
		return ocAC;
	}
	
	public boolean canBook(int st){
		if(st == 0 && vacSL > 0) return true;
		else if(st == 1 && vacAC > 0) return true;
		return false;
	}
	
	public void doBooking(int st){
		if(st == 0 && vacSL>0){
			vacSL--;
			ocSL++;
		}
		else if(st == 1 && vacAC>0){
			vacAC--;
			ocAC++;
		}
	}
	
	public void cancelBooking(int st){
		if(st==0 && ocSL>0){
			vacSL++;
			ocSL--;
		}
		else if(st==1 && ocAC>0){
			vacAC++;
			ocAC--;
		}
	}
}