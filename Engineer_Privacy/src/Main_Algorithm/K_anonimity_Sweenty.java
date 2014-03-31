package Main_Algorithm;
import Hierachy.*;

import java.io.IOException;
import java.util.*;
public class K_anonimity_Sweenty extends K_anonimity {
	public K_anonimity_Sweenty(String file, int k)
	{
		super(file, k);
		OrderIsdn = new frequency_test();	
		hierachy = new ArrayList<Hierachy_Tree> ();
		level = new ArrayList<Integer>();
		//freq = new ArrayList< ArrayList <String> > (database);
		//System.out.print(column_name.size());
		//priorityQueue = new PriorityQueue <frequency>(database.size(),OrderIsdn);
		
	}
	public K_anonimity_Sweenty(String file, int k, ArrayList<Integer> set)
	{
		super(file, k, set);
		OrderIsdn = new frequency_test();	
		hierachy = new ArrayList<Hierachy_Tree> ();
		level = new ArrayList<Integer>();
		//freq = new ArrayList< ArrayList <String> > (database);
		//System.out.print(column_name.size());
		//priorityQueue = new PriorityQueue <frequency>(database.size(),OrderIsdn);
		
	}
	//public ArrayList< ArrayList <String> > freq;
	class frequency_test implements Comparator<frequency>
	{

		@Override
		public int compare(frequency arg0, frequency arg1) {
			// TODO Auto-generated method stub
			if(arg0.value<arg1.value)
				return 1;
			if(arg0.value>arg1.value)
				return -1;
			return 0;
		}
	
	}
	class frequency
	{
		public int position;
		public int value;
		public frequency(int name, int value)
		{
			this.position = name;
			this.value = value;
		}
	}
	Queue<frequency> priorityQueue;
	
	Comparator<frequency> OrderIsdn ;
	public void create_priority()
	{
		int size = database.get(0).size();
		ArrayList< HashMap<String, Integer> > map= new ArrayList< HashMap<String, Integer>>();
			
		
		ArrayList<frequency> frequency_list = new ArrayList<frequency> ();
		
		for(int i=0;i<column_name.size()-1;i++)
		{
			HashMap<String, Integer> tmp2 = new HashMap<String, Integer>();
			map.add(tmp2);
			frequency tmp = new frequency (i,0);
			frequency_list.add(tmp);
		}
		
		
		
		//System.out.print(size);
		priorityQueue = new PriorityQueue <frequency>(column_name.size(),OrderIsdn);
		for(int i=0;i<database.size();i++)
		{
			
			for(int j=0;j<size;j++)
			{
				
				if(sensitive.get(j) !=1)
				{
					String str = database.get(i).get(j);	
					if(!map.get(j).containsKey(str))
					{
						frequency_list.get(j).value++;
						map.get(j).put(str, 1);
					}
					
					
				}
				
			}
			
		}
		//System.out.print(frequency_list.size());
		for(int i=0;i<frequency_list.size();i++){ 
				
	            priorityQueue.add(frequency_list.get(i));
	            
	        }   
		
	}
	public void print()
	{
		while(!priorityQueue.isEmpty())
		{
			System.out.println(priorityQueue.peek().position+" "+priorityQueue.poll().value);
			
		}
	}
	ArrayList<Hierachy_Tree> hierachy;
	ArrayList<Integer> level ;
	//ArrayList<frequency> freq_list ;
	public void create_hiearchy_tree_for_test() throws IOException
	{
		
		for(int i =0;i<column_name.size();i++){
			level.add(0);
		}
		Hierachy_Tree a = new Hierachy_Tree("root");
		a.create_Tree("age.txt");
		hierachy.add(a);
		a = new Hierachy_Tree("root");
		a.create_Tree("education");
		hierachy.add(a);
		a = new Hierachy_Tree("root");
		a.create_Tree("education");
		
		hierachy.add(a);
		 a = new Hierachy_Tree("root");
			a.create_Tree("marital-status");
			hierachy.add(a);
		//Hierachy_Tree.pre_order(a);
		a = new Hierachy_Tree("root");
			a.create_Tree("race");
			hierachy.add(a);
		a = new Hierachy_Tree("root");
		a.create_Tree("sex");
		
		hierachy.add(a);
	
		
	}
	
	public HashMap<String, String> get_parent_name (int position, int level)
	{
		HashMap<String , String > map = new HashMap<String , String > ();
		Hierachy_Tree root = hierachy.get(position);
		//System.out.println(root.name);
		ArrayList<Hierachy_Tree > list = root.get_ith_level(level);
		
		for(int i=0;i<list.size();i++)
		{
			map.put(list.get(i).name, list.get(i).parent);
		}
		return map;
	}
	public void generalize()
	{
		create_frequency_list();
		
		int count =0;
		while(database.size()>=k && count <5)
		{
			frequency tmp = priorityQueue.poll();
			//System.out.println(tmp.position +" "+ tmp.value);
			int new_level = level.get(tmp.position);
			
			level.set(tmp.position, ++new_level);
			//System.out.println(new_level);
			HashMap<String , String > map = get_parent_name(tmp.position, (new_level-1));
			HashMap<String, Integer> map2 = new HashMap<String, Integer>();
			int parent_num = 0;
			for (Map.Entry<String, String> entry : map.entrySet()) {   
	            if(!map2.containsKey(entry.getValue()))
	            {
	            	//System.out.println(entry.getValue());
	            	map2.put(entry.getValue(), 1);
	            	parent_num++;
	            }
	            
	            
	        }   
			for(int i =0; i<database.size();i++)
			{
				String str = database.get(i).get(tmp.position);
				
				if(map.containsKey(str))
				{					
					database.get(i).set(tmp.position, map.get(str));
				}
			}
		//	System.out.println(tmp.value +" "+map.size()+" "+parent_num);
			frequency new_freq = new frequency (tmp.position, tmp.value - map.size() + map2.size());
			priorityQueue.add(new_freq);
			//print();
			create_frequency_list();
			if(this.check())
				break;
			
			count++;
	
		}
		for(int i=new_database.size();i<this.row;i++)
		{
			ArrayList<String> tmp = new ArrayList<String> ();
			for(int j=0 ;j<this.column;j++)
				tmp.add("*");
			new_database.add(tmp);
		}
	}
	public void create_frequency_list()
	{
		//freq_list.clear();
		HashMap  <String, ArrayList<String> > map = new HashMap<String,  ArrayList<String>>();
		
		for(int i=0;i<database.size();i++)
		{
			String str= "";
			for(int j=0;j<database.get(i).size();j++)
			{
				if(sensitive.get(j) !=1 )
				{
					str = str+database.get(i).get(j)+'@';
				}
				
			}
			if(!map.containsKey(str))
			{
				 ArrayList<String> tmp = new  ArrayList<String>();
				 tmp.add(database.get(i).get(this.sensitive_pos));
				 map.put(str, tmp);
			}
			else
			{
				map.get(str).add(database.get(i).get(this.sensitive_pos));
				
			}
		}
		database.clear();
		 for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {   
			 	
			 		StringTokenizer st = new StringTokenizer(entry.getKey(), "@");
			 		ArrayList<String> tmp=new ArrayList<String>(); 
			 		while (st.hasMoreTokens()) { 
			 			String str=st.nextToken();		  
			 			tmp.add(str);
			 		}
			 		if(entry.getValue().size()>=k){
			 		for(int i=0;i<entry.getValue().size();i++)
			 		{
			 			ArrayList<String> temp = new ArrayList<String> (tmp);
			 			temp.add(entry.getValue().get(i));
			 			new_database.add(temp);
			 		}
			 		}
			 		else
			 		{
			 			for(int i=0;i<entry.getValue().size();i++)
				 		{
				 			ArrayList<String> temp = new ArrayList<String> (tmp);
				 			temp.add(entry.getValue().get(i));
				 			database.add(temp);
				 		}
			 		}
			 	
	        }   
		
		/* for(int i = 0;i<this.row - new_database.size();i++)
		 {
			 ArrayList<String> tmp = new ArrayList<String>();
			 for( int j = 0;j<this.column;j++)
			 {
				 tmp.add("*");
			 }
			 new_database.add(tmp);
		 } database.clear();
		 for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {   
			 	if(entry.getValue().size()<k){
			 		StringTokenizer st = new StringTokenizer(entry.getKey(), "@");
			 		ArrayList<String> tmp=new ArrayList<String>(); 
			 		while (st.hasMoreTokens()) { 
			 			String str=st.nextToken();		  
			 			tmp.add(str);
			 		}
			 		
			 		database.add(tmp);
			 	}
			 	
			 	
	        }
		*/
		 
	}
}
