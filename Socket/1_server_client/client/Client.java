import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) throws IOException{
        String host = "localhost";
        int port = 999;
        System.out.println("Starting connection to Port: "+port);
        Socket s = new Socket(host, port);
        System.out.println("Connected to: "+s.getRemoteSocketAddress());
        DataInputStream dsin = new DataInputStream(s.getInputStream());
        DataOutputStream dsout = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Query: ");
        String dt = sc.next();
        dsout.writeUTF(dt);
        dsout.flush();
        System.out.println("Server Response: "+dsin.readUTF());
        dsin.close();
        s.close();
    }
}