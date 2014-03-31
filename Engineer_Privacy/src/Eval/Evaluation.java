package Eval;
import Hierachy.Hierachy_Tree;

import java.util.*;
import java.util.*;
public class Evaluation {
	Hierachy_Tree root; 
	ArrayList<Hierachy_Tree> hierachy; 
	public Evaluation(ArrayList< Hierachy_Tree> h)
	{
		root = new Hierachy_Tree ("name");
		hierachy = h;
	}
	public double  precision(ArrayList<ArrayList<String> >  database)
	{
		double sum  = 0;
		HashMap<String, Double> map = new HashMap<String, Double> ();
		ArrayList<Integer> size = new ArrayList<Integer>();
		for(int i=0;i<database.get(0).size()-1;i++)
		{
			int value = Hierachy_Tree.get_num_of_leaves(this.hierachy.get(i));
						size.add(value);
		}
		
		for(int i = 0 ; i<database.size(); i++)
		{
			for(int j=0;j<database.get(i).size()-1;j++)
			{
				String str = database.get(i).get(j);
				

				if(str.equals("*"))
					map.put(str, (double)1);
				else{
				if(!map.containsKey(str))
				{
					
					Hierachy_Tree node  = root.find_node(str, hierachy.get(j));
					
					int tmp = Hierachy_Tree.get_num_of_leaves(node);
					
					if(tmp == 1)
						tmp = 0;
					
					double value =(double)( (double)tmp/(double)size.get(j));
					map.put(str, value);
				}
				
				}
			}
		}
		for (Map.Entry<String, Double> entry : map.entrySet()) 
		{
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		for(int i = 0 ; i<database.size(); i++)
		{
			for(int j=0;j<database.get(i).size()-1;j++)
			{
				String str = database.get(i).get(j);
				
				sum = sum + map.get(str);
			}
		}
		return 1 - sum/(database.size()*(database.get(0).size()-1));
	}
}
