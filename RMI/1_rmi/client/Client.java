import java.rmi.*;
public class Client{
    public static void main(String[] args){
        try{
            Service stub = (Service) Naming.lookup("rmi://localhost:1025/factandpow");
            System.out.println("5! = "+stub.fact(5));
            System.out.println("2^3 = "+stub.pow(2,3));
        }catch(Exception e){
            System.out.println(e);
        }
    }
}