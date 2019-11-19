import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote{
	double sqrt(int a) throws RemoteException;
	double power(double a, int b) throws RemoteException;
	double square(double a) throws RemoteException;
	double sin(double a) throws RemoteException;
	double cos(double a) throws RemoteException;
	double tan(double a) throws RemoteException;
	double exp(int a) throws RemoteException;
}