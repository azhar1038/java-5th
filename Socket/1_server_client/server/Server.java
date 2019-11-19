import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server{
    public static void main(String[] args) throws IOException{
        int port = 999;
        System.out.println("Starting server at Port: "+port);
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Waiting for client at: "+port);
        Socket sf = ss.accept();
        System.out.println("Connected to: "+sf.getRemoteSocketAddress());
        DataInputStream dsin = new DataInputStream(sf.getInputStream());
        DataOutputStream dsout = new DataOutputStream(sf.getOutputStream());
        System.out.println("Client Request: "+dsin.readUTF());
        System.out.print("Response: ");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        dsout.writeUTF(res);
        dsin.close();
        sf.close();
        ss.close();
    }
}