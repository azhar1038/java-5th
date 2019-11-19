import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Service extends Remote{
    int fact(int n) throws RemoteException;
    int pow(int a, int b) throws RemoteException;
}