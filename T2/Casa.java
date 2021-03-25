package T2.T2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Casa extends Thread {
	
	private String nume;
	private  Queue<Client> lista = new LinkedList<Client>();
	private boolean flag=false;
	private int timp_mediu=0;
	public Casa (String n)
	{
		nume=n;
	
	}
	
	
	
	public void run(){
		try{
			while( flag ){
							
				Client c=removeClient();
				
					while(c.getTs()>0)
				{	
					sleep(100);
					c.decrementTs();
					
					for(Client c1:lista)
					{	
						c1.incrementTMed();
						
					}
					
				}

				lista.remove();
				timp_mediu+=c.get_med();
				
			}
				
		 }
		 catch( InterruptedException e ){
			 System.out.println("Intrerupere");
			 System.out.println( e.toString());
		 	}
		 }
	
	public synchronized void addClient( Client c ) throws InterruptedException{
		lista.add(c);
		notifyAll();
	
	}
	
	public synchronized Client removeClient() throws InterruptedException{
		while( lista.size() == 0 )
		wait();
		
		Client c=	lista.peek();

		notifyAll();
		return c;
	}
	public synchronized void decrementareTs() throws InterruptedException{
		while( lista.size() == 0 )
		wait();
				
		lista.peek().decrementTs();

	}
	
	public synchronized long lungime_coada() throws InterruptedException{
		notifyAll();
		long size = lista.size();
		
		return size;
	}
	
public int getLungime()
{
	return lista.size();
	}

public void adauga(Client c)
{
	lista.add(c);}

public void setFlag(boolean b)
{
	flag=b;
}
public boolean getFlag()
{
	return flag;
}

public Queue<Client> getLista()
{
	return lista;}

public int getMed()
{
	return timp_mediu;
}

public synchronized int getTAsteptare() throws InterruptedException
{notifyAll();
	int rez=0;
	for (Client c:lista)
	{
		rez+=c.getTs();
	}
	return rez;
}

}
