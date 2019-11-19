public class PNR{
	private int userId;
	private long pnr;
	private String source;
	private String destination;
	private int train_no;
	private int st;
	
	PNR(int userId, long pnr, int train_no, int st, String source, String destination){
		this.userId = userId;
		this.pnr = pnr;
		this.source = source;
		this.destination = destination;
		this.train_no = train_no;
		this.st = st;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public long getPNR(){
		return pnr;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination(){
		return destination;
	}
	
	public int getTrainNo(){
		return train_no;
	}
	
	public int getSt(){
		return st;
	}
}