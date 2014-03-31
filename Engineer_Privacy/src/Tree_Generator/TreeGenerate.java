package Tree_Generator;
import Tree_Generator.Tree.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class TreeGenerate 
{
	//public Tree getHierarchy()
	public static void main(String args[]) throws IOException
	{
		//Read .txt files
		//Change address to where it is stored on final machine
		BufferedReader br = new BufferedReader(new FileReader("G://Eclipse/workspace/Engineer_Privacy/src/Tree_Generator/hierarchy.txt"));
		
		//Define tree
		Tree <String> hierarchy = new Tree <String>("N/A");		
		try 
		{
			String line = br.readLine();
			String nodeData = "N/A";
			
			while (line != null) 
			{
				ArrayList <Node<String>> children = new ArrayList <Node<String>>();
				String [] nodes = line.split(" ");
				int i = 0;
				nodeData = nodes[0].substring(0, (nodes[0].length() - 1)); //Remove additional ":"
				Node<String> tempParent = new Node<String>(nodeData);
				Node<String> tempChild;
				
				//I'm assuming a child will not have multiple parents
				while(++i < nodes.length)
				{
					//Add node as a child of current_parent
					tempChild = new Node<String>(nodes[i]);
					children.add(tempChild);
				}
				tempParent.setNodeChildren(children);			
				line = br.readLine();
				
				hierarchy.appendToTree(tempParent);
			}
			
			//Tree check
			hierarchy.traverse(hierarchy.getRoot());
			
		} 
		finally 
		{
			br.close();
		}
	}
}

