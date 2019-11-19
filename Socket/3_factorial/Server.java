import java.net.*;
import java.io.*;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        String req="", res="";
        while(true){
            req = dsin.readUTF();
            System.out.println(sf.getRemoteSocketAddress()+" : "+req);
            if(req.equals("Bye")){
                dsout.writeUTF("Bye");
                break;
            };
            res = getResponse(req);
            dsout.writeUTF(res);
            dsout.flush();
            if(res.equals("Bye")) break;
        }
        dsin.close();
        sf.close();
        ss.close();
    }

    static String getResponse(String req){
        String res = "";
        try{
            int n = Integer.parseInt(req);
            res = Long.toString(factorial(n));
        } catch (Exception e) {
            res = "Bye";
        }
        return res;
    }

    static long factorial(int n){
        long fact = 1;
        for(int i=n; i>1; i--){
            fact *= i;
        }
        return fact;
    } 
}