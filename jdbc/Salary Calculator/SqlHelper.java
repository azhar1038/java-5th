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

//  It does all interaction with sql starting from establishing to closing the connection.

//  Structure of database used:
//
//  employee
//  +-------------+-------------+------+-----+---------+-------+
//  | Field       | Type        | Null | Key | Default | Extra |
//  +-------------+-------------+------+-----+---------+-------+
//  | id          | int(11)     | NO   | PRI | NULL    |       |
//  | name        | varchar(20) | NO   |     | NULL    |       |
//  | designation | int(11)     | NO   |     | NULL    |       |
//  | join_date   | date        | NO   |     | NULL    |       |
//  +-------------+-------------+------+-----+---------+-------+
//
//  attendance
//  +----------+----------+------+-----+---------+-------+
//  | Field    | Type     | Null | Key | Default | Extra |
//  +----------+----------+------+-----+---------+-------+
//  | id       | int(11)  | YES  |     | NULL    |       |
//  | day      | date     | YES  |     | NULL    |       |
//  | start    | datetime | YES  |     | NULL    |       |
//  | end      | datetime | YES  |     | NULL    |       |
//  | duration | int(11)  | YES  |     | NULL    |       |
//  +----------+----------+------+-----+---------+-------+
//
//  salary
//  +-------------+-------------+------+-----+---------+----------------+
//  | Field       | Type        | Null | Key | Default | Extra          |
//  +-------------+-------------+------+-----+---------+----------------+
//  | id          | int(11)     | NO   | PRI | NULL    | auto_increment |
//  | designation | varchar(20) | NO   |     | NULL    |                |
//  | salary_hr   | int(11)     | NO   |     | NULL    |                |
//  +-------------+-------------+------+-----+---------+----------------+

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SqlHelper{
    Connection con;

    // Establishing connection to the server.
    public SqlHelper(String database, String user, String password) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/"+database,
            user,
            password
        );
    }
    
    // It prints all the available Designation as in salary table.
    protected void printDesignations() throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, designation from salary");
        while(rs.next()){
            System.out.println(rs.getInt(1)+") "+rs.getString(2));
        }
        st.close();
    }

    // It checks anyone by given id already exists or not.
    protected boolean checkID(int id) throws SQLException{
        Statement st = con.createStatement();
        boolean valid = false;
        ResultSet rs = st.executeQuery("SELECT COUNT(id) FROM employee WHERE id = "+id+";");
        if(rs.next()){
            if(!rs.getString(1).equals("0"))  valid = true;
        }
        st.close();
        return valid;
    }

    // It checks if one with given id has already entered today or not.
    protected boolean checkTodayEntry(int id) throws SQLException{
        Statement st = con.createStatement();
        boolean valid = false;
        ResultSet rs = st.executeQuery("SELECT COUNT(id) FROM attendance WHERE date(day) = date(SYSDATE()) AND id = "+id+";");
        if(rs.next()){
            if(!rs.getString(1).equals("0")) return true;
        }
        st.close();
        return valid;
    }

    // It registers a new user.
    // It updates employee table.
    protected int register(String name, int id, int desg) throws SQLException{
        PreparedStatement ps = con.prepareStatement("INSERT INTO employee (name, id, designation, join_date) values (?,?,?,SYSDATE())");
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.setInt(3, desg);
        int n = ps.executeUpdate();
        ps.close();
        return n;
    }

    // It makes an entry for given id in attendance table.
    protected int start(int id) throws SQLException{
        Statement st;
        st = con.createStatement();
        int n = st.executeUpdate("INSERT INTO attendance (id, day, start, end) values ("+id+", SYSDATE(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);");
        st.close();
        return n;
    }

    // It updates the end value for given id according to system date. 
    protected int stop(int id) throws SQLException{
        Statement st;
        st = con.createStatement();
        int n = st.executeUpdate("UPDATE attendance SET end = CURRENT_TIMESTAMP WHERE id = "+id+" AND date(day) = date(SYSDATE());");
        st.close();
        return n;
    }

    // It generates report for each day entry of given id.
    protected DayReport[] calculate(int id) throws SQLException{
        ArrayList<DayReport> report = new ArrayList<DayReport>();
        Statement st;
        st = con.createStatement();
        int n = st.executeUpdate("UPDATE attendance SET duration = TIMESTAMPDIFF(MINUTE, start, end) where id = "+id+";");
        ResultSet rs = st.executeQuery("SELECT day, start, end, duration FROM attendance WHERE id = "+id+";");
        while(rs.next()){
            LocalDateTime ldt;
            String date = rs.getDate(1).toString();
            ldt = rs.getTimestamp(2).toLocalDateTime();
            String start = ""+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
            ldt = rs.getTimestamp(3).toLocalDateTime();
            String end = ""+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
            int duration = rs.getInt(4);
            report.add(new DayReport(date, start, end, duration));
        }
        DayReport[] reportArray = new DayReport[report.size()];
        reportArray = report.toArray(reportArray);
        st.close();
        return reportArray;
    }

    // It calculates the salary for the given id.
    protected int salary(int id) throws SQLException{
        Statement st;
        int duration = 0;
        int salary_hr = 0;
        st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT SUM(duration) FROM attendance where id = "+id+";");
        if(rs.next()){
            duration = rs.getInt(1);
        }
        rs = st.executeQuery("select salary_hr from employee inner join salary on employee.designation = salary.id where employee.id = "+id+";");
        if(rs.next()){
            salary_hr = rs.getInt(1);
        }
        st.close();
        return duration/60*salary_hr;
    }

    // It closes the server connection safely.
    protected void exit() throws Exception{
        con.close();
        System.out.println("System closed successfully :)\n");
    }
}
