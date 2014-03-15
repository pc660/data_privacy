package Hierachy;
import java.util.*;
public class Hierachy_Tree {
	public String parent;
	public String name; 
	public ArrayList<Hierachy_Tree> child;
	
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
	
	
}
