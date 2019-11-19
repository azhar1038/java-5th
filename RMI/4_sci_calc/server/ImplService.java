import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class ImplService extends UnicastRemoteObject implements Service{
	
	final double PI = 3.14159265358979323846;
	final int PRECISION = 50;
	final double EXP = 2.71828183;
	
	public ImplService() throws RemoteException{
		super();
	}
	
	@Override
	public double sqrt(int a){
		if(a<0) return Double.NaN;
		double temp;
		double sr = a/2;
		do{
			temp = sr;
			sr = (temp + (a/temp))/2;
		}while(temp-sr != 0);
		return sr;
	}
	
	@Override
	public double power(double a, int b){
		double p = 1;
		while(b>0){
			p *= a;
			b--;
		}
		return p;
	}
	
	@Override
	public double square(double a){
		return a*a;
	}
	
	@Override
	public double sin(double a){
		if(a == Double.NEGATIVE_INFINITY || a >= Double.POSITIVE_INFINITY){
			return Double.NaN;
		}
				
		a %= 2*PI;
		if(a<0) a=2*PI-a;
		
		int sign = 1;
		if(a>PI){
			a -= PI;
			sign = -1;
		}
		
		double temp = 0;
		for(int i=0; i<=PRECISION; i++){
			temp += power(-1, i)*(power(a, 2*i+1)/factorial(2*i+1));
		}
		return sign*temp;
	}
	
	@Override
	public double cos(double a){
		if(a == Double.NEGATIVE_INFINITY || a >= Double.POSITIVE_INFINITY){
			return Double.NaN;
		}
		
		a %= 2*PI;
		if(a<0) a= -1*a;
		
		int sign = 1;
		if(a>PI){
			a -= PI;
			sign = -1;
		}
		
		double temp = 1;
		for(int i=1; i<=PRECISION; i++){
			temp +=power(-1, i)*(power(a, 2*i)/factorial(2*i));
		}
		return sign*temp;
	}
	
	@Override
	public double tan(double a){
		double sine = sin(a);
		double cosine = cos(a);
		return sine/cosine;
	}
	
	@Override
	public double exp(int a){
		return power(EXP, a);
	}
	
	public double factorial(int n){
		double fact = 1;
		while(n>0){
			fact *= n;
			n--;
		}
		return fact;
	}
}