package jTableaux;

public class Formula {

	public Formula(String lit)
	{
		int conIndex = 0;
		boolean found= false;
		if(lit.charAt(0)!='(')
		{
			if(lit.length()>1)
			{
				if(lit.substring(0,1).equals("!"))
				{
					connective = "!";
				}
				else if(lit.substring(0,2).equals("[]")||lit.substring(0,2).equals("<>"))
				{
					connective = lit.substring(0,2);
				}
				else
				{
					connective="";
					atom=lit;
				}
			}
			else
			{
				connective="";
				atom=lit;
			}
		}
		else
		{
			connective = lit;
			while(lit.contains("("))
			{
				if(connective.substring(connective.length()-1).equals("")&&found==false)
				{
					conIndex = connective.length()-1;
					found = true;
				}
				connective = connective.substring(0,connective.lastIndexOf("("))+connective.substring(connective.indexOf(")", connective.lastIndexOf("("))+1);
			}
		}
		System.out.println(conIndex);
		
		
	}
	
	public Formula(Formula a, Formula b, String conn)
	{
		connective = conn;
		subformulas = {a, b};
	}
	
	public Formula(Formula a, String conn)
	{
		connective = conn;
		subformulas = {a};
	}
	
	public String getMainConnective()
	{
		return connective;
	}
	
	public Formula getLeftFormula()
	{
		if isAtomic()
			return NULL;
		return subformulas[0];
	}
	
	public Formula getRightFormula()
	{
		if isAtomic()
			return NULL;
		return subformulas[1];
	}
	
	public boolean isSingleConnective()
	{
		return connective.equals("!")||connective.equals("[]")||connective.equals("<>");
	}
	
	
	public boolean isAtomic()
	{
		return connective.equals("");
	}
	
	public void toggleUsed()
	{
		ruleUsed = !ruleUsed;
	}
	
	public boolean hasBeenused()
	{
		return ruleUsed;
	}
	
	private String determineConnective(String lit)
	{
		String conn="";
		
		return conn;
	}
	
	private String atom;
	private String connective;
	private Formula[] subformulas;
	private boolean ruleUsed = false;
}
