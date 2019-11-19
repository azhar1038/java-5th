import java.sql.*;
public class TableCreate{
    public static void main(String[] args){
        Connection con;
        Statement st;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java","azhar","Azhar101"
            );
            st = con.createStatement();
            st.executeUpdate(
                "CREATE TABLE student(regd_no INTEGER,name VARCHAR(20),branch VARCHAR(10))"
            );
            System.out.println("Table created successfully :)");

        }catch(SQLException sqle){
            System.out.println("Table creation Failed :(");
            sqle.printStackTrace();
        }
    }
}