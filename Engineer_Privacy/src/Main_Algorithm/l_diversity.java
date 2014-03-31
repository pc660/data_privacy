package Main_Algorithm;

import java.util.ArrayList;

public class l_diversity extends AbstractAlgorithm{
	public l_diversity (String file, int l_value, ArrayList<Integer> set)
	{
		super(file,set);
		this.l = l_value;
	}
	public l_diversity (String file, int l_value)
	{
		super(file);
		this.l = l_value;
	}
	public int l;
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}
}
