import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class ImplService extends UnicastRemoteObject implements Service{
	public ImplService() throws RemoteException{
		super();
	}
	
	@Override
	public int factorial(int n){
        int f = 1;
        while(n>0){
            f *= n;
            n--;
        }
        return f;
    }
	
	@Override
	public boolean isPalindrome(String s){
		boolean flag = true;
        int len = s.length();
        for(int i=0; i<len; i++){
            if(s.charAt(i) != s.charAt(len-1-i)) flag = false;
        }
        return flag;
	}
	
	@Override
	public String primeRange(int a, int b){
		String res = "";
		for(int i=a; i<=b; i++){
			if(isPrime(i)) res += Integer.toString(i)+" ";
		}
		return res;
	}
	
	@Override
	public String fibonacciRange(int num1, int num2){
		String res = "";
        if(num2 > 0) res = "1 1 ";
        int a=1, b=1, c=0;
        while(c<=num2){
            c=a+b;
            if(c>=num1 && c<=num2) res += c+" ";
            a=b;
            b=c;
        }
        return res;
	}
	
	@Override
	public int sumOfProductDigits(int n){
		String req = Integer.toString(n);
        int[] num = new int[req.length()];
		for(int i=0; i<req.length(); i++){
			num[i] = Integer.parseInt(""+req.charAt(i));
		}
		int result=0;
		for(int i=0; i<num.length-1; i++){
			result += num[i]*num[i+1];
		}
        return result;
	}
	
	@Override
	public int sumOfProductPrimeDigits(int n){
		String req = Integer.toString(n);
        ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i=0; i<req.length(); i++){
			int digit = Integer.parseInt(""+req.charAt(i));
			if(isPrime(digit)) num.add(digit);
		}
		int result=0;
		for(int i=0; i<num.size()-1; i++){
			result += num.get(i)*num.get(i+1);
		}
        return result;
	}
	
	boolean isPrime(int num){
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