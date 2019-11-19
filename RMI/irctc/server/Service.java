import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Service extends Remote{
	User login(int id, int password) throws RemoteException;
	ArrayList<Train> search(String source, String destination) throws RemoteException;
	Train search(int train_no) throws RemoteException;
	String book(String source, String destination, int train_no, int userId, int st) throws RemoteException;
	String cancel(long pnr_no) throws RemoteException;
	String checkPNR(long pnr_no) throws RemoteException;
}