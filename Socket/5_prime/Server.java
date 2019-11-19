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
        int num1=0, num2=0;
        try{
            String[] numbers = req.split(" ");
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);
        } catch (Exception e) {
            res = "Bye";
        }
        for(int i=num1; i<=num2; i++){
            if(isPrime(i)) res += Integer.toString(i)+", ";
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