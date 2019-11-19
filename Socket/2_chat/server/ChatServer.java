import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatServer{
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
            res = sc.nextLine();
            dsout.writeUTF(res);
            dsout.flush();
            if(res.equals("Bye")) break;
        }
        dsin.close();
        sf.close();
        ss.close();
    }
}