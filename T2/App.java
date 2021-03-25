package T2.T2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException
    {	
Integer v[]=new Integer[7];	  	
File myObj = new File(args[0]);
Scanner myReader = new Scanner(myObj);
        
for(int i=0;i<7;i++) 
{
        	if(i==3)
        		{
        		String s=myReader.next();
        		String s1=s.substring(0,s.indexOf(','));
        		String s2=s.substring(s.indexOf(',')+1,s.length());
        	
        		v[3]=Integer.parseInt(s1);
        		v[4]=Integer.parseInt(s2);
        		
        		i=4;
        		continue;
        	}
        	
        	if(i==5)
    		{
        		String s=myReader.next();
        		String s1=s.substring(0,s.indexOf(','));
    		    String s2=s.substring(s.indexOf(',')+1,s.length());
    		
    		    v[5]=Integer.parseInt(s1);
    		    v[6]=Integer.parseInt(s2);
    		    i=6;
    		    continue;
    	}
        	v[i] = myReader.nextInt();
          
         
        }
        
   		int nr = v[1];
   		
   		Casa c[] = new Casa[ nr +1];
   		
   		for( int i=0; i<nr; i++){
   			c[ i ] = new Casa("Casa "+Integer.toString( i ));
   			
   		} 
   		Magazin p = new Magazin( v[1] , c, "Lidl",v[0],v[3],v[4],v[5],v[6],v[2]);
   		p.setNumeOut(args[1]);
   		p.start();
   		
   			
   		
   
    }
    
}
