import java.rmi.Naming;
public class Server{
    public static void main(String[] args){
        try{
            ImplService obj = new ImplService();
            Service skeleton = (Service) obj;
            Naming.rebind("rmi://127.0.0.1:1025/studentinfo", skeleton);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}