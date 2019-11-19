import java.net.*;
import java.io.*;

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
        int num1=0, num2=0;
        try{
            String[] numbers = req.split(" ");
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);
        } catch (Exception e) {
            res = "Bye";
        }
        if(num2 > 0) res = "1, 1, ";
        int a=1, b=1, c=0;
        while(c<=num2){
            c=a+b;
            if(c>=num1 && c<=num2) res += c+", ";
            a=b;
            b=c;
        }
        return res;
    }
}