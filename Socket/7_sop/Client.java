import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) throws IOException{
        String host = "127.0.0.1";
        int port = 1025;
        System.out.println("Connecting to "+host+" at port: "+port);
        Socket s = new Socket(host, port);
        System.out.println("Connected to: "+s.getRemoteSocketAddress());
        DataInputStream dsin = new DataInputStream(s.getInputStream());
        DataOutputStream dsout = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);
        String req="", res="";
        System.out.println("SUM OF PRODUCT OF CONSECUTIVE NUMBERS");
        while(true){
            System.out.print("Enter a Number: ");
            try{
j                req = Integer.toString(sc.nextInt());
            }catch(Exception e){
                req = "Bye";
            }
            dsout.writeUTF(req);
            dsout.flush();
            res = dsin.readUTF();
            System.out.println(s.getRemoteSocketAddress()+" : Sum of product of consecutive numbers of "+req+" is "+res);
            if(res.equalsIgnoreCase("Bye")) break;
        }
        dsin.close();
        s.close();
    }
}