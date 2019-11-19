import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class AtmServer{
	enum Status{
		ACCOUNT_INVALID, ACCOUNT_VALID, PASSWORD_INVALID, PASSWORD_VALID, END_OF_CHANCE;
	}

    private class User{
        private int account;
        private int password;
        private double amount;

        public User(int account, int password, double amount){
            this.account = account;
            this.password = password;
            this.amount = amount;
        }

        public int getAccount(){
            return this.account;
        }

        public int getPassword(){
            return this.password;
        }

        public double getAmount(){
            return this.amount;
        }

        public void setAmount(double newAmount){
            this.amount = newAmount;
        }
		
		public void setPassword(int newPassword){
			this.password = newPassword;
		}
    }

    public static void main(String[] args){
        AtmServer runner = new AtmServer();
        try{
            runner.run();
        }catch(IOException e){
            System.out.println("Oops! We ran into some problem. Please try again later.");
        }
    }

    public void run() throws IOException{
        
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(100,1000,12000));
        users.add(new User(101,1001,45000));
        users.add(new User(102,1002,2000));
        users.add(new User(103,1003,10000));
        users.add(new User(104,1004,2500));

        int port = 1025;
        System.out.println("Starting ATM Server at port: "+port);
        ServerSocket ss = new ServerSocket(port);
		System.out.println("Waiting for Client...");
		Socket socket = ss.accept();
		System.out.println("Connected with: "+socket.getRemoteSocketAddress());
		DataInputStream dsin = new DataInputStream(socket.getInputStream());
		DataOutputStream dsout = new DataOutputStream(socket.getOutputStream());
		while(true){
			int userIndex;
			boolean validUser=false, validPassword=false;
			int chance=2;
			
			// Get a valid Account number from User.
			do{
				int userAccount = dsin.readInt();
				userIndex = -1;
				for(int i=0; i<users.size(); i++){
					if(users.get(i).getAccount() == userAccount){
						userIndex = i;
						break;
					}
				}
				if(userIndex != -1){
					System.out.println("Valid User account.");
					validUser = true;
					dsout.writeInt(Status.ACCOUNT_VALID.ordinal());
				}
				else{
					System.out.println("Invalid user account.");
					dsout.writeInt(Status.ACCOUNT_INVALID.ordinal());
				}
			}while(!validUser);
			
			// Get correct Password for the respective Account Number.
			do{
				int userPassword = dsin.readInt();
				if(users.get(userIndex).getPassword() == userPassword){
					System.out.println("Valid Password.");
					validPassword = true;
					dsout.writeInt(Status.PASSWORD_VALID.ordinal());
				}
				else{
					System.out.println("Invalid Password");
					chance--;
					if(chance>0) dsout.writeInt(Status.PASSWORD_INVALID.ordinal());
				}
			}while(!validPassword && chance>0);
			if(!validPassword){
				System.out.println("End of chance");
				dsout.writeInt(Status.END_OF_CHANCE.ordinal());
				continue;
			}
			User user = users.get(userIndex);
			// Run the required service for valid User.
			int serviceNumber = dsin.readInt();
			double amount, curBalance;
			switch(serviceNumber){
				case 1:
					System.out.println("Running service Check Balance");
					dsout.writeDouble(user.getAmount());
					break;
				case 2:
					System.out.println("Running service Deposit");
					amount = dsin.readDouble();
					curBalance = user.getAmount();
					user.setAmount(amount+curBalance);
					dsout.writeDouble(user.getAmount());
					break;
				case 3:
					System.out.println("Running service Withdraw");
					amount = dsin.readDouble();
					curBalance = user.getAmount();
					if(curBalance-amount > 0){
						dsout.writeBoolean(true);
						user.setAmount(curBalance-amount);
					}
					else dsout.writeBoolean(false);
					dsout.writeDouble(user.getAmount());
					break;
				case 4:
					System.out.println("Running service Transfer");
					int account = dsin.readInt();
					int transferIndex = -1;
					if(account != user.getAccount())
						for(int i=0; i<users.size(); i++){
							if(account == users.get(i).getAccount()){
								transferIndex = i;
								dsout.writeBoolean(true);
								break;
							}
						}
					if(transferIndex == -1){
						dsout.writeBoolean(false);
						System.out.println("Invalid account number. Service canceled.");
						break;
					}
					amount = dsin.readDouble();
					curBalance = user.getAmount();
					double curTransferBalance = users.get(transferIndex).getAmount();
					if(curBalance-amount > 0){
						dsout.writeBoolean(true);
						user.setAmount(curBalance-amount);
						users.get(transferIndex).setAmount(curTransferBalance+amount);
					}
					else dsout.writeBoolean(false);
					dsout.writeDouble(user.getAmount());
					break;
				case 5:
					System.out.println("Running service Change Password");
					int curPassword = dsin.readInt();
					if(user.getPassword() != curPassword){
						dsout.writeBoolean(false);
						break;
					}
					dsout.writeBoolean(true);
					int newPassword = dsin.readInt();
					int verifyPassword = dsin.readInt();
					if(newPassword == verifyPassword){
						user.setPassword(newPassword);
						dsout.writeBoolean(true);
					}
					else dsout.writeBoolean(false);
				default:
					System.out.println("Exit user");
					break;
			}
			System.out.println("Ran Service");
		}
    }
}