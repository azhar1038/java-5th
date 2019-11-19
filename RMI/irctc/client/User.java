import java.io.Serializable;
public class User implements Serializable{
	private int id;
	private int password;
	private String name;
	private long mobile;
	private String address;
	
	User(int id, int password, String name, long mobile){
		this.id = id;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
	}
	
	public int getId(){
		return id;
	}
	
	public int getPassword(){
		return password;
	}
	
	public String getName(){
		return name;
	}
	
	public long getMobile(){
		return mobile;
	}
}