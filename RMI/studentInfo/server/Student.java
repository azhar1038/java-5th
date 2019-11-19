import java.io.Serializable;
public class Student implements Serializable{
    private String name, branch, address;
    private long regd, phone;
    private int roll;

    Student(String name, long regd, int roll, String branch, long phone, String address){
        this.name = name;
        this.regd = regd;
        this.roll = roll;
        this.branch = branch;
        this.phone = phone;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public long getRegd(){
        return regd;
    }

    public int getRoll(){
        return roll;
    }

    public String getBranch(){
        return branch;
    }

    public long getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setRegd(long newRegd){
        regd = newRegd;
    }

    public void setRoll(int newRoll){
        roll = newRoll;
    }

    public void setBranch(String newBranch){
        branch = newBranch;
    }

    public void setPhone(long newPhone){
        phone = newPhone;
    }

    public void setAddress(String newAddress){
        address = newAddress;
    }    
}