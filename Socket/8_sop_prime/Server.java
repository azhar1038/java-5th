import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server{
    public static void main(String[] args) throws IOException{
        int port = 1025;
        System.out.println("Starting server at Port: "+port);
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Waiting for client at: "+port);
        Socket sf = ss.accept();
        System.out.println("Connected to: "+sf.getRemoteSocketAddress());
        DataInputStream dsin = new DataInputStream(sf.getInputStream());
        DataOutputStream dsout = new DataOutputStream(sf.getOutputStream());
                                       String req="", res="";
        while(true){
            req = dsin.readUTF();
            System.out.println(sf.getRemoteSocketAddress()+" : "+req);
            if(req.equalsIgnoreCase("Bye")){
                dsout.writeUTF("Bye");
                break;
            };
            res = getResponse(req);
            dsout.writeUTF(res);
            dsout.flush();
        }
        dsin.close();hhhhgtyuniio
        sf.close();
        ss.close();
    }

    static String getResponse(String req){
        String res;
        ArrayList<Integer> num = new ArrayList<Integer>();
        try{
            for(int i=0; i<req.length(); i++){
                int n = Integer.parseInt(""+req.charAt(i));
                if(isPrime(n)) num.add(n);
            }
            int result=0;
            for(int i=0; i<num.size()-1; i++){
                result += num.get(i)*num.get(i+1);
            }
            res = Integer.toString(result);
        }catch(Exception e){
            res = "Bye";
        }
        return res;
    }
    static boolean isPrime(int num){
        boolean prime = true;
        if(num < 2) return false;
        for(int i=2; i<=num/2; i++){
            if(num%i == 0){
                prime = false;
                break;
            }
        }
        return prime;
    } 
}