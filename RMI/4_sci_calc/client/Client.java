import java.rmi.*;
import java.util.Scanner;
public class Client{
	public static void main(String[] args){
        try{
            Service stub = (Service) Naming.lookup("rmi://localhost:1025/scicalc");
			System.out.println("Enter your choice: ");
			System.out.println("1. Square root");
			System.out.println("2. Power");
			System.out.println("3. Square");
			System.out.println("4. Sine");
			System.out.println("5. Cosine");
			System.out.println("6. Tangent");
			System.out.println("7. Exponent");
			System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			double a;
			int power;
			switch(choice){
				case 1:
					System.out.print("Enter an integer: ");
					int root = sc.nextInt();
					System.out.println("Square root of "+root+" = "+stub.sqrt(root));
					break;
				case 2:
					System.out.print("Enter number: ");
					a = sc.nextDouble();
					System.out.print("Enter power: ");
					power = sc.nextInt();
					System.out.println(a+" raised to "+power+" = "+stub.power(a,power));
					break;
				case 3:
					System.out.print("Enter number: ");
					a = sc.nextDouble();
					System.out.println("Square of "+a+" = "+stub.square(a));
					break;
				case 4:
					System.out.print("Enter number (in Radian): ");
					a = sc.nextDouble();
					System.out.println("sin("+a+") = "+stub.sin(a));
					break;
				case 5:
					System.out.print("Enter number (in Radian): ");
					a = sc.nextDouble();
					System.out.println("cos("+a+") = "+stub.cos(a));
					break;
				case 6:
					System.out.print("Enter number (in Radian): ");
					a = sc.nextDouble();
					System.out.println("tan("+a+") = "+stub.tan(a));
					break;
				case 7:
					System.out.print("Enter power (an Integer): ");
					power = sc.nextInt();
					System.out.println("e raised to "+power+" = "+stub.exp(power));
					break;
				default:
					System.out.println("Invalid Choice.");
			}
        }catch(Exception e){
            System.out.println(e);
        }
    }
}