package jTableaux;

public class Formula {

	public Formula(String lit)
	{
		
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
		return subformulas[0];
	}
	
	public Formula getRightFormula()
	{
		if isAtomic()
			return NULL;
		return subformulas[1];
	}
	
	
	public boolean isAtomic()
	{
		return connective.equals("");
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
	
	private String connective;
	private Formula[] subformulas;
	private boolean ruleUsed = false;
}
