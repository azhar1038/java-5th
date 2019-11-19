import java.net.*;
import java.io.*;
import java.util.Scanner;

public class PalindromeServer{
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
            if(req.equalsIgnoreCase("Bye")){
                dsout.writeUTF("Bye");
                break;
            };
            res = getResponse(req);
            dsout.writeUTF(res);
            dsout.flush();
        }
        dsin.close();
        sf.close();
        ss.close();
    }

    static String getResponse(String req){
        boolean flag = true;
        int len = req.length();
        for(int i=0; i<len; i++){
            if(req.charAt(i) != req.charAt(len-1-i)) flag = false;
        }
        if(flag) return " a Palindrome";
        else return "not a Palindrome";
    }
}