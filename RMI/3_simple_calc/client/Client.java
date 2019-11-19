import java.rmi.*;
import java.util.Scanner;
public class Client{
	public static void main(String[] args){
        try{
            Service stub = (Service) Naming.lookup("rmi://localhost:1025/functions");
			System.out.println("Enter your choice: ");
			System.out.println("1. Add");
			System.out.println("2. Subtract");
			System.out.println("3. Multiply");
			System.out.println("4. Divide");
			System.out.println("5. Mod");
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			double a, b;
			switch(choice){
				case 1:
					System.out.print("Enter first number: ");
					a = sc.nextDouble();
					System.out.print("Enter second number: ");
					b = sc.nextDouble();
					System.out.println(a+" + "+b+" = "+stub.add(a,b));
					break;
				case 2:
					System.out.print("Enter first number: ");
					a = sc.nextDouble();
					System.out.print("Enter second number: ");
					b = sc.nextDouble();
					System.out.println(a+" - "+b+" = "+stub.subtract(a,b));
					break;
				case 3:
					System.out.print("Enter first number: ");
					a = sc.nextDouble();
					System.out.print("Enter second number: ");
					b = sc.nextDouble();
					System.out.println(a+" x "+b+" = "+stub.multiply(a,b));
					break;
				case 4:
					System.out.print("Enter first number: ");
					a = sc.nextDouble();
					System.out.print("Enter second number: ");
					b = sc.nextDouble();
					System.out.println(a+" / "+b+" = "+stub.divide(a,b));
					break;
				case 5:
					System.out.print("Enter first number: ");
					int a2 = sc.nextInt();
					System.out.print("Enter second number: ");
					int b2 = sc.nextInt();
					System.out.println(a2+"mod("+b2+") = "+stub.mod(a2,b2));
					break;
				default:
					System.out.println("Invalid Choice.");
			}
        }catch(Exception e){
            System.out.println(e);
        }
    }
}