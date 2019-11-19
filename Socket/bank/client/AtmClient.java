import java.net.*;
import java.io.*;
import java.util.Scanner;

public class AtmClient{
	enum Status{
		ACCOUNT_INVALID, ACCOUNT_VALID, PASSWORD_INVALID, PASSWORD_VALID, END_OF_CHANCE;
	}
	
	public static void main(String[] args){
		AtmClient runner = new AtmClient();
		try{
			runner.run();
		}catch(Exception e){
			System.out.println("Oops! We ran into some problem. Please try again later.");
		}
	}
	public void run() throws IOException{
		String host = "127.0.0.1";
        int port = 1025;
        System.out.println("Starting connection to Port: "+port);
        Socket s = new Socket(host, port);
        System.out.println("Connected to: "+s.getRemoteSocketAddress());
        DataInputStream dsin = new DataInputStream(s.getInputStream());
        DataOutputStream dsout = new DataOutputStream(s.getOutputStream());
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("\nWELCOME TO AZ ATM\n");
			boolean validAccount=false, validPassword=false, chance=true;
			do{
				System.out.print("Please enter your account number: ");
				int accountNumber = sc.nextInt();
				dsout.writeInt(accountNumber);
				dsout.flush();
				int response = dsin.readInt();
				if(response == Status.ACCOUNT_INVALID.ordinal()){
					validAccount = false;
					System.out.println("Sorry! This account number is Invalid.");
				}else if(response == Status.ACCOUNT_VALID.ordinal()){
					validAccount = true;
				}
			}while(!validAccount);
			do{
				System.out.print("Please enter your Password: ");
				int password = sc.nextInt();
				dsout.writeInt(password);
				dsout.flush();
				int response = dsin.readInt();
				if(response == Status.PASSWORD_VALID.ordinal()){
					validPassword = true;
				}
				else if(response == Status.PASSWORD_INVALID.ordinal()){
					validPassword = false;
					System.out.println("Wrong Password!");
				}
				else if(response == Status.END_OF_CHANCE.ordinal()){
					chance = false;
					validPassword = false;
					System.out.println("Wrong Password! Your account has been locked for safety reasons.");
				}
			}while(!validPassword && chance);
			if(!validAccount || !validPassword) continue;
			System.out.println("\nSelect what you want to do:\n");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("5. Change Password");
			System.out.println("6. Cancel");
			System.out.print("Enter your choice: ");
			int service = sc.nextInt();
			dsout.writeInt(service);
			double amount, curBalance;
			switch(service){
				case 1:
					curBalance = dsin.readDouble();
					System.out.println("Your current balance is Rs "+curBalance);
					break;
				case 2:
					System.out.print("Enter the amount you want to deposit (in Rs): ");
					amount = sc.nextDouble();
					dsout.writeDouble(amount);
					System.out.println("Amount deposited Successfully!");
					System.out.println("Your current balance is Rs "+dsin.readDouble());
					break;
				case 3:
					System.out.print("Enter the amount you want to withdraw (in Rs): ");
					amount = sc.nextDouble();
					dsout.writeDouble(amount);
					boolean success = dsin.readBoolean();
					if(success) System.out.println("Withdraw successful.\nPlease take your money.");
					else System.out.println("Cannot withdraw amount. Insufficient balance");
					System.out.println("Your current balance is Rs "+dsin.readDouble());
					break;
				case 4:
					System.out.print("Enter the account number to which you want to transfer: ");
					int account = sc.nextInt();
					dsout.writeInt(account);
					boolean accValid = dsin.readBoolean();
					if(!accValid){
						System.out.println("This account number is invalid!");
						break;
					}
					System.out.print("Enter the amount you want to transfer (in Rs): ");
					amount = sc.nextDouble();
					dsout.writeDouble(amount);
					boolean transfer = dsin.readBoolean();
					if(transfer) System.out.println("Transfer successful.");
					else System.out.println("Cannot transfer amount. Insufficient balance");
					System.out.println("Your current balance is Rs "+dsin.readDouble());
					break;
				case 5:
					System.out.print("Enter your current password: ");
					int password = sc.nextInt();
					dsout.writeInt(password);
					boolean curPassword = dsin.readBoolean();
					if(!curPassword){
						System.out.println("Cannot change your password. Your password is incorrect.");
						break;
					}
					System.out.print("Enter new password: ");
					dsout.writeInt(sc.nextInt());
					System.out.print("Again enter your new password: ");
					dsout.writeInt(sc.nextInt());
					if(dsin.readBoolean()){
						System.out.println("Changed password successfully.");
					}else{
						System.out.println("Cannot change password. New password did not match.");
					}
					break;
				default:
					break;
			}
			System.out.println("\nThanks for using our service.");
		}
	}
}