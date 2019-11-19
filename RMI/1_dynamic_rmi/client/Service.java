import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote{
	int factorial(int n) throws RemoteException;
	boolean isPalindrome(String s) throws RemoteException;
	String primeRange(int a, int b) throws RemoteException;
	String fibonacciRange(int a, int b) throws RemoteException;
	int sumOfProductDigits(int n) throws RemoteException;
	int sumOfProductPrimeDigits(int n) throws RemoteException;
}