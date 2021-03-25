package T2.T2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Magazin extends Thread{
	private Casa casa[];
	private int nr_case;
	static int ID =0;
	static int t_sim;
	private int nr_clienti;
	private int tamin,tamax,tsmin,tsmax;
	private ArrayList<Client> lista_clienti=new ArrayList<Client>();
	private String nume_out;
	
	public Magazin( int nr_case, Casa casa[], String name, int nr_clienti,int tamin,int tamax,int tsmin,int tsmax,int t_s ){
		setName( name );
		this.nr_case = nr_case;
		this.tamin=tamin;
		this.tamax=tamax;
		t_sim=t_s;
		this.tsmin=tsmin;
		this.tsmax=tsmax;
		this.casa = new Casa[nr_case];
		this.nr_clienti = nr_clienti;
		for( int i=0; i<nr_case; i++){
			this.casa[i] =casa[i] ; 	
		}
	 }
	
	 private int min_index (){
		int index = 0;
		try{
			long min = casa[0].getTAsteptare();
			for( int i=0; i<nr_case; i++){
			// long lung = casa[ i ].lungime_coada();
				int lung = casa[i].getTAsteptare();
			 if ( lung < min ){
				 min = lung;
				 index = i;
			 }
			}
		 }
		 catch( InterruptedException e ){
			 System.out.println( e.toString());
		 }
		 return index;
	 }

	public void run(){
		 try{PrintWriter writer = new PrintWriter(nume_out, "UTF-8");
			
			 for(int i=0;i<nr_clienti;i++)
				 lista_clienti.add(new Client(ID++,tamin,tamax,tsmin,tsmax));
	
			 	int j=0;
			 	for(j=0;j<nr_case;j++)
			 	{
			 		casa[j].setFlag(true);
			 		casa[j].start();
			 	}
			 
			 int i=0;
			 int time=0;
			 int status_case=1;
			
			 while( time<=t_sim && (lista_clienti.size()!=0 || status_case!=0)){
														 
				 writer.println("time: "+time);			
				 System.out.println("Time:"+time+"/"+t_sim);
			
				for( i=0;i<lista_clienti.size();i++)
					if(time==lista_clienti.get(i).getTa())
					{		
				 int m = min_index();
				
				 casa[m].addClient( lista_clienti.get(i) );
				 lista_clienti.remove(i);
				 i--;		
					}
						 
     			 writer.println("Waiting clients:"+lista_clienti);
				 int index=0;
				 
				 while(index<nr_case)
				 {
				 if(casa[index].getLista().size()!=0)
				     writer.println("casa"+(index+1)+": "+casa[index].getLista());
				 else  
					 writer.println("casa"+(index+1)+":"+"closed");		   		
			   		 index++;
				 }
				
			    writer.println("");
			   	time++;	
			   	status_case=statusCase();
			   		
			   	 for( j=0;j<nr_case;j++)
						if(casa[j].getLungime() >0 && casa[j].getFlag()==false)
							{
							casa[j].setFlag(true);					
							}
			   		
			   		sleep(100 );
			   		for( j=0;j<nr_case;j++)
						if(casa[j].getLungime() ==0)
							{
							casa[j].setFlag(false);						
							}			   					   
			 }
			 float total=0,med=0;
			 for(int z=0;z<nr_case;z++)
				 {total+=casa[z].getMed();
				 casa[z].stop();
				 }
			 
			med=total/nr_clienti;
			 writer.println("timp mediu de asteptare: "+med);
			 writer.close();
		 }
		 catch( InterruptedException e ){
			 System.out.println( e.toString());
		 } catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		 
		
	 }
	public int statusCase()
	{
		int ok=0;
		for(Casa c:casa)
		{
			if(c.getLista().size()!=0)
				ok=1;
		}
		return ok;
	}
	 public int getTimeSim()
	 {
		 return t_sim;
	 }
	 public void setNumeOut(String a)
	 {
		 nume_out=a;
	 }
}
