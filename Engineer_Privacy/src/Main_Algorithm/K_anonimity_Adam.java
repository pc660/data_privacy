package Main_Algorithm;
import java.util.*;
public class K_anonimity_Adam extends K_anonimity {
	public K_anonimity_Adam(String file, int k)
	{
		super(file, k);
		subsets = new ArrayList<ArrayList<Integer>>();
		diameter = new ArrayList<Integer>();
	}
	ArrayList< ArrayList<Integer> >  subsets;
	ArrayList<Integer> diameter;
	public void generate_all_subsets()
	{
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		generate_all_subsets(0, tmp, 0, 0);
	}
	
	private void generate_all_subsets(int size, ArrayList<Integer> tmp, int start, int judge)
	{
		if(start > database.size() || size > 2*this.k -1)
			return ;
		if(judge ==1 && size>= this.k && size<= 2*this.k-1)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>(tmp);
			
			subsets.add(temp);
		}
		
		tmp.add(start);
		generate_all_subsets(size+1, tmp, start+1, 1);
		tmp.remove(tmp.size()-1);
		generate_all_subsets(size, tmp, start+1, 0);	
	}
	private int min_diameter(String s1, String s2)
	{
		int value = 0;
		if(s1.length() >= s2.length())
		{
			int count = s1.length()-1;
			for(int i=s2.length()-1;i>=0;i--)
			{
				if(s1.charAt(count--) != s2.charAt(i) )
				{
					value ++;
				}
			}
			value = value + s1.length() - s2.length();
		}
		else
		{
			int count = s2.length()-1;
			for(int i=s1.length()-1;i>=0;i--)
			{
				if(s2.charAt(count--) != s1.charAt(i) )
				{
					value ++;
				}
			}
			value = value + s2.length() - s1.length();
		}
		return value;
	}
	
	
	public void calculate_diameter()
	{
		for(int i=0;i<subsets.size();i++)
		{
			int max = 0;
			for(int j=0; j<subsets.get(i).size();j++)
			{
				for(int k=j+1;k<subsets.get(i).size();k++)
				{
					int diameter = 0;
					for(int l = 0;l<this.column;l++)
					{
						diameter += min_diameter(database.get(subsets.get(i).get(j)).get(l),database.get(subsets.get(i).get(k)).get(l));
					}
					if(max < diameter)
						max = diameter;
				}
			}
			this.diameter.add(max);
		}
	}
	public void print_subsets()
	{
		for(int i=0;i<subsets.size();i++)
		{
			System.out.println(subsets.get(i));
		}
	}
	public ArrayList<ArrayList<Integer> > generate_cover()
	{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int [] removed_subsets = new int [subsets.size()];
		for(int i=0;i<removed_subsets.length;i++)
			removed_subsets[i] = 0;
		calculate_diameter();
		ArrayList<ArrayList<Integer> > result = new ArrayList<ArrayList<Integer> >();
		while(map.size()<database.size())
		{
			int position = 0;
			double min = 999999;
			for(int i=0; i<subsets.size(); i++)
			{
				//System.out.println(i);
				if(removed_subsets[i] == 1)
					continue;
				//System.out.println(i);
				int occurence = subsets.get(i).size();
				//System.out.println(occurence);
				for(int j = 0; j<subsets.get(i).size() ; j++)
				{
					if(map.containsKey(subsets.get(i).get(j)))
					{
						occurence --;
					}
					
				}
				if(occurence == 0)
					continue;
				double val = this.diameter.get(i)/occurence;
				//System.out.println(val);
				if(min > val)
				{
					min = val;
					position = i;
				}
				//System.out.println(position);	
			}
			
			for(int i=0;i<subsets.get(position).size();i++)
			{
				map.put(subsets.get(position).get(i), 1);
			}
			
			removed_subsets[position] = 1;
			result.add(subsets.get(position));
		}
		return result;
	}
	public void suppression(ArrayList<ArrayList<Integer> > result)
	{
		int [] check_col = new int [this.row];
		ArrayList<String> value = new ArrayList<String> ();
		for(int j=0;j<this.column;j++)
			value.add("*");
		for(int i=0;i<result.size();i++)
		{	
			for(int j=0;j<this.row;j++)
				check_col[j]=0;
			int count = 0;
			ArrayList<String> tmp = database.get(result.get(i).get(0));
			
			for(int j=1;j<result.get(i).size();j++)
			{
				ArrayList<String> new_tmp = database.get(result.get(i).get(j));
				//System.out.println(tmp);
				for(int k=0;k<tmp.size();k++)
				{
					if(check_col[k]!=1 && !tmp.get(k).equals(new_tmp.get(k)))
					{
						check_col[k]=1;
						count ++;
					}
				}
				if(count == this.row-1)
					break;
				tmp = new_tmp;
			}
			for(int j=0;j<this.column;j++)
			{
				//System.out.println(j);
				if(check_col[j]==0)
					value.set(j, tmp.get(j));	
			}
			for(int j=0;j<this.column;j++)
			{		
				if(check_col[j]==0)
					value.set(j, tmp.get(j));	
			}
			for(int m=0;m<result.get(i).size();m++){
				for(int j=0;j<this.column;j++)
				{
					if(sensitive.get(j)==1)
						value.set(j, database.get(result.get(i).get(m)).get(j));
					
				}
				new_database.add(new ArrayList<String>(value));
			}
		}
	}
	public void reduce (ArrayList<ArrayList<Integer> > result)
	{
		//HashMap<Integer, Integer> [] map = new HashMap<Integer, Integer> [this.row];
		ArrayList< HashMap<Integer, Integer> > map = new ArrayList<HashMap<Integer, Integer> >();
		
		for(int i=0;i<this.row;i++)
		{
			HashMap<Integer, Integer> tmp = new HashMap<Integer, Integer> ();
			map.add(tmp);
		}
		
		for(int i =0 ;i<result.size();i++)
		{
			//System.out.println(result.get(i));
			for(int j =0 ;j<result.get(i).size();j++)
			{
				
				map.get(result.get(i).get(j)).put(i, 1);
			}
		}
		
		
		for(int i=0;i<map.size();i++)
		{
			if(map.get(i).size()>1)
			{
				 int count = 0;
				 Iterator it = map.get(i).entrySet().iterator();
				 Map.Entry<Integer, Integer> pairs1 =(Map.Entry<Integer, Integer>)it.next();
				 int subset1 =  pairs1.getKey();
				 it.remove();
				 Map.Entry<Integer, Integer> pairs2 =(Map.Entry<Integer, Integer>)it.next();
				 it.remove();
				 int subset2 = pairs2.getKey();
				 //System.out.println(subset1 +" "+subset2);
				 if(result.get(subset1).size()>this.k || result.get(subset2).size()>k)
				 {
					 if(result.get(subset1).size()>result.get(subset2).size())
					 {
						 result.get(subset1).remove(new Integer(i));
					 }
					 else
						 result.get(subset2).remove(new Integer(i));
					
				 }
				 else
				 {
					 ArrayList<Integer> new_set = new ArrayList<Integer> ();
					 HashMap<Integer, Integer> set_map= new HashMap<Integer, Integer>();
					 for(int j=0;j<result.get(subset1).size();j++)
					 {
						 new_set.add(result.get(subset1).get(j));
						 set_map.put(result.get(subset1).get(j), 1);
					 }
					 for(int j=0;j<result.get(subset2).size();j++)
					 {
						if(!set_map.containsKey(result.get(subset2).get(j)))
						{
							new_set.add(result.get(subset2).get(j));
						}
					 }
					 ArrayList<Integer> tmp1 = result.get(subset1);
					 ArrayList<Integer> tmp2 = result.get(subset2);
					 result.remove(tmp1);
					 result.remove(tmp2);
					 result.add(new_set);
				 }
				 
			}
		}
		
	}
}
