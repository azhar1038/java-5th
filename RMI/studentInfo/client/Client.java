import java.rmi.Naming;
import java.util.Scanner;
import java.util.ArrayList;

public class Client{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        try{
            Service stub = (Service) Naming.lookup("rmi://127.0.0.1:1025/studentinfo");
            int choice = 0;
            while(choice != 10){
                System.out.println("\nSTUDENT INFORMATION MANAGEMENT SYSTEM (SIMS)\n");
                System.out.println("Select what you want to do:");
                System.out.println("1. Register a new Student.");
                System.out.println("2. Search Student by Registration number.");
                System.out.println("3. Search Student by Roll number.");
                System.out.println("4. Search Students by Name.");
                System.out.println("5. Search Students by Branch");
                System.out.println("6. Update Student's info. by Registration number.");
                System.out.println("7. Update Student's info. by Roll number.");
                System.out.println("8. Delete a Student by Registration number.");
                System.out.println("9. Delete a Student by Roll number.");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                String name, branch, address, response;
                long regd, phone;
                int roll, i;
                Student s;
                ArrayList<Student> students;
                switch(choice){
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        System.out.print("Enter Registration number (10 digits): ");
                        regd = sc.nextLong();
                        System.out.print("Enter Roll number (5 digits): ");
                        roll = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter branch: ");
                        branch = sc.nextLine();
                        System.out.print("Enter Phone number (10 digits): ");
                        phone = sc.nextLong();
                        sc.nextLine();
                        System.out.print("Enter Address: ");
                        address = sc.nextLine();
                        response = stub.newStudent(name, regd, roll, branch, phone, address);
                        System.out.println("\n"+response);
                        break;
                    case 2:
                        System.out.print("Enter Registration number (10 digits): ");
                        regd = sc.nextLong();
                        s = stub.searchByRegd(regd);
                        if(s != null){
                            System.out.println("\nName       :  "+s.getName());
                            System.out.println("Roll No.   :  "+s.getRoll());
                            System.out.println("Regd No.   :  "+s.getRegd());
                            System.out.println("Branch     :  "+s.getBranch());
                            System.out.println("Phone No.  :  "+s.getPhone());
                            System.out.println("Address    :  "+s.getAddress());
                        }else{
                            System.out.println("This registration number does not exists.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Roll number (5 digits): ");
                        roll = sc.nextInt();
                        s = stub.searchByRoll(roll);
                        if(s != null){
                            System.out.println("\nName       :  "+s.getName());
                            System.out.println("Roll No.   :  "+s.getRoll());
                            System.out.println("Regd No.   :  "+s.getRegd());
                            System.out.println("Branch     :  "+s.getBranch());
                            System.out.println("Phone No.  :  "+s.getPhone());
                            System.out.println("Address    :  "+s.getAddress());
                        }else{
                            System.out.println("This roll number does not exists.");
                        }
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        students = stub.searchByName(name);
                        System.out.println("\n           Name          | Roll No. |  Regd. No.   |     Branch     |  Phone No.  |    Address");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                        for(Student st: students){
                            System.out.format(" %23s | %8d | %12d | %14s | %11d |%s\n",st.getName(),st.getRoll(),st.getRegd(),st.getBranch(),st.getPhone(),st.getAddress());
                        }
                        break;
                    case 5:
                        sc.nextLine();
                        System.out.print("Enter Branch: ");
                        branch = sc.nextLine();
                        students = stub.searchByBranch(branch);
                        System.out.println("\n           Name          | Roll No. |  Regd. No.   |     Branch     |  Phone No.  |    Address");
                        System.out.println("---------------------------------------------------------------------------------------------------------");
                        for(Student st: students){
                            System.out.format(" %23s | %8d | %12d | %14s | %11d |%s\n",st.getName(),st.getRoll(),st.getRegd(),st.getBranch(),st.getPhone(),st.getAddress());
                        }
                        break;
                    case 6:
                        System.out.print("Enter registration number: ");
                        regd = sc.nextLong();
                        i = stub.searchByRegdIndex(regd);
                        if( i != -1){
                            s = stub.getStudent(i);
                            System.out.println("\nName       :  "+s.getName());
                            System.out.println("Roll No.   :  "+s.getRoll());
                            System.out.println("Regd No.   :  "+s.getRegd());
                            System.out.println("Branch     :  "+s.getBranch());
                            System.out.println("Phone No.  :  "+s.getPhone());
                            System.out.println("Address    :  "+s.getAddress());
                            updateStudent(i, stub);
                        }
                        else System.out.println("This registration number does not exists.");
                        break;
                    case 7:
                        System.out.print("Enter roll number: ");
                        roll = sc.nextInt();
                        i = stub.searchByRollIndex(roll);
                        if( i != -1){
                            s = stub.getStudent(i);
                            System.out.println("\nName       :  "+s.getName());
                            System.out.println("Roll No.   :  "+s.getRoll());
                            System.out.println("Regd No.   :  "+s.getRegd());
                            System.out.println("Branch     :  "+s.getBranch());
                            System.out.println("Phone No.  :  "+s.getPhone());
                            System.out.println("Address    :  "+s.getAddress());
                            updateStudent(i, stub);
                        }
                        else System.out.println("This roll number does not exists.");
                        break;
                    case 8:
                        System.out.print("Enter registration number: ");
                        regd = sc.nextLong();
                        i = stub.searchByRegdIndex(regd);
                        if( i != -1){
                            response = stub.deleteStudent(i);
                            System.out.println("\n"+response);
                        }
                        else System.out.println("This registration number does not exists.");
                        break;
                    case 9:
                        System.out.print("Enter roll number: ");
                        roll = sc.nextInt();
                        i = stub.searchByRollIndex(roll);
                        if( i != -1){
                            response = stub.deleteStudent(i);
                            System.out.println("\n"+response);
                        }
                        else System.out.println("This roll number does not exists.");
                        break;
                    case 10:
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateStudent(int s, Service stub){
        int choice = 0;
        while(choice != 7){
            System.out.println("Choose what you want to update: ");
            System.out.println("1. Name");
            System.out.println("2. Regd No.");
            System.out.println("3. Roll No.");
            System.out.println("4. Branch");
            System.out.println("5. Phone No.");
            System.out.println("6. Address");
            System.out.println("7. Cancel");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            String response;
            try{
                switch(choice){
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        response = stub.updateName(s, name);
                        System.out.println("\n"+response);
                        break;
                    case 2:
                        System.out.print("Enter Registration number: ");
                        long regd = sc.nextLong();
                        response = stub.updateRegd(s, regd);
                        System.out.println("\n"+response);
                        break;
                    case 3:
                        System.out.print("Enter Roll number: ");
                        int roll = sc.nextInt();
                        response = stub.updateRoll(s, roll);
                        System.out.println("\n"+response);
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.print("Enter Branch: ");
                        String branch = sc.nextLine();
                        response = stub.updateBranch(s, branch);
                        System.out.println("\n"+response);
                        break;
                    case 5:
                        System.out.print("Enter Phone number: ");
                        long phone = sc.nextLong();
                        response = stub.updatePhone(s, phone);
                        System.out.println("\n"+response);
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();
                        response = stub.updateAddress(s, address);
                        System.out.println("\n"+response);
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            }catch(Exception e){
                System.out.println(e);
            }
             
        }
    }
}