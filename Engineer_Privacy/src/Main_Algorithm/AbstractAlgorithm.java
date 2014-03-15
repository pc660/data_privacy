package Main_Algorithm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public abstract class AbstractAlgorithm {
	public int column;
	public int row;
	public String filename;
	public ArrayList<String> column_name;
	public ArrayList<Integer> sensitive;
	public ArrayList<ArrayList<String>> column_domain;
	public ArrayList<ArrayList<String > > database;
	public ArrayList<ArrayList<String > > new_database;
	public void setSensitive ( ArrayList <String> sen)
	{
		for(int i= 0;i<column;i++)
		{
			String str = column_name.get(i);
			for(int j=0;j<sen.size();j++)
			{
				if(str.equals(sen.get(j)))
					sensitive.set(i, 1);
			}
		}
	}
	public void readCSV(String filename)
	{
		
	  try { 
          File csv = new File(filename);

          BufferedReader br = new BufferedReader(new FileReader(csv));

          String line = ""; 
          int judge=0;
          while ((line = br.readLine()) != null) { 
       		  StringTokenizer st = new StringTokenizer(line, ",");
        	  if(judge==0){
        		  while (st.hasMoreTokens()) 
        		  {
        			  column_name.add(st.nextToken());
        		  }
        	  judge=1;
        	  continue;
        	  }
        	  ArrayList<String> tmp=new ArrayList<String>();
        	  while (st.hasMoreTokens()) { 
        		  String str=st.nextToken();		  
        		  tmp.add(str);
        	  } 
        	  database.add(tmp);
              
          } 
          br.close();
         
      } catch (FileNotFoundException e) { 
         
          e.printStackTrace(); 
      } catch (IOException e) { 
         
          e.printStackTrace(); 
      }
	//return result; 
  } 
	
	public AbstractAlgorithm( String file)
	{
		column_name = new ArrayList<String> ();
		column_domain = new ArrayList<ArrayList<String> > ();
		database = new ArrayList<ArrayList<String> > ();
		sensitive =  new ArrayList <Integer> ();
		new_database = new ArrayList<ArrayList<String> > ();
		filename = file;
		readCSV(file);
		column = column_name.size();
		row = database.size();
		for(int i = 0 ;i< column;i++)
			sensitive.add(0);
	}
	
	public void print ()
	{
		for (int i=0;i<column_name.size();i++)
		{
			System.out.print(column_name.get(i)+' ');
		}
		System.out.println(' ');
		for(int i = 0; i < database.size(); i++)
		{
			for(int j=0; j <database.get(i).size() ;j++)
			{
				System.out.print(database.get(i).get(j)+' ');
			}
			System.out.print('\n');
		}
	}
	public abstract boolean check();
}
