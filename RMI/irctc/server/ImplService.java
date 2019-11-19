import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.math.*;

public class ImplService extends UnicastRemoteObject implements Service{
	
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<PNR> pnrs = new ArrayList<PNR>();
	
	public ImplService() throws RemoteException{
		super();
		trains.add(new Train(12802, "PURUSHOTTAM EXPRESS", 
		new String[]{"delhi", "kanpur", "fatehpur", "allahabad", "mirzapur", "chunar", "bhabua", "sasaram", "gaya", "paharpur", "koderma", "parasnath", "bokaro", "tatanagar", "hijli", "balasore", "bhadrak", "jajpur", "cuttack", "bhubaneshwar", "khurda", "puri"},
		new String[]{"10:25 PM", "3:55 AM","5:10 AM","7:05 AM", "8:15 AM", "8:45 AM", "11:12 AM", "11:45 AM", "1:15 PM", "2:00 PM","2:35 PM","3:36 PM", "5:15 PM", "8:07 PM", "10:30 PM", "12:04 AM","1:18 AM", "1:56 AM", "3:00 AM", "3:45 AM", "4:20 AM", "5:30 AM"},
		960, 0, 270, 0));
		
		trains.add(new Train(18478, "KALINGA UTKAL EXPRESS",
		new String[]{"haridwar", "roorkee", "muzaffarnagar", "meerut", "ghaziabad", "faridabad", "mathura", "agra", "gwalior", "jhansi", "birsinghpur", "bilaspur", "sakti", "jharsuguda", "tatanagar", "hijli", "jaleswar", "balasore", "soro", "bhadrak", "jajpur", "cuttack", "bhubaneshwar", "khurda", "puri"},
		new String[]{"6:45 AM", "7:28 AM", "9:00 AM", "9:36 AM", "11:00 AM", "12:24 PM", "2:10 PM", "3:10 PM", "5:31 PM", "7:15 PM", "5:30 AM", "10:45 AM", "12:34 PM", "3:10 PM", "8:24 PM", "10:56 PM", "12:00 AM", "12:45 AM", "1:18 AM", "2:05 AM", "2:43 AM", "3:45 AM", "4:30 AM", "5:05 AM", "6:20 AM"},
		864, 0, 304, 0));
		
		trains.add(new Train(58001, "HOWRAH-PURI PASSENGER",
		new String[]{"howrah", "santragachi", "panskura", "kharagpur", "jaleswar", "basta", "balasore", "nilgiri", "soro", "bhadrak", "kenduapada", "jajpur", "kapilas", "salagaon", "narajmarthapur", "barang", "patia", "bhubaneshwar", "retang", "khurda", "malatipatpur", "puri"},
		new String[]{"12:05 AM", "12:25 AM", "1:57 AM", "3:05 AM", "4:42 AM", "5:08 AM", "5:50 AM", "6:02 AM", "6:32 AM", "7:28 AM", "7;53 AM", "8:25 AM", "9:30 AM", "10:23 AM", "10:34 AM", "10:46 AM", "10:58 AM", "11:20 AM", "11:38 AM", "1:55 PM", "3:00 PM", "3:30 PM"},
		0, 0, 0, 0));
		
		trains.add(new Train(22840, "ROURKELA INTERCITY SF EXPRESS",
		new String[]{"bhubaneshwar", "narajmartharpur", "dhenkanal", "talcher", "angul", "sambalpur", "jharsuguda", "rourkela"},
		new String[]{"2:10 PM", "2:36 PM", "3:18 PM", "4:06 PM", "4:18 PM", "6:40 PM", "7:50 PM", "9:40 PM"},
		412, 0, 204, 0));
		
		trains.add(new Train(12891, "BHUBANESHWAR INTERCITY SF EXPRESS",
		new String[]{"bangriposi", "baripada", "rupsa", "haldipada", "balasore", "soro", "bhadrak", "jajpur", "cuttack", "bhubaneshwar"},
		new String[]{"4:20 AM", "5:02 AM", "6:23 AM", "6:32 AM", "6:44 AM", "7:08 AM", "7:40 AM", "8:18 AM", "9:20 AM", "10:00 AM"},
		206, 0, 0, 0));
		
		users.add(new User(100, 1000, "Abhisekh", 9074576289L));
		users.add(new User(101, 1001, "Abhijit", 8906754562L));
		users.add(new User(102, 1002, "Biswajeet", 9040627845L));
		users.add(new User(103, 1003, "Prakesh", 9079456237L));
		users.add(new User(104, 1004, "Vikash", 8906753456L));
		users.add(new User(105, 1005, "Vanketesh", 7006557865L));
	}
	
	@Override
	public User login(int id, int password){
		for(User u : users){
			if(u.getId() == id && u.getPassword() == password){
				return u;
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<Train> search(String source, String destination){
		ArrayList<Train> response = new ArrayList<Train>();
		for(Train t : trains){
			if(t.isCompatible(source, destination)) response.add(t);
		}
		return response;
	}
	
	@Override
	public Train search(int train_no){
		Train response = null;
		for(Train t : trains){
			if(t.getTrainNo() == train_no) response = t;
		}
		return response;
	}
	
	@Override
	public String book(String source, String destination, int train_no, int userId, int st){
		Train t = null;
		for(Train c : trains){
			if(c.getTrainNo() == train_no) t = c; 
		}
		if(t == null) return "No such train exists in our database.";
		if(t.isCompatible(source, destination)){
			if(t.canBook(st)){
				long pnr_no = (long)(Math.floor(Math.random()*9000000000L)+1000000000L);
				t.doBooking(st);
				pnrs.add(new PNR(userId, pnr_no, train_no, st, source, destination));
				return "Booked Successfully. Your PNR number is "+pnr_no;
			}
			else return "No seat available for booking in specified class.";
		}
		return "This train doesn't travel between the specified locations.";
	}
	
	@Override
	public String cancel(long pnr_no){
		PNR p = null;
		for(PNR pnr : pnrs){
			if(pnr.getPNR() == pnr_no){
				p = pnr;
				break;
			}
		}
		if(p == null) return "PNR doesn't exists.";
		int train_no = p.getTrainNo();
		for(Train t : trains){
			if(t.getTrainNo() == train_no){
				t.cancelBooking(p.getSt());
				pnrs.remove(p);
				break;
			}
		}
		return "Your booking was canceled successfully.";
	}
	
	@Override
	public String checkPNR(long pnr_no){
		PNR p = null;
		for(PNR pnr : pnrs){
			if(pnr.getPNR() == pnr_no){
				p = pnr;
				break;
			}
		}
		if(p == null) return "PNR doesn't exists.";
		String st = p.getSt()==0?"SL":"AC";
		return "Your booking for Train number: "+p.getTrainNo()+" from "+p.getSource()+" to "+p.getDestination()+" for class "+st+" is Confirmed!";
	}
}