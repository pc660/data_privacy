package Main_Algorithm;
import java.util.HashMap;
import java.util.Map;


public class K_anonimity extends AbstractAlgorithm {
	public K_anonimity(String file, int k_value)
	{
		super(file);
		k = k_value;
	}
	public int k;
	public boolean check()
	{
		HashMap<String, Integer> map = new  HashMap <String, Integer> ();
		
		for (int i = 0; i<database.size();i++)
		{
			String str = "";
			for (int j = 0; j<database.get(i).size();j++)
			{
				if(sensitive.get(j) != 1)
					str = str + database.get(i).get(j);
				
			}
			//System.out.println(str);
			if(map.containsKey(str))
			{
				int value = map.get(str);
				map.put(str, ++value);
			}
			else
				map.put(str, 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {   
			//System.out.println(entry.getValue());
			if(entry.getValue()<k)
            	return false;
        }  
		return true;
	}
	
	
	
}
