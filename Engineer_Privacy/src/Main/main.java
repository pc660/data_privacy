package Main;
import java.util.*;
import Hierachy.*;
import Main_Algorithm.*;

public class main {
	public static void main(String args[])
	{
		Hierachy_Tree a = new Hierachy_Tree ("***");
		Hierachy_Tree b = new Hierachy_Tree (a.name, "b");
		Hierachy_Tree c = new Hierachy_Tree (a.name, "c");
		a.child.add(b);
		a.child.add(c);
		//ArrayList<Hierachy_Tree> child1 = new ArrayList<Hierachy_Tree> ();
		for(int i =0; i<3;i++)
		{
			String name = Integer.toString(i);
			Hierachy_Tree tmp = new Hierachy_Tree(b.name,name);
			b.child.add(tmp);
		}
		Hierachy_Tree.pre_order(a);
		
		K_anonimity_Sweenty test= new K_anonimity_Sweenty("example1.csv", 2);
		test.create_priority();
		test.create_hiearchy_tree_for_test();
		test.create_frequency_list();
		test.generalize();
		for(int i=0;i<test.new_database.size();i++)
			System.out.println(test.new_database.get(i));
	}
}
