import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ImplService extends UnicastRemoteObject implements Service{

    private ArrayList<Student> students = new ArrayList<Student>();

    ImplService() throws RemoteException{
        super();
        students.add(new Student("Abhijit Panda", 1701105100L, 36701, "CSE", 8114957735L, "Jajpur"));
        students.add(new Student("Abhijit Pradhan", 1701105251L, 36702, "CSE", 7008972527L, "Bhadrak"));
        students.add(new Student("Abhinab Kumar Nayak", 1701105275L, 36703, "CSE", 8457927168L, " "));
        students.add(new Student("Abhisekh Mahapatra", 1701105122L, 36704, "CSE", 8338848210L, " "));
        students.add(new Student("Abinash Sahoo", 1701105105L, 36705, "CSE", 7609929488L, " "));
        students.add(new Student("Aditya Ranjan Mohanty", 1701105429L, 36706, "CSE", 9777643048L, "Kendrapada"));
        students.add(new Student("Amit Tripathy", 1701105098L, 36702, "CSE", 7381159118L, "Uttar Pradesh"));
        students.add(new Student("Andhavarapu Supriya", 1701105293L, 36708, "CSE", 8018917590L, "Koraput"));
        students.add(new Student("Ansumal Shamal", 1701105121L, 36709, "CSE", 9439922731L, " "));
        students.add(new Student("Arabinda Guin", 1701105263L, 36710, "CSE", 8079965335L, " "));
    }

    @Override
    public String newStudent(String name, long regd, int roll, String branch, long phone, String address){
        if(regd < 1000000000L || regd > 9999999999L) return "Invalid Registration number format.";
        else if(roll < 10000 || roll > 99999) return "Invalid Roll number format";
        else  if(phone < 1000000000L || phone > 9999999999L) return "Invalid Phone number";
        students.add(new Student(name, regd, roll, branch, phone, address));
        return "New student registered successfully!";
    }

    @Override
    public Student searchByRegd(long regd){
        for(Student s : students){
            if(s.getRegd() == regd) return s;
        }
        return null;
    }

    @Override
    public int searchByRegdIndex(long regd){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getRegd() == regd) return i;
        }
        return -1;
    }

    @Override
    public Student searchByRoll(long roll){
        for(Student s : students){
            if(s.getRoll() == roll) return s;
        }
        return null;
    }

    @Override
    public int searchByRollIndex(long roll){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getRoll() == roll) return i;
        }
        return -1;
    }

    @Override
    public Student getStudent(int index){
        if(index >= 0) return students.get(index);
        return null;
    }

    @Override
    public ArrayList<Student> searchByName(String name){
        ArrayList<Student> st = new ArrayList<Student>();
        name = name.toLowerCase();
        for(Student s: students){
            String sName = s.getName().toLowerCase(); 
            if(sName.matches(name) || sName.matches("(.*)"+name) || sName.matches(name+"(.*)") || sName.matches("(.*)"+name+"(.*)")){
                st.add(s);
            }
        }
        return st;
    }

    @Override
    public ArrayList<Student> searchByBranch(String branch){
        ArrayList<Student> st = new ArrayList<Student>();
        branch = branch.toLowerCase();
        for(Student s: students){
            String sBranch = s.getBranch().toLowerCase(); 
            if(sBranch.matches(branch) || sBranch.matches("(.*)"+branch) || sBranch.matches(branch+"(.*)") || sBranch.matches("(.*)"+branch+"(.*)")){
                st.add(s);
            }
        }
        return st;
    }

    @Override
    public String updateName(int i, String newName){
        Student s = students.get(i);
        s.setName(newName);
        return "Updated Successfully!";
    }

    @Override
    public String updateRegd(int i, long newRegd){
        Student s = students.get(i);
        if(newRegd < 1000000000L || newRegd > 9999999999L) return "Invalid Registration number format.";
        else s.setRegd(newRegd);
        return "Updated Successfully!";
    }

    @Override
    public String updateRoll(int i, int newRoll){
        Student s = students.get(i);
        if(newRoll < 10000 || newRoll > 99999) return "Invalid Roll number format";
        else s.setRoll(newRoll);
        return "Updated Successfully!";
    }

    @Override
    public String updateBranch(int i, String newBranch){
        Student s = students.get(i);
        s.setBranch(newBranch);
        return "Updated Successfully!";
    }

    @Override
    public String updatePhone(int i, long newPhone){
        Student s = students.get(i);
        if(newPhone < 1000000000L || newPhone > 9999999999L) return "Invalid Phone number";
        else s.setPhone(newPhone);
        return "Updated Successfully!";
    }

    @Override
    public String updateAddress(int i, String newAddress){
        Student s = students.get(i);
        s.setAddress(newAddress);
        return "Updated Successfully!";
    }

    @Override
    public String deleteStudent(int index){
        students.remove(index);
        return "Student deleted successfully!";
    }
}