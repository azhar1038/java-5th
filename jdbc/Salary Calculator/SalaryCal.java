/*
*   MIT License
*   
*   Copyright (c) 2019 Md.Azharuddin
*
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*/

//  It is the main file which contains main().
//  It DOES NOT have any code to interact with the database.
//  It only takes input and shows output.

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.sql.SQLException;
public class SalaryCal{
    public static void main(String[] args){
        SalaryCal runner = new SalaryCal();
        runner.run();
    }
    public void run(){
        int choice = 0;
        SqlHelper helper = null;
        try{
            helper = new SqlHelper("salary", "azhar", "Azhar101");
            System.out.println("System booted Successfully :)\n");
        }catch(Exception ex){
            System.out.println("Failed to connect to database :(");
            ex.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("WELCOME TO SALARY CALCULATOR");
            System.out.println("1. Register");
            System.out.println("2. Start");
            System.out.println("3. Stop");
            System.out.println("4. Calculate");
            System.out.println("5. Salary");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            try{
                choice = sc.nextInt();
            }catch(InputMismatchException imex){
                System.out.println("Please select an option number!");
                choice = 0;
            }
            sc.nextLine();
            String name;
            int id, designation;
            switch(choice){
                case 0:
                    break;
                case 1:
                    try{
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        System.out.println("\nChoose Designation \n");
                        helper.printDesignations();
                        System.out.print("Enter Designation: ");
                        designation = sc.nextInt();
                        if(!helper.checkID(id)){
                            helper.register(name, id, designation);
                            System.out.println("\nRegistered Successfully :)");
                        }else{
                            System.out.println("\nThis ID is already registered!");
                        }
                        
                    }catch(SQLException sqle){
                        System.out.println("\nFailed to register :(");
                    }catch(InputMismatchException ime){
                        sc.next();
                        System.out.println("\nPlease give correct input!");
                    }finally{
                        pressEnterToContinue();
                    }
                    break;
                case 2:
                    System.out.print("Enter your ID: ");
                    try {
                        id = sc.nextInt();
                        if(helper.checkID(id)){
                            if(!helper.checkTodayEntry(id)){
                                helper.start(id);
                                System.out.println("\nMade your entry successfully! :)");
                            }else{
                                System.out.println("\nYou have already entered!");
                            }
                        }else{
                            System.out.println("\n You are not registered! :(");
                        }
                        
                    }catch(SQLException sqle){
                        System.out.println("\nFailed to make your entry! :(");
                    }catch(InputMismatchException ime){
                        sc.next();
                        System.out.println("\nPlease give correct input!");
                    }finally{
                        pressEnterToContinue();
                    }
                    break;
                case 3:
                    System.out.print("Enter your ID: ");
                    try {
                        id = sc.nextInt();
                        if(helper.checkID(id)){
                            if(helper.checkTodayEntry(id)){
                                helper.stop(id);
                                System.out.println("\nExited successfully! :)");
                            }else{
                                System.out.println("\nYou have not entered!");
                            }
                        }else{
                            System.out.println("\n You are not registered! :(");
                        }
                    }catch(SQLException sqle){
                        sqle.printStackTrace();
                        System.out.println("\nFailed to checkout! :(");
                    }catch(InputMismatchException ime){
                        sc.next();
                        System.out.println("\nPlease give correct input!");
                    }finally{
                        pressEnterToContinue();
                    }
                    break;
                case 4:
                    System.out.print("Enter your ID: ");
                    try {
                        id = sc.nextInt();
                        if(helper.checkID(id)){
                            DayReport[] reports = helper.calculate(id);
                            int total = 0;
                            System.out.printf("%13s  |%9s |%9s | %s\n","Date", "Start", "End", "Duration(in Min.)");
                            for(int i=0; i<55; i++)System.out.print("-");
                            System.out.println();
                            for(DayReport report: reports){
                                total += report.getDuration();
                                System.out.printf("%13s  |%9s |%9s |%5d\n",report.getDay(), report.getStart(), report.getEnd(), report.getDuration());
                            }
                            for(int i=0; i<55; i++)System.out.print("-");
                            System.out.printf("\n    %30s   |%5d minutes = %d hours\n", "Total", total, total/60);
                            int n = LocalDate.now().getDayOfWeek().getValue();
                            if(n == 5 && total/60 < 40){
                                System.out.println("\n\n==================================================================");
                                System.out.println("IMPORTANT! Due to less working hours, your salary will be blocked.");
                                System.out.println("==================================================================\n\n");
                            }
                        }else{
                            System.out.println("\n You are not registered! :(");
                        }
                        
                    }catch(SQLException sqle){
                        sqle.printStackTrace();
                        System.out.println("\nFailed to calculate! :(");
                    }catch(InputMismatchException ime){
                        sc.next();
                        System.out.println("\nPlease give correct input!");
                    }finally{
                        pressEnterToContinue();
                    }
                    break;
                case 5:
                    System.out.print("Enter your ID: ");
                    try {
                        id = sc.nextInt();
                        if(helper.checkID(id)){
                            int salary = helper.salary(id);
                            System.out.println("\nCalculated Salary for this week = Rs. "+salary);
                        }else{
                            System.out.println("\n You are not registered! :(");
                        } 
                    }catch(SQLException sqle){
                        sqle.printStackTrace();
                        System.out.println("\nFailed to calculate your salary! :(");
                    }catch(InputMismatchException ime){
                        sc.next();
                        System.out.println("\nPlease give correct input!");
                    }finally{
                        pressEnterToContinue();
                    }
                    break;
                default:
                    System.out.println("Thanks for using!");
                    try{
                        helper.exit();
                    }catch(Exception ex){
                        System.out.println("System ran into a problem :(");
                        System.out.println("System closed forcefully!");
                    }
                    break;
            }
        }while(choice>=0 && choice<6);
    }

    private void pressEnterToContinue(){
        System.out.println("\nPress Enter to continue...");
        try{
            System.in.read();
        }catch(Exception e){

        }
    }
}