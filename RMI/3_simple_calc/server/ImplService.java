import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class ImplService extends UnicastRemoteObject implements Service{
	public ImplService() throws RemoteException{
		super();
	}
	
	@Override
	public double add(double a, double b){
		return a+b;
	}
	
	@Override
	public double subtract(double a, double b){
		return a-b;
	}
	
	@Override
	public double multiply(double a, double b){
		return a*b;
	}
	
	@Override
	public double divide(double a, double b){
		return a/b;
	}
	
	@Override
	public int mod(int a, int b){
		return a%b;
	}
}