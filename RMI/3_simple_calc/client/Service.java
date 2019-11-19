import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote{
	double add(double a, double b) throws RemoteException;
	double subtract(double a, double b) throws RemoteException;
	double multiply(double a, double b) throws RemoteException;
	double divide(double a , double b) throws RemoteException;
	int mod(int a, int b) throws RemoteException;
}