package T2.T2;

public class Client {
	
	private int ID;
	private int ta;
	private int ts;
	private int t_med=0;
	
	public Client(int i,int tamin,int tamax,int tsmin,int tsmax)
	{
		ID=i;
		 int range = (tamax - tamin) + 1;     
		    
		ta=(int)(Math.random() * range) + tamin;
		
		range = (tsmax - tsmin) + 1;  
		ts=(int)(Math.random() * range) + tsmin;;
	}
	
	public int getID()
	{
	return ID;
}
	public int getTs()
	{
		return ts;
	}
	public int getTa()
	{
		return ta;
	}
	public int getId()
	{
		return ID;
	}
	public String toString()
	{
		String s="";
		return s+"("+ID+","+ta+","+ts+")";
	}
	public void decrementTs()
	{
		ts--;
	}
	
	public void incrementTMed()
	{
		t_med++;
	}
	public int get_med()
	{
		return t_med;
	}
	
}
