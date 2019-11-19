import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Service extends Remote{
    String newStudent(String name, long regd, int roll, String branch, long phone, String address) throws RemoteException;
    Student searchByRegd(long regd) throws RemoteException;
    Student searchByRoll(long roll) throws RemoteException;
    int searchByRegdIndex(long regd) throws RemoteException;
    int searchByRollIndex(long roll) throws RemoteException;
    Student getStudent(int index) throws RemoteException;
    ArrayList<Student> searchByName(String name) throws RemoteException;
    ArrayList<Student> searchByBranch(String branch) throws RemoteException;
    String updateName(int i, String newName) throws RemoteException;
    String updateRegd(int i, long newRegd) throws RemoteException;
    String updateRoll(int i, int newRoll) throws RemoteException;
    String updateBranch(int i, String newBranch) throws RemoteException;
    String updatePhone(int i, long newPhone) throws RemoteException;
    String updateAddress(int i, String newAddress) throws RemoteException;
    String deleteStudent(int index) throws RemoteException;
}