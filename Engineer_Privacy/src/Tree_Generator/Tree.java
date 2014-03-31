
package Tree_Generator;
import java.util.ArrayList;
import java.util.List;


public class Tree<T> 
{
    private Node<T> root;

    public Tree(T rootData) 
    {
        setRoot(new Node<T>(rootData));
        getRoot().children = new ArrayList<Node<T>>();
    }

    public Node<T> getRoot() 
    {
		return root;
	}

	public void setRoot(Node<T> root) 
	{
		this.root = root;
	}
	
    public void traverse(Node<T> child)
    { 
    	//post-order traversal
        for(Node<T> each : child.getNodeChildren())
        {
        	if(each.children!=null)
        	{
        		traverse(each);
            	System.out.println(" <- " + each.data);
        	}
        	else
        		System.out.print(each.data + " ");

        }
    }
    
    public void appendToTree(Node<T> x)
    { 
    	/*Test if it works
    	System.out.print("Children of " + x.data + " are ");
    	for(Node<T> each : x.getNodeChildren())
    		System.out.print(each.data + " ");
    	System.out.println();
    	*/
    	Boolean added = false;
        for(Node<T> each : root.getNodeChildren())
        {
        	if(each.data == x.data)
        	{
        		each.children.addAll(x.children);
        		added=true;
        	}
        }
        if(!added)
        	root.children.add(x);
    }
    

	public static class Node<T> 
    {
        private T data;
        private List<Node<T>> children;
        
        public Node(T data)
        {
        	this.data = data;
        }
		public void setNode(T data) 
		{
			this.data = data;
			
		}
		public void setNodeChildren(List<Node<T>> children) 
		{
			this.children = children;
		}
		public T getNodeData() 
		{
			return this.data;
		}
		public List<Node<T>> getNodeChildren() 
		{
			return this.children;
		}
    }
}