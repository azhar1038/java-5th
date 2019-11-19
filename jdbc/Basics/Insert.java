`// Insert data in table 'student' using prepared statement.
import java.sql.*;
import java.util.Scanner;
public class Insert{
    public static void main(String[] args){
        int regd_no;
        String name, branch;
        Connection con;
        PreparedStatement pst;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter registration number: ");
        regd_no = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter branch: ");
        branch = sc.next();
        try{
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java","azhar","Azhar101"
            );
            pst = con.prepareStatement(
                "INSERT INTO student (regd_no, name, branch) values (?, ?, ?)"
            );
            pst.setInt(1, regd_no);
            pst.setString(2, name);
            pst.setString(3, branch);
            int n = pst.executeUpdate();
            if(n > 0){
                System.out.println("Student information inserted successfully :)");
            }else{
                System.out.println("Failed to insert information :(");
            }
            pst.close();
            con.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}