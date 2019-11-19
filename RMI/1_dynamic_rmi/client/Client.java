import java.rmi.*;
import java.util.Scanner;
public class Client{
	public static void main(String[] args){
        try{
            Service stub = (Service) Naming.lookup("rmi://localhost:1025/functions");
			System.out.println("Enter your choice: ");
			System.out.println("1. Factorial");
			System.out.println("2. Check Palindrome");
			System.out.println("3. All prime numbers between");
			System.out.println("4. All fibonacci numbers between");
			System.out.println("5. Sum of product of digits of number");
			System.out.println("6. Sum of product of prime digits of number");
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			int a, b;
			switch(choice){
				case 1:
					System.out.print("Enter a number: ");
					a = sc.nextInt();
					System.out.print("Factorial of "+a+" is "+stub.factorial(a));
					break;
				case 2:
					System.out.print("Enter a string: ");
					String s  = sc.next();
					boolean p = stub.isPalindrome(s);
					if(p) System.out.print(s+" is a palindrome");
					else System.out.print(s+" is not a palindrome");
					break;
				case 3:
					System.out.print("Enter first number: ");
					a = sc.nextInt();
					System.out.print("Enter second number: ");
					b = sc.nextInt();
					System.out.println("All prime numbers are "+stub.primeRange(a,b));
					break;
				case 4:
					System.out.print("Enter first number: ");
					a = sc.nextInt();
					System.out.print("Enter second number: ");
					b = sc.nextInt();
					System.out.println("All fibonacci numbers are "+stub.fibonacciRange(a,b));
					break;
				case 5:
					System.out.print("Enter a number: ");
					a = sc.nextInt();
					System.out.print("Sum of product of digits of "+a+" is "+stub.sumOfProductDigits(a));
					break;
				case 6:
					System.out.print("Enter a number: ");
					a = sc.nextInt();
					System.out.print("Sum of product of prime digits of "+a+" is "+stub.sumOfProductPrimeDigits(a));
					break;
				default:
					System.out.println("Invalid Choice.");
			}
        }catch(Exception e){
            System.out.println(e);
        }
    }
}