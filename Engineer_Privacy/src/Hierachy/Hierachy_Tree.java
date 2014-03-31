package Hierachy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Tree_Generator.Tree;
import Tree_Generator.Tree.Node;
public class Hierachy_Tree {
//	Hierachy_Tree root; 
	public String parent;
	public String name; 
	public ArrayList<Hierachy_Tree> child;
	public static int get_num_of_leaves(Hierachy_Tree root)
	{
		int size = 0;
		if(root.child.size() == 0)
			return size + 1;
		for(int i=0;i<root.child.size();i++)
		{
			size = size + get_num_of_leaves(root.child.get(i));
		}
		return size;
	}
	public static int get_num_of_nodes(Hierachy_Tree root)
	{
		int size = 0;
		size = size + root.child.size();
		for(int i = 0;i<root.child.size();i++)
		{
			size = size + get_num_of_nodes(root.child.get(i));
		}
		return size;
	}
	public Hierachy_Tree(String p, String n, ArrayList<Hierachy_Tree> children)
	{
		
		this.parent = new String(p);
		this.name = new String (n);
		this.child = new ArrayList<Hierachy_Tree> ();
		for (int i =0 ;i<children.size();i++)
		{
			if(children.get(i).child.size()==0)
			{
				Hierachy_Tree tmp = new Hierachy_Tree(children.get(i).parent, children.get(i).name);
				child.add(tmp);
			}
			else
			{
				Hierachy_Tree tmp = new Hierachy_Tree(children.get(i).parent, children.get(i).name, children.get(i).child);
				child.add(tmp);
			}
		}
	}
	public Hierachy_Tree(String p, String n)
	{
		this.parent = new String (p);
		this.name = new String (n);
		this.child = new ArrayList<Hierachy_Tree> ();
	}
	public Hierachy_Tree(String n)
	{
		this.parent = "root";
		this.name = n;
		this.child = new ArrayList<Hierachy_Tree> ();
	}	
	public static void pre_order (Hierachy_Tree root)
	{
		
		System.out.println("parent:" + root.parent +", name: " + root.name +", children size: "+root.child.size());
		for(int i = 0; i<root.child.size();i++)
		{
			pre_order(root.child.get(i));
		}
	}
	public int get_size(Hierachy_Tree root)
	{
		if(root == null)
			return 0;
		int max = 0;
		for (int i=0;i<root.child.size();i++)
		{
			int value = get_size(root.child.get(i));
			if (value >max)
				max = value;
		}
		return max+1;
		
	}
	public ArrayList<Hierachy_Tree> get_ith_level(int level)
	{
		ArrayList<Hierachy_Tree> pre = new ArrayList<Hierachy_Tree>();
		
		int size = get_size(this);
		level = size - level;
		//System.out.println(level);
		pre.add(this);
		for(int i=0;i<level-1;i++)
		{
			ArrayList<Hierachy_Tree> curr = new ArrayList<Hierachy_Tree>();
			for(int j=0;j<pre.size();j++)
			{
				for(int k=0;k<pre.get(j).child.size();k++)
				{
					curr.add(pre.get(j).child.get(k));
				}
			}
			pre = curr;
		}
		
		
		
		return pre;
	}
	
	public Hierachy_Tree find_node (String name, Hierachy_Tree root)
	{
		if(root == null)
			return null;
		if(name.equals(root.name))
			return root;
		for(int i=0;i<root.child.size();i++)
		{
			Hierachy_Tree node = find_node(name, root.child.get(i));
			if( node!=null)
				return node;
		}
		return null;
	}
	public  Hierachy_Tree create_Tree(String filename) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		this.name = "root";
		//Hierachy_Tree hierachy = new Hierachy_Tree ("root");
		
		try 
		{
			String line = br.readLine();
			String nodeData = "N/A";
			
			while (line != null) 
			{
				ArrayList <Hierachy_Tree> children = new ArrayList <Hierachy_Tree>();
				String [] nodes = line.split(" ");
				nodeData = nodes[0].substring(0, (nodes[0].length() - 1)); //Remove additional ":"
				if(this.name == "root")
					this.name = nodeData;
				Hierachy_Tree tempParent = this.find_node(nodeData, this);
				Hierachy_Tree tempChild;
				int i = 0;
				//I'm assuming a child will not have multiple parents
				while(++i < nodes.length)
				{
					//Add node as a child of current_parent
					tempChild = new Hierachy_Tree(nodeData, nodes[i]);
					children.add(tempChild);
				}
				tempParent.child = children;
				line = br.readLine();
				
				
			}
		
		}
		finally 
		{
			br.close();
		}
		return this;
	}
	
	    
}
