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
	public ArrayList<Integer> set_column;
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
          HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
          for(int i=0;i<this.set_column.size();i++)
        	  map.put(this.set_column.get(i), 1);
          String line = ""; 
          int judge=0;
          int lines = 0;
          while ((line = br.readLine()) != null && lines<100) { 
        	  int tmp_count = 0;
        	 lines++;
       		  StringTokenizer st = new StringTokenizer(line, ",");
        	  if(judge==0){
        		  while (st.hasMoreTokens()) 
        		  {
        			  if(map.containsKey(tmp_count))
        			  column_name.add(st.nextToken());
        			  else st.nextToken();
        			  tmp_count++;
        		  }
        	  judge=1;
        	  continue;
        	  }
        	  ArrayList<String> tmp=new ArrayList<String>();
        	 
        	  while (st.hasMoreTokens()) { 
        		  if(map.containsKey(tmp_count)){
        		  String str=st.nextToken();	
        		  
        		  if(str.charAt(0)==' ')
        			  tmp.add(str.substring(1));
        		  else tmp.add(str);
        		  
        		  }
        		  else st.nextToken();
        		  tmp_count++;
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
	public void readCSV2(String filename)
	{
		
	  try { 
          File csv = new File(filename);
          BufferedReader br = new BufferedReader(new FileReader(csv));
          HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
          for(int i=0;i<this.set_column.size();i++)
        	  map.put(i, 1);
          String line = ""; 
          int judge=0;
          int lines = 0;
          while ((line = br.readLine()) != null ) { 
        	  int tmp_count = 0;
        	 lines++;
        	 //System.out.println(line);
        
        	 
       		  StringTokenizer st = new StringTokenizer(line, ";");
        	  if(judge==0){
        		  while (st.hasMoreTokens()) 
        		  {
        			  if(map.containsKey(tmp_count))
        			  column_name.add(st.nextToken());
        			  else st.nextToken();
        			  tmp_count++;
        		  }
        	  judge=1;
        	  continue;
        	  }
        	  ArrayList<String> tmp=new ArrayList<String>();
        	 
        	  while (st.hasMoreTokens()) { 
        		  if(map.containsKey(tmp_count)){
        		  String str=st.nextToken();	
        		  if(str.contains("-"))
        		  {
        			  String str2 ="(";
        			  str = str.replace("-", ",");
        			  str2 = str2 + str;
        			  str2 = str2 +")";
        			  tmp.add(str2);
        		  }
        		  else{
        		  if(str.charAt(0)==' ')
        			  tmp.add(str.substring(1));
        		  else tmp.add(str);
        		  }
        		  }
        		  else st.nextToken();
        		  tmp_count++;
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
		set_column = new ArrayList<Integer>();
		for(int i = 0 ;i< column;i++)
			sensitive.add(0);
	}
	public AbstractAlgorithm( String file, ArrayList<Integer> set_column)
	{
		column_name = new ArrayList<String> ();
		column_domain = new ArrayList<ArrayList<String> > ();
		database = new ArrayList<ArrayList<String> > ();
		sensitive =  new ArrayList <Integer> ();
		new_database = new ArrayList<ArrayList<String> > ();
		filename = file;
		this.set_column = set_column;
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
