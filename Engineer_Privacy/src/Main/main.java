package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Eval.Evaluation;
import Hierachy.*;
import Main_Algorithm.*;

public class main {

	public static void main(String args[]) throws IOException
	{
		//Hierachy_Tree a = new Hierachy_Tree("root");
		//a.create_Tree("age.txt");
	
		//Hierachy_Tree.pre_order(a);
		ArrayList<Integer> set_column = new ArrayList<Integer>();
		set_column.add(0);
		set_column.add(3);
		set_column.add(8);
		set_column.add(9);
		set_column.add(14);
		//Hierachy_Tree a = new Hierachy_Tree("root");
		ArrayList<Hierachy_Tree> hierachy = new ArrayList<Hierachy_Tree>();
		Hierachy_Tree a = new Hierachy_Tree("root");
		a.create_Tree("age.txt");
		hierachy.add(a);
		

		a = new Hierachy_Tree("root");
		a.create_Tree("education");
		hierachy.add(a);
		
		
		
		a = new Hierachy_Tree("root");
		a.create_Tree("sex");
		hierachy.add(a);
		
		a = new Hierachy_Tree("root");
		a.create_Tree("race");
			hierachy.add(a);
			
				
		
			
		
		K_anonimity_Adam test = new K_anonimity_Adam("test",2,set_column);
		test.sensitive.set(4, 1);
		test.sensitive_pos = 4;
		//for(int i=0;i<test.database.size();i++)
		//	System.out.println(test.database.get(i));
		test.generate_all_subsets();
		 ArrayList<ArrayList<Integer> > result = test.generate_cover();
		 test.suppression(result);
		 for(int i=0;i<test.new_database.size();i++)
			 System.out.println(test.new_database.get(i));
	
		
			
		/*	
		K_anonimity_Sweenty test= new K_anonimity_Sweenty("test", 5,set_column);
		test.sensitive.set(4, 1);
		test.sensitive_pos = 4;
		test.create_priority();
		test.create_hiearchy_tree_for_test();
		test.create_frequency_list();
		
		test.generalize();
		for(int i=0;i<test.new_database.size();i++)
			System.out.println(test.new_database.get(i));*/
		 Evaluation eval = new Evaluation(hierachy);
		/*
		 K_anonimity_Sweenty test2= new K_anonimity_Sweenty("/Users/chaopan/Downloads/arx-result-2/age-race-sex-education-k-20.csv", 5,set_column); 
		 for(int i=0;i<test2.database.size();i++)
			 System.out.println(test2.database.get(i));
		 ArrayList<String> tmp = test2.database.get(4);
		 test2.database.set(4,test2.database.get(3));
		 test2.database.set(3, tmp);
		 */
		
	/*	K_anonimity_Sweenty test= new K_anonimity_Sweenty("example1.csv", 2);
		test.create_priority();
		test.create_hiearchy_tree_for_test();
		test.create_frequency_list();
		test.generalize();
		for(int i=0;i<test.new_database.size();i++)
			System.out.println(test.new_database.get(i));
		K_anonimity_Adam test = new K_anonimity_Adam("example1.csv",2);
		
		test.generate_all_subsets();
		test.print_subsets();
		for(int i=0;i<test.database.size();i++)
			System.out.println(test.database.get(i));
		 ArrayList<ArrayList<Integer> > result = test.generate_cover();
		 for(int i=0;i<result.size();i++)
			 System.out.print(result.get(i));
		 System.out.print('\n');
		 test.reduce(result);
		 for(int i=0;i<result.size();i++)
			 System.out.println(result.get(i));
		 test.suppression(result);
		 for(int i=0;i<test.new_database.size();i++)
			 System.out.println(test.new_database.get(i));*/
		/* l_diversity_Anatomy test = new l_diversity_Anatomy("test",2,set_column);
		 test.sensitive.set(4, 1);
		 test.initilization();
		 test.Anatomy();
		 for(int i=0;i<test.QIT.size();i++)
			 System.out.println(test.QIT.get(i));
		 for(int i=0;i<test.ST.size();i++)
			 System.out.println(test.ST.get(i));
		 */
			set_column.add(1);
			set_column.add(2);
			
		 l_diversity_Anatomy test2 = new l_diversity_Anatomy("example1.csv",2,set_column);
		 test2.sensitive.set(2, 1);
		 test2.initilization();
		 test2.Anatomy();
		 for(int i=0;i<test2.QIT.size();i++)
			 System.out.println(test2.QIT.get(i));
		 for(int i=0;i<test2.ST.size();i++)
			 System.out.println(test2.ST.get(i));
		 
		 
	}
}
