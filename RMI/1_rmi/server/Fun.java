import java.rmi.*;
import java.rmi.server.*;
public class Fun extends UnicastRemoteObject implements Service{
    public Fun() throws RemoteException{
        super();
    }

    @Override
    public int fact(int n){
        int f = 1;
        while(n>0){
            f *= n;
            n--;
        }
        return f;
    }

    @Override
    public int pow(int a, int b){
        int p=1;
        while(b>0){
            p *= a;
            b--;
        }
        return p;
    }
}