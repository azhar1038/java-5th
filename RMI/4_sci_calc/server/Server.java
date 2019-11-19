import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class Server{
	public static void main(String[] args){
		try{
			ImplService obj = new ImplService();
			Service stub = (Service) obj;
			Naming.rebind("rmi://localhost:1025/scicalc", stub);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}