import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class Server{
    public static void main(String[] args){
        try{
            Service skeleton = new Fun();
            Naming.rebind("rmi://localhost:1025/factandpow", skeleton);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}