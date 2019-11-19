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
        System.out.println("FIND FIBONACCI NUMBERS BETWEEN RANGE");
        while(true){
            try{
                System.out.print("Enter starting number: ");
                int num1 = sc.nextInt();
                System.out.print("Enter ending number: ");
                int num2 = sc.nextInt();
                req = Integer.toString(num1)+" "+Integer.toString(num2);
            }catch(Exception e){
                req = "Bye";
            }
            dsout.writeUTF(req);
            dsout.flush();
            res = dsin.readUTF();
            if(res.equals("Bye")){
                System.out.println("Invalid input. Closed connection.");
                break;
            };
            System.out.println(s.getRemoteSocketAddress()+" : "+res);
        }
        dsin.close();
        s.close();
    }
}