import java.rmi.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Client{
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            Service stub = (Service) Naming.lookup("rmi://localhost:1025/irctc");
			User user = null;
            while(user == null){
                System.out.print("Enter your id: ");
                int id = sc.nextInt();
                System.out.print("Enter your password: ");
                int password = sc.nextInt();
                user = stub.login(id, password);
                if(user == null) System.out.println("Either your id or password is incorrect. Try again.");
            }
            int select = 0;
            while(select != 6){
                System.out.println("\n Welcome "+user.getName()+"\n");
                System.out.println("Select what you want to do: ");
                System.out.println("1. Search Train by place.");
                System.out.println("2. Search Train by Train number.");
                System.out.println("3. Booking.");
                System.out.println("4. Cancel your booking.");
                System.out.println("5. Check your PNR status.");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                select = sc.nextInt();
                String source, destination, response;
                int train_no;
                long pnr_no;
                switch(select){
                    case 1:
                        System.out.print("Enter source of your journey: ");
                        source = sc.next();
                        source = source.toLowerCase();
                        System.out.print("Enter destination of your journey: ");
                        destination = sc.next();
                        destination = destination.toLowerCase();
                        ArrayList<Train> trains = stub.search(source, destination);
                        System.out.println("Train No |               Train Name               | Source Time | Dest. Time  |  SL  |  AC  ");
                        System.out.println("--------------------------------------------------------------------------------------------");
                        for(int i=0; i<trains.size(); i++){
                            Train t = trains.get(i);
                            System.out.format("%7d  | %38s |%11s  |%11s  |%4d  |%4d  \n",t.getTrainNo(),t.getTrainName(),t.getTime(source),t.getTime(destination),t.getVacSL(),t.getVacAC());
                        }
                        break;
                    case 2:
                        System.out.print("Enter train number: ");
                        train_no = sc.nextInt();
                        Train t = stub.search(train_no);
                        if(t != null){
                            System.out.println("Train number: "+t.getTrainNo());
                            System.out.println("Train name: "+t.getTrainName());
                            System.out.println("Available SL seats: "+t.getVacSL());
                            System.out.println("Available AC seats: "+t.getVacAC());
                            System.out.println("       Station       | Arrival Time");
                            System.out.println("-----------------------------------");
                            String[] destinations = t.getDestinations();
                            String[] times = t.getTimes();
                            for(int i=0; i<times.length; i++){
                                System.out.format(" %19s | %s\n",destinations[i], times[i]);
                            }
                        }
                        else System.out.println("No such train exists in our database.");
                        break;
                    case 3:
                        System.out.print("Enter train number: ");
                        train_no = sc.nextInt();
                        System.out.print("Enter source: ");
                        source = sc.next();
                        System.out.print("Enter destination: ");
                        destination = sc.next();
                        System.out.print("Select class:\n0. SL\n1. AC\nEnter your choice: ");
                        int st = sc.nextInt();
                        response = stub.book(source, destination, train_no, user.getId(), st);
                        System.out.println(response);
                        break;
                    case 4:
                        System.out.print("Enter your PNR number: ");
                        pnr_no = sc.nextLong();
                        response = stub.cancel(pnr_no);
                        System.out.println(response);
                        break;
                    case 5:
                        System.out.print("Enter your PNR number: ");
                        pnr_no = sc.nextLong();
                        response = stub.checkPNR(pnr_no);
                        System.out.println(response);
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}