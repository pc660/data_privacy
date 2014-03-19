package Main_Algorithm;
import java.util.*;

import Main_Algorithm.K_anonimity_Sweenty.frequency;
import Main_Algorithm.K_anonimity_Sweenty.frequency_test;

import java.util.*;
public class l_diversity_Anatomy extends l_diversity{
	public l_diversity_Anatomy(String file, int l)
	{
		super(file, l);
		OrderIsdn = new bucket_test();	
	    QIT = new ArrayList<ArrayList<String> >();
	    ST= new ArrayList<ArrayList<String> >();
	    buck = new HashMap<String , bucket>();
	}
	HashMap<String, bucket> buck;
	public ArrayList<ArrayList<String> > QIT;
	public ArrayList<ArrayList<String> > ST;
	public bucket_test OrderIsdn;
	class bucket_test implements Comparator<bucket>
	{

		@Override
		public int compare(bucket arg0, bucket arg1) {
			// TODO Auto-generated method stub
			if(arg0.tuples.size()<arg1.tuples.size())
				return 1;
			if(arg0.tuples.size()>arg1.tuples.size())
				return -1;
			return 0;
		}
	
	}
	class bucket
	{
		public String attribute;
		public ArrayList<Integer> tuples;
		public bucket(String name,  ArrayList<Integer> tmp)
		{
			this.attribute = name;
			this.tuples = tmp;
		}
	}
	Queue<bucket> priorityQueue;
	//HashMap<String, ArrayList<Integer> > bucket; 
	public void initilization()
	{
		int position = 0;
		for(int i = 0; i<sensitive.size(); i++)
		{
			if(sensitive.get(i) == 1)
			{
				position = i;
				break;
			}
		}
		
		
		for(int i = 0; i< database.size(); i++)
		{
			String str = database.get(i).get(position);
			if(buck.containsKey(str))
			{
				buck.get(str).tuples.add(i);
			}
			else
			{
				bucket tmp = new bucket(str, new ArrayList<Integer>());
				tmp.tuples.add(i);
				buck.put(str, tmp);
			}
		}
		priorityQueue = new PriorityQueue<bucket>(buck.size(), OrderIsdn);
		for (Map.Entry<String, bucket> entry : buck.entrySet()) { 
			
			priorityQueue.add(entry.getValue());
        }  
		
	}
	

	public void Anatomy()
	{
		
		int group_id = 0;
		ArrayList<HashMap<Integer, Integer > > QI = new ArrayList<HashMap <Integer, Integer> >();
		//System.out.println(temp_bucket.size());
		while(priorityQueue.size()>=this.l)
		{
			group_id ++;
			HashMap<Integer, Integer> map = new HashMap <Integer, Integer > ();
			ArrayList<bucket> temp_bucket = new ArrayList<bucket> ();
			for(int i = 0; i<l ;i++)
			{
				temp_bucket.add(priorityQueue.poll());
				
			}
			
			for(int i=0;i<l;i++)
			{
				Random generator = new Random(); 
				int remove = generator.nextInt(temp_bucket.get(i).tuples.size());
				int tuple = temp_bucket.get(i).tuples.get(remove);
				map.put(tuple, 1);
				temp_bucket.get(i).tuples.remove(remove);
			}
			for(int i = 0; i<l ;i++)
			{
				if(temp_bucket.get(i).tuples.size()>0)
					priorityQueue.add(temp_bucket.get(i));
			}
			QI.add(map);
		}
		ArrayList<bucket> temp_bucket = new ArrayList<bucket> ();
		for(int i=0;i<QI.size();i++)
			System.out.println(QI.get(i));
		for(int i = 0;i<priorityQueue.size();i++)
		{
			bucket tmp = priorityQueue.poll();
			int tuple = tmp.tuples.get(0);
			ArrayList<Integer> random_tmp = new ArrayList<Integer>();
			for(int j=0;j<QI.size();j++)
			{
				if(!QI.get(i).containsKey(tuple))
				{
					random_tmp.add(j);
				}
			}
			Random generator = new Random(); 
			if(random_tmp.size()>0){
				int add = generator.nextInt(random_tmp.size());
				QI.get(add).put(tuple, 1);	
				tmp.tuples.remove(tuple);
			}
			if(tmp.tuples.size()>0)
				temp_bucket.add(tmp);
		}
		if(temp_bucket.size()>0)
			System.out.println("This is not a valid l-diversity dataset");
		int position = 0;
		
		for(int i = 0; i<sensitive.size(); i++)
		{
			if(sensitive.get(i) == 1)
			{
				position = i;
				break;
			}
		}
		
		
		for(int i=0;i<QI.size();i++)
		{
			System.out.println(QI.get(i).size());
			HashMap<String, Integer> A_s = new HashMap<String, Integer>();
			for (Map.Entry<Integer, Integer> entry : QI.get(i).entrySet()) {   
				int index = entry.getKey();
				ArrayList<String> tmp = database.get(index);
				if(A_s.containsKey(tmp.get(position)))
				{
					int value = A_s.get(tmp.get(position));
					A_s.put(tmp.get(position), ++value);
				}
				else
				{
					A_s.put(tmp.get(position),1);
				}
				tmp.remove(position);
				tmp.add(Integer.toString(i));
				QIT.add(tmp);
	        }  
			for (Map.Entry<String, Integer> entry : A_s.entrySet()) {   
				ArrayList<String>  tmp = new ArrayList<String> ();
				tmp.add(Integer.toString(i));
				tmp.add(entry.getKey());
				tmp.add(Integer.toString(entry.getValue()));	
				ST.add(tmp);
			}
			
		}
		
	}
}
